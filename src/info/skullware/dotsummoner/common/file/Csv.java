package info.skullware.dotsummoner.common.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;

public class Csv {

	private Csv() {
	}

	/**
	 * CSVファイル読み込み
	 * 
	 * @param activity
	 * @param filename
	 * @return
	 */
	public static List<String[]> read(final Activity activity, String filename) {
		// TXT読み込み
		InputStream is = null;
		BufferedReader br = null;

		List<String[]> list = new ArrayList<String[]>();
		try {
			try {
				is = activity.getAssets().open(filename);
				br = new BufferedReader(new InputStreamReader(is));

				String str;
				while ((str = br.readLine()) != null) {
					list.add(str.split(","));
				}
			} finally {
				if (br != null) {
					br.close();
				}
			}
		} catch (final IOException e) {
			activity.runOnUiThread(new Runnable() {
			     @Override
			     public void run() {
			          Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
			     }
			});
		}

		return list;
	}
}

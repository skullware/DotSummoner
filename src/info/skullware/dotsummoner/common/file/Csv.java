package info.skullware.dotsummoner.common.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;

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
	public static String read(Activity activity, String filename) {
		// TXT読み込み
		InputStream is = null;
		BufferedReader br = null;

		StringBuilder sb = new StringBuilder();
		try {
			try {
				is = activity.getAssets().open(filename);
				br = new BufferedReader(new InputStreamReader(is));

				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str + "\n");
				}
			} finally {
				if (br != null) {
					br.close();
				}
			}
		} catch (IOException e) {
			// 読み込みエラー
		}

		return sb.toString();
	}
}

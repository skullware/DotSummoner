package info.skullware.dotsummoner.database;

import info.skullware.dotsummoner.database.tables.Binder;
import info.skullware.dotsummoner.database.tables.Units;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	static final String DATABASE_NAME = "dotsummoner.db";
	static final int VERSION = 1;

	protected final Context context;
	protected DatabaseHelper dbHelper;
	protected SQLiteDatabase db;

	/*
	 * コンストラクタ
	 */
	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(this.context);
	}

	/*
	 * 開く
	 */
	public DBAdapter open() {
		this.db = dbHelper.getWritableDatabase();
		this.binder = new Binder(db);
		return this;
	}

	/*
	 * 閉じる
	 */
	public void close() {
		if (db != null)
			db.close();
		if (dbHelper != null)
			dbHelper.close();
	}

	// バインダー
	private Binder binder;
	// ユニット
	private Units units;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private Context context;

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, VERSION);
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			InputStream is;
			BufferedReader reader;
			try {
				is = context.getAssets().open("sql/dbinit.sql");
				reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					if (!line.equals("")) {
						sb.append(line);
						sb.append("\n");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
				for (String sql : sb.toString().split(";")) {
					db.execSQL(sql);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}

	public Binder getBinder() {
		return binder;
	}

	public void setBinder(Binder binder) {
		this.binder = binder;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}
}

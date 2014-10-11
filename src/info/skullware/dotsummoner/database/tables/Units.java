package info.skullware.dotsummoner.database.tables;

import info.skullware.dotsummoner.param.unit.Unit;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Units {

	private SQLiteDatabase db;

	public Units(SQLiteDatabase db) {
		this.db = db;
	}

	public Unit getUnit(String unitId) {
		Cursor cursor = null;
		try {
			String sql = "select * from units where unit_id = '" + unitId + "'";
			cursor = db.rawQuery(sql, null);

			if (cursor != null && cursor.moveToFirst()) {
				Unit unit = new Unit(cursor.getString(1));
				unit.setImagePath(cursor.getString(2));
				unit.setRarity(cursor.getInt(3));
				unit.setName(cursor.getString(4));
				unit.setCost(cursor.getInt(5));
				unit.setLife(cursor.getInt(6));
				unit.setAttack(cursor.getInt(7));
				unit.setRange(cursor.getInt(8));
				unit.setActionName(cursor.getString(12));
				unit.setActionId(cursor.getInt(13));
				unit.setActionValue(cursor.getInt(14));
				unit.setCounterName(cursor.getString(15));
				unit.setCounterId(cursor.getInt(16));
				unit.setCounterValue(cursor.getInt(17));
				unit.setPassiveName(cursor.getString(18));
				unit.setPassiveId(cursor.getInt(19));
				unit.setPassiveValue(cursor.getInt(20));
				return unit;
			} else {
				return null;
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}
}

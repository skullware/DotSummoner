package info.skullware.dotsummoner.database.tables;

import info.skullware.dotsummoner.param.unit.PlayerUnit;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Binder {

	private SQLiteDatabase db;

	public Binder(SQLiteDatabase db) {
		this.db = db;
	}

	public List<PlayerUnit> getUnitList() {
		Cursor cursor = null;
		try {
			List<PlayerUnit> units = new ArrayList<PlayerUnit>();

			String sql = "select a.*,b.* from binder a left join units b on a.unit_id = b.unit_id";
			cursor = db.rawQuery(sql, null);

			if (cursor != null && cursor.moveToFirst()) {
				for (int i = 1; i <= cursor.getCount(); i++) {
					PlayerUnit unit = new PlayerUnit(cursor.getString(1));
					unit.setUseDeck(cursor.getInt(2) != 0);
					unit.setLevel(cursor.getInt(3));
					unit.setExp(cursor.getInt(4));
					unit.setNext(cursor.getInt(5));
					unit.setLife(cursor.getInt(6));
					unit.setAttack(cursor.getInt(7));
					unit.setRange(cursor.getInt(8));
					unit.setActionValue(cursor.getInt(9));
					unit.setCounterValue(cursor.getInt(10));
					unit.setPassiveValue(cursor.getInt(11));

					unit.setImagePath(cursor.getString(14));
					unit.setRarity(cursor.getInt(15));
					unit.setName(cursor.getString(16));
					unit.setCost(cursor.getInt(17));
					unit.setActionName(cursor.getString(24));
					unit.setActionId(cursor.getInt(25));
					unit.setCounterName(cursor.getString(27));
					unit.setCounterId(cursor.getInt(28));
					unit.setPassiveName(cursor.getString(30));
					unit.setPassiveId(cursor.getInt(31));

					units.add(unit);
					cursor.moveToNext();
				}
			}
			return units;
		} finally {
			if (cursor != null)
				cursor.close();
		}

	}
}

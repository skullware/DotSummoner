package info.skullware.dotsummoner.scene.quest;

import info.skullware.dotsummoner.common.file.Csv;
import info.skullware.dotsummoner.database.DBAdapter;
import info.skullware.dotsummoner.param.unit.EnemyUnit;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class Quest {

	private int questId;
	private String name;
	// Battle
	private int maxCost;
	private List<EnemyUnit> enemys = new ArrayList<EnemyUnit>();

	public List<EnemyUnit> getEnemys() {
		return enemys;
	}

	public int getMaxCost() {
		return maxCost;
	}

	public String getName() {
		return name;
	}

	public int getQuestId() {
		return questId;
	}

	public Quest(Activity activity, int questId) {
		List<String[]> list = Csv.read(activity, "quest/10.csv");
		DBAdapter adapter = new DBAdapter(activity).open();
		this.questId = questId;
		for (String[] item : list) {
			if (item[0].equals("Name"))
				this.name = item[1];
			if (item[0].equals("Cost"))
				this.maxCost = Integer.valueOf(item[1]);
			if (item[0].equals("Enemy")) {
				EnemyUnit unit = new EnemyUnit(adapter.getUnits().getUnit(item[1]));
				unit.setLevel(Integer.valueOf(item[2]));
				unit.setPosition(Integer.valueOf(item[3]));
				unit.setInitial(Integer.valueOf(item[4]));
				this.enemys.add(unit);
			}
		}
	}

}

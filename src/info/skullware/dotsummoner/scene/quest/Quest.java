package info.skullware.dotsummoner.scene.quest;

import info.skullware.dotsummoner.param.unit.EnemyUnit;

import java.util.List;

public class Quest {

	private int questId;
	private String name;
	// Battle
	private int maxCost;
	private List<EnemyUnit> enemys;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(int maxCost) {
		this.maxCost = maxCost;
	}

	public List<EnemyUnit> getEnemys() {
		return enemys;
	}

	public void setEnemys(List<EnemyUnit> enemys) {
		this.enemys = enemys;
	}

	public int getQuestId() {
		return questId;
	}

	public void setQuestId(int questId) {
		this.questId = questId;
	}
}

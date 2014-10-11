package info.skullware.dotsummoner.param.unit;


public class EnemyUnit extends Unit {

	private int level;
	private int position;
	private boolean initial;

	public EnemyUnit(String unitId) {
		super(unitId);
	}

	public EnemyUnit(Unit unit) {
		super(unit.getUnitId());
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean getInitial() {
		return initial;
	}

	public void setInitial(int initial) {
		this.initial = (initial == 1);
	}

}

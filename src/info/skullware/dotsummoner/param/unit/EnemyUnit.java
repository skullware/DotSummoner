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
		 this.setImagePath(unit.getImagePath());
		 this.setRarity(unit.getRarity());
		 this.setName(unit.getName());
		 this.setCost(unit.getCost());
		 this.setLife(unit.getLife());
		 this.setAttack(unit.getAttack());
		 this.setRange(unit.getRange());

		 this.setActionName(unit.getActionName());
		 this.setActionId(unit.getActionId());
		 this.setActionValue(unit.getActionValue());
		 this.setCounterName(unit.getCounterName());
		 this.setCounterId(unit.getCounterId());
		 this.setCounterValue(unit.getCounterValue());
		 this.setPassiveName(unit.getPassiveName());
		 this.setPassiveId(unit.getPassiveId());
		 this.setPassiveValue(unit.getPassiveValue());
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

package info.skullware.dotsummoner.database.vo;


public class BinderVo {
	
	private int id;
	private String unitId;
	private boolean use;
	private int level;
	private int exp;
	private int next;
	private int life;
	private int attack;
	private int range;
	private int actionValue;
	private int counterValue;
	private int passiveValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = (use != 0);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getActionValue() {
		return actionValue;
	}

	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	public int getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(int counterValue) {
		this.counterValue = counterValue;
	}

	public int getPassiveValue() {
		return passiveValue;
	}

	public void setPassiveValue(int passiveValue) {
		this.passiveValue = passiveValue;
	}

}

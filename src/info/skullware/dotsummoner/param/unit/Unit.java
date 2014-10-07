package info.skullware.dotsummoner.param.unit;


public class Unit {

	private String unitId;
	private String imagePath;
	private int rarity;
	private String name;
	private int cost;
	private int life;
	private int attack;
	private int range;

	private String actionName;
	private int actionId;
	private int actionValue;
	private String counterName;
	private int counterId;
	private int counterValue;
	private String passiveName;
	private int passiveId;
	private int passiveValue;

	public Unit(String unitId, String imagePath) {
		this.unitId = unitId;
		this.imagePath = imagePath;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getActionValue() {
		return actionValue;
	}

	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public int getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(int counterValue) {
		this.counterValue = counterValue;
	}

	public String getPassiveName() {
		return passiveName;
	}

	public void setPassiveName(String passiveName) {
		this.passiveName = passiveName;
	}

	public int getPassiveId() {
		return passiveId;
	}

	public void setPassiveId(int passiveId) {
		this.passiveId = passiveId;
	}

	public int getPassiveValue() {
		return passiveValue;
	}

	public void setPassiveValue(int passiveValue) {
		this.passiveValue = passiveValue;
	}
}

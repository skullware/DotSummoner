package info.skullware.dotsummoner.param.unit;

public class PlayerUnit extends Unit {

	private boolean useDeck;
	private int level;
	private int exp;
	private int next;

	public PlayerUnit(String unitId, String imagePath) {
		super(unitId, imagePath);
	}

	public boolean isUseDeck() {
		return useDeck;
	}

	public void setUseDeck(boolean useDeck) {
		this.useDeck = useDeck;
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

}

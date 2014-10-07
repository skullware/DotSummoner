package info.skullware.dotsummoner.param;

import info.skullware.dotsummoner.param.unit.PlayerUnit;

import java.util.List;


public class Player {

	private int level;
	private int exp;
	private int next;
	private int coin;
	private int cost;
	private List<PlayerUnit> binder;

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

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<PlayerUnit> getBinder() {
		return binder;
	}

	public void setBinder(List<PlayerUnit> binder) {
		this.binder = binder;
	}
}

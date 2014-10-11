package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;

import java.util.ArrayList;
import java.util.List;

public class BattleSceneDto {
	private List<CardSprite> cards = new ArrayList<CardSprite>();
	private DeckArea deckArea;
	private List<FieldSprite> playerFields;
	private List<FieldSprite> enemyFields;

	public List<CardSprite> getCards() {
		return cards;
	}

	public void setCards(List<CardSprite> cards) {
		this.cards = cards;
	}

	public DeckArea getDeckArea() {
		return deckArea;
	}

	public void setDeckArea(DeckArea deckArea) {
		this.deckArea = deckArea;
	}

	public List<FieldSprite> getPlayerFields() {
		return playerFields;
	}

	public void setPlayerFields(List<FieldSprite> playerFields) {
		this.playerFields = playerFields;
	}

	public List<FieldSprite> getEnemyFields() {
		return enemyFields;
	}

	public void setEnemyFields(List<FieldSprite> enemyFields) {
		this.enemyFields = enemyFields;
	}
}

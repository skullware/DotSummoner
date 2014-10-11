package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.quest.Quest;

import java.util.ArrayList;
import java.util.List;

public class BattleSceneDto {
	private Quest quest;
	private List<CardSprite> cards = new ArrayList<CardSprite>();
	private DeckArea deckArea;
	private List<FieldSprite> playerFields;
	private List<FieldSprite> enemyFields;

	public List<CardSprite> getCards() {
		return cards;
	}

	public DeckArea getDeckArea() {
		return deckArea;
	}

	public List<FieldSprite> getEnemyFields() {
		return enemyFields;
	}

	public List<FieldSprite> getPlayerFields() {
		return playerFields;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setCards(List<CardSprite> cards) {
		this.cards = cards;
	}

	public void setDeckArea(DeckArea deckArea) {
		this.deckArea = deckArea;
	}

	public void setEnemyFields(List<FieldSprite> enemyFields) {
		this.enemyFields = enemyFields;
	}

	public void setPlayerFields(List<FieldSprite> playerFields) {
		this.playerFields = playerFields;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}
}

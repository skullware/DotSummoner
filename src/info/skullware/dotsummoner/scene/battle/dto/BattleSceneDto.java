package info.skullware.dotsummoner.scene.battle.dto;

import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.StatusArea;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;
import info.skullware.dotsummoner.scene.quest.Quest;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.Sprite;

public class BattleSceneDto {

	// スプライト
	private Sprite background;
	private List<CardSprite> cards = new ArrayList<CardSprite>();
	private List<FieldSprite> playerFields = new ArrayList<FieldSprite>();
	private List<FieldSprite> enemyFields = new ArrayList<FieldSprite>();
	private List<UnitSprite> enemys = new ArrayList<UnitSprite>();

	// エフェクト
	private EffectDto effect;

	// デック
	private DeckArea deckArea;

	// ステータス
	private StatusArea statusArea;
	
	// クエスト
	private Quest quest;

	public Sprite getBackground() {
		return background;
	}

	public List<CardSprite> getCards() {
		return cards;
	}

	public DeckArea getDeckArea() {
		return deckArea;
	}

	public EffectDto getEffect() {
		return effect;
	}

	public List<FieldSprite> getEnemyFields() {
		return enemyFields;
	}

	public List<UnitSprite> getEnemys() {
		return enemys;
	}

	public List<FieldSprite> getPlayerFields() {
		return playerFields;
	}

	public Quest getQuest() {
		return quest;
	}

	public StatusArea getStatusArea() {
		return statusArea;
	}

	public void setBackground(Sprite background) {
		this.background = background;
	}

	public void setCards(List<CardSprite> cards) {
		this.cards = cards;
	}

	public void setDeckArea(DeckArea deckArea) {
		this.deckArea = deckArea;
	}

	public void setEffect(EffectDto effect) {
		this.effect = effect;
	}

	public void setEnemyFields(List<FieldSprite> enemyFields) {
		this.enemyFields = enemyFields;
	}

	public void setEnemys(List<UnitSprite> enemys) {
		this.enemys = enemys;
	}

	public void setPlayerFields(List<FieldSprite> playerFields) {
		this.playerFields = playerFields;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public void setStatusArea(StatusArea statusArea) {
		this.statusArea = statusArea;
	}
}

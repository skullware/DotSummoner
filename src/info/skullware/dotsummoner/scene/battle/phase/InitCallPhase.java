package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.database.DBAdapter;
import info.skullware.dotsummoner.param.unit.EnemyUnit;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.BattleSceneDto;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite.States;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import java.util.ArrayList;
import java.util.List;

public class InitCallPhase extends AbstractPhase implements UnitPositionListener, CollisionListener {

	private List<CardSprite> cards;
	private DeckArea deckArea;
	private List<FieldSprite> enemyFields;
	private List<FieldSprite> playerFields;
	private List<EnemyUnit> enemys;

	public InitCallPhase(BattleSceneDto dto) {
		this.cards = dto.getCards();
		this.deckArea = dto.getDeckArea();
		this.playerFields = dto.getPlayerFields();
		this.enemyFields = dto.getEnemyFields();
		for (CardSprite card : cards) {
			card.setCollisionListener(this);
			card.setUnitPositionListener(this);
		}
		this.enemys = dto.getQuest().getEnemys();
	}

	@Override
	public void finish() {
		this.nextPhaseListener.nextPhase(this);
	}

	@Override
	public void init(KeyListenScene scene) {

		DBAdapter adapter = new DBAdapter(scene.getBaseActivity()).open();
		List<PlayerUnit> initialUnits = adapter.getBinder().getUnitList();
		// カードスプライト生成
		for (PlayerUnit unit : initialUnits) {
			CardSprite uSprite = new CardSprite(scene.getBaseActivity().getResourceUtil()
					.getSprite("unit/" + unit.getImagePath().replace(".png", "_c.png")), unit);
			uSprite.setText(scene.getBaseActivity());
			uSprite.setCollisionListener(this);
			uSprite.setUnitPositionListener(this);
			cards.add(uSprite);
			scene.registerTouchArea(uSprite);
			scene.setTouchAreaBindingOnActionDownEnabled(true);
		}
		// デックエリア設定
		deckArea = new DeckArea(scene);
		deckArea.setDeckUnits(cards);
		// フィールド生成
		playerFields = new ArrayList<FieldSprite>();
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
				FieldSprite field = new FieldSprite(scene.getBaseActivity().getResourceUtil()
						.getSprite("battle/field.png"));
				field.setPosition(430 + n * 20 + i * 100, 280 - n * 65);
				field.setZIndex((3 - n) * 10);
				playerFields.add(field);
				scene.attachChild(field);
			}
		}
		enemyFields = new ArrayList<FieldSprite>();
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
				FieldSprite field = new FieldSprite(scene.getBaseActivity().getResourceUtil()
						.getSprite("battle/field.png"));
				field.setPosition(370 - n * 20 - i * 100 - field.getWidth(), 280 - n * 65);
				field.setZIndex((3 - n) * 10);
				field.setFlippedHorizontal(true);
				enemyFields.add(field);
				scene.attachChild(field);
			}
		}
		// Enemy設定
		for (EnemyUnit enemy : enemys) {
			UnitSprite eSprite = new UnitSprite(scene.getBaseActivity().getResourceUtil()
					.getSprite("unit/" + enemy.getImagePath()), enemy);
			FieldSprite field = enemyFields.get(enemy.getPosition());
			field.attachChild(eSprite);
			eSprite.setPosition(field.getWidth() / 2 - eSprite.getWidth() / 2, 40 - eSprite.getHeight());
			eSprite.setZIndex(field.getZIndex() + 1);
		}
		scene.sortChildren();
	}

	/**
	 * フィールド衝突判定（ユニットドラッグ）
	 */
	@Override
	public void onCollisionAtFieldWithDown(CardSprite card) {
		// // フィールドの可視化
		// for (FieldSprite fieldSprite : playerFields) {
		// fieldSprite.setVisible(true);
		// }
	}

	/**
	 * フィールド衝突判定（ユニット移動）
	 */
	@Override
	public void onCollisionAtFieldWithMove(CardSprite card) {
		boolean isCollision = false;
		// フィールドとの衝突判定
		for (FieldSprite field : playerFields) {
			if (card.collidesWith(field) && !isCollision) {
				// フィールドの点滅
				Effects.flash(field, 0.3f);
				isCollision = true;
			} else {
				field.clearEntityModifiers();
				field.setAlpha(0.8f);
			}
		}
	}

	/**
	 * フィールド衝突判定（ユニットドロップ）
	 */
	@Override
	public boolean onCollisionAtFieldWithUp(CardSprite card) {
		// フィールドとの衝突判定
		for (FieldSprite field : playerFields) {
			if (card.collidesWith(field) && !field.isUse()) {
				// フィールドに貼り付けO
				card.setState(States.PRE_FIELD);
				card.onFieldUnitSprite(card);
				field.attachChild(card);
				card.setPosition(field.getWidth() / 2 - card.getWidth() / 2, 40 - card.getHeight());
				card.setZIndex(field.getZIndex() + 1);
				card.setRotation(10f);
				field.setUse(true);
				field.clearEntityModifiers();
				return true;
			}
		}
		// // タイル不可視
		// for (FieldSprite fieldSprite : playerFields) {
		// fieldSprite.setVisible(false);
		// }
		return false;
	}

	@Override
	public void onFieldUnitSprite(CardSprite card) {
		// デックエリア再設定
		deckArea.setDeckUnits(cards);
	}

}

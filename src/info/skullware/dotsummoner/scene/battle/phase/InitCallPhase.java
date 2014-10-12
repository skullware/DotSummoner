package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.MainActivity;
import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.scene.battle.BattleScene;
import info.skullware.dotsummoner.scene.battle.BattleSceneDto;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite.States;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import org.andengine.entity.text.Text;

public class InitCallPhase extends AbstractPhase implements UnitPositionListener, CollisionListener {

	private BattleSceneDto dto;

	public InitCallPhase(BattleSceneDto dto) {
		this.dto = dto;
		for (CardSprite card : dto.getCards()) {
			card.setCollisionListener(this);
			card.setUnitPositionListener(this);
		}
	}

	@Override
	public void finish() {
		this.nextPhaseListener.nextPhase(this);
	}

	@Override
	public void init(BattleScene scene) {
		// 背景
		scene.attachChild(dto.getBackground());
		// カード
		for (CardSprite card : dto.getCards()) {
			card.setCollisionListener(this);
			card.setUnitPositionListener(this);
			scene.registerTouchArea(card);
			scene.setTouchAreaBindingOnActionDownEnabled(true);

		}
		// デックエリア設定
		scene.attachChild(dto.getDeckArea());
		scene.registerTouchArea(dto.getDeckArea());
		dto.getDeckArea().setDeckUnits(dto.getCards());

		// コスト表示
		Text cost = PixelMplus.getStrokeTextRegular12(scene.getBaseActivity(), "COST 000/123", 0,
				10);
		cost.setX(MainActivity.WIDTH - cost.getWidth() - 10);
		scene.attachChild(cost);
		// フィールド生成
		for (FieldSprite field : dto.getPlayerFields()) {
			scene.attachChild(field);
		}
		for (FieldSprite field : dto.getEnemyFields()) {
			scene.attachChild(field);
		}
		// Enemy設定
		for (UnitSprite enemy : dto.getEnemys()) {
			FieldSprite field = dto.getEnemyFields().get(enemy.getPosition());
			field.attachChild(enemy);
			enemy.setPosition(field.getWidth() / 2 - enemy.getWidth() / 2, 40 - enemy.getHeight());
			enemy.setZIndex(field.getZIndex() + 1);
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
		for (FieldSprite field : dto.getPlayerFields()) {
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
		for (FieldSprite field : dto.getPlayerFields()) {
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
		dto.getDeckArea().setDeckUnits(dto.getCards());
	}

}

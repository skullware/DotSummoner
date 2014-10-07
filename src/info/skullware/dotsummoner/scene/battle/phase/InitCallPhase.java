package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite.States;

import java.util.List;

import org.andengine.entity.modifier.MoveModifier;

public class InitCallPhase implements UnitPositionListener, CollisionListener {

	private List<UnitSprite> playerUnits;
	private DeckArea deckArea;
	private List<FieldSprite> fields;

	public InitCallPhase(List<UnitSprite> playerUnits, DeckArea deckArea, List<FieldSprite> fields) {
		this.playerUnits = playerUnits;
		this.deckArea = deckArea;
		this.fields = fields;
		for (UnitSprite unit : playerUnits) {
			unit.setCollisionListener(this);
			unit.setUnitPositionListener(this);
		}
	}

	public void init() {

	}

	/**
	 * フィールド衝突判定（ユニットドラッグ）
	 */
	@Override
	public void onCollisionAtFieldWithDown(UnitSprite unit) {
		// フィールドの可視化
		for (FieldSprite fieldSprite : fields) {
			fieldSprite.setVisible(true);
		}
	}

	/**
	 * フィールド衝突判定（ユニットドロップ）
	 */
	@Override
	public boolean onCollisionAtFieldWithUp(UnitSprite unit) {
		// フィールドとの衝突判定
		for (FieldSprite field : fields) {
			if (unit.collidesWith(field)) {
				// フィールドに貼り付け
				float centerX = field.getX() + field.getWidth() / 2;
				float centerY = field.getY() + 40;
				unit.registerEntityModifier(new MoveModifier(1.0f, unit.getX(), centerX - unit.getWidth() / 2, unit
						.getY(), centerY - unit.getHeight()));
				unit.setState(States.PRE_FIELD);
				unit.onFieldUnitSprite(unit);
				field.setUse(true);
				field.clearEntityModifiers();
				return true;
			}
		}
		// タイル不可視
		for (FieldSprite fieldSprite : fields) {
			fieldSprite.setVisible(false);
		}
		return false;
	}

	/**
	 * フィールド衝突判定（ユニット移動）
	 */
	@Override
	public void onCollisionAtFieldWithMove(UnitSprite unit) {
		boolean isCollision = false;
		// フィールドとの衝突判定
		for (FieldSprite field : fields) {
			if (unit.collidesWith(field) && !isCollision) {
				// フィールドの点滅
				Effects.Flash(field, 0.3f);
				isCollision = true;
			} else {
				field.clearEntityModifiers();
				field.setAlpha(0.8f);
			}
		}
	}

	@Override
	public void onFieldUnitSprite(UnitSprite unit) {
		// デックエリア再設定
		deckArea.setDeckUnits(playerUnits);
	}
}

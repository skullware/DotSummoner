package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.param.unit.Unit;

import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.sprite.Sprite;

public class FieldSprite extends Sprite {

	private boolean isFlash;
	private IEntityModifier flashModifier = Effects.getFlashModifier(0.5f);
	private Unit unitData;

	public FieldSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite
				.getVertexBufferObjectManager());
		this.isFlash = false;
	}

	/*
	 * アタッチカード
	 */
	public void attachCard(CardSprite card) {
		card.setPosition(this.getWidth() / 2 - card.getWidth() / 2, 40 - card.getHeight());
		card.setZIndex(this.getZIndex() + 1);
		card.setRotation(10f);
		this.unitData = card.getUnitData();
		this.clear();
		this.attachChild(card);
	}

	public void clear() {
		if (isFlash) {
			this.unregisterEntityModifier(flashModifier);
			this.isFlash = false;
			this.setAlpha(0.8f);
		}
	}

	/*
	 * デタッチカード
	 */
	public void detachCard(CardSprite card) {
		this.detachChild(card);
		this.unitData = null;
	}

	public void flash() {
		if (!isFlash) {
			this.registerEntityModifier(flashModifier);
			this.isFlash = true;
		}
	}

	public Unit getUnitData() {
		return unitData;
	}
}

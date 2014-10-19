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

	public void clear() {
		if (isFlash) {
			this.unregisterEntityModifier(flashModifier);
			this.isFlash = false;
			this.setAlpha(0.8f);
		}
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

	public void setUnitData(Unit unitData) {
		this.unitData = unitData;
	}
}

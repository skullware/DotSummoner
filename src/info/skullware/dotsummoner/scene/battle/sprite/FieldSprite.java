package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.param.unit.Unit;

import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.sprite.Sprite;

public class FieldSprite extends Sprite {

	private IEntityModifier flashModifier = Effects.getFlashModifier(0.5f);
	private Unit unitData;

	public FieldSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite
				.getVertexBufferObjectManager());
	}

	public void clear() {
		this.unregisterEntityModifier(flashModifier);
		this.setAlpha(1f);
	}

	public void flash() {
		this.registerEntityModifier(flashModifier);
	}

	public Unit getUnitData() {
		return unitData;
	}

	public void setUnitData(Unit unitData) {
		this.unitData = unitData;
	}
}

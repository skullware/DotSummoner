package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.param.unit.Unit;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;

public class UnitSprite extends Sprite {

	/**
	 * アタッチユニットデータ
	 */
	private Unit unitData;
	private int position;

	public UnitSprite(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion, ISpriteVertexBufferObject pSpriteVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pSpriteVertexBufferObject);
	}

	public UnitSprite(Sprite sprite, Unit unit) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite
				.getVertexBufferObjectManager());
		this.unitData = unit;
	}

	public int getPosition() {
		return position;
	}

	public Unit getUnitData() {
		return unitData;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setUnitData(Unit unitData) {
		this.unitData = unitData;
	}
}

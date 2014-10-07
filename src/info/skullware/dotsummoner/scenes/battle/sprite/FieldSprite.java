package info.skullware.dotsummoner.scenes.battle.sprite;

import org.andengine.entity.sprite.Sprite;

public class FieldSprite extends Sprite {

	private boolean use;
	
	public FieldSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite.getVertexBufferObjectManager());
		// super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}
}

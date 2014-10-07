package info.skullware.dotsummoner.common.entity;

import org.andengine.entity.sprite.Sprite;

public class LayerSprite extends Sprite {

	public LayerSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite.getVertexBufferObjectManager());
	}

}

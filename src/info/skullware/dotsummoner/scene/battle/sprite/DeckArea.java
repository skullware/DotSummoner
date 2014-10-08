package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.MainActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;

import java.util.List;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class DeckArea extends Rectangle {

	public static int HEIGHT = 128;
	public static int WIDTH = 800;
	public static int ZINDEX = 2;

	private float fx;

	public DeckArea(KeyListenScene scene) {
		super(0, MainActivity.HEIGHT - HEIGHT, WIDTH, HEIGHT, scene.getBaseActivity()
				.getVertexBufferObjectManager());
		this.setColor(org.andengine.util.color.Color.BLACK);
		this.setAlpha(0.5f);
		this.setZIndex(ZINDEX);
		scene.attachChild(this);
		scene.registerTouchArea(this);
	}

	/*
	 * タッチスクロール
	 * 
	 * @see org.andengine.entity.shape.Shape#onAreaTouched(org.andengine.
	 * input.touch.TouchEvent, float, float)
	 */
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		Sprite first = (Sprite) this.getFirstChild();
		Sprite last = (Sprite) this.getLastChild();
		if (last.getX() + last.getWidth() < 800)
			return true;

		float x = pSceneTouchEvent.getX();
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN: {
			fx = x;
			break;
		}
		case TouchEvent.ACTION_MOVE: {
			float dx = (x - fx);
			if (first.getX() + dx > 10)
				dx = 10 - first.getX();
			if (last.getX() + last.getWidth() + dx < 790)
				dx = 790 - (last.getX() + last.getWidth());
			for (int index = 0; index < this.getChildCount(); index++) {
				UnitSprite unit = (UnitSprite) this.getChildByIndex(index);
				if (unit.isStateDeck())
					unit.registerEntityModifier(new MoveXModifier(0.01f, unit.getX(), unit.getX()
							+ dx));
			}
			break;
		}
		}
		return true;
	}

	public void setDeckUnits(List<UnitSprite> sprites) {
		this.detachChildren();
		for (UnitSprite unitSprite : sprites) {
			if (unitSprite.isStateDeck()) {
				unitSprite.setZIndex(this.getZIndex() + 1);
				this.attachChild(unitSprite);
			}
		}
		float currentX = 10;
		for (int index = 0; index < this.getChildCount(); index++) {
			Sprite unit = (Sprite) this.getChildByIndex(index);
			unit.setPosition(currentX, HEIGHT - 10 - unit.getHeight());
			currentX += unit.getWidth() + 5;
		}
	}
}

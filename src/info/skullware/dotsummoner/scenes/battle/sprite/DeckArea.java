package info.skullware.dotsummoner.scenes.battle.sprite;

import info.skullware.dotsummoner.common.KeyListenScene;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.input.touch.TouchEvent;

public class DeckArea extends Rectangle {

	public static int HEIGHT = 128;
	public static int WIDTH = 800;
	private List<UnitSprite> deckUnits = new ArrayList<UnitSprite>();

	private float deckUnitsWidth;
	private float fx;

	public DeckArea(KeyListenScene scene) {
		super(0, 480 - HEIGHT, WIDTH, HEIGHT, scene.getBaseActivity().getVertexBufferObjectManager());
		this.setColor(org.andengine.util.color.Color.BLACK);
		this.setAlpha(0.5f);
		this.setZIndex(2);
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
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (deckUnitsWidth < 800)
			return true;

		float x = pSceneTouchEvent.getX();
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN: {
			fx = x;
			break;
		}
		case TouchEvent.ACTION_MOVE: {
			float dx = (x - fx);
			UnitSprite first = deckUnits.get(0);
			UnitSprite last = deckUnits.get(deckUnits.size() - 1);
			if (first.getX() + dx > 10)
				dx = 10 - first.getX();
			if (last.getX() + last.getWidth() + dx < 790)
				dx = 790 - (last.getX() + last.getWidth());
			for (UnitSprite sprite : deckUnits) {
				if (sprite.isStateDeck())
					sprite.registerEntityModifier(new MoveXModifier(0.01f, sprite.getX(), sprite.getX() + dx));
			}
			break;
		}
		}
		return true;
	}

	public void setDeckUnits(List<UnitSprite> sprites) {
		deckUnits.clear();
		for (UnitSprite unitSprite : sprites) {
			if (unitSprite.isStateDeck()) {
				deckUnits.add(unitSprite);
			}
		}
		float currentX = 10;
		for (UnitSprite unitSprite : deckUnits) {
			unitSprite.setPosition(currentX, 480 - 10 - unitSprite.getHeight());
			currentX += unitSprite.getWidth();
		}
		this.deckUnitsWidth = currentX;
	}
}

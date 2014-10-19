package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;

import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class CardSprite extends Sprite {

	public static enum States {
		// デックエリア
		DECK_AREA,
		// フィールド
		FIELD_AREA
	}

	/**
	 * 衝突検知リスナ
	 */
	private CollisionListener collisionListener;

	private IEntityModifier flashModifier = Effects.getFlashModifier(2);
	/**
	 * 移動元X
	 */
	private float fx;
	/**
	 * 移動元Y
	 */
	private float fy;

	/**
	 * 状態
	 */
	private States state;

	/**
	 * アタッチユニットデータ
	 */
	private PlayerUnit unitData;

	/**
	 * コンストラクタ
	 * 
	 * @param sprite
	 */
	public CardSprite(Sprite sprite, PlayerUnit unit) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite
				.getVertexBufferObjectManager());
		this.state = States.DECK_AREA;
		this.unitData = unit;
	}

	public States getState() {
		return state;
	}

	public PlayerUnit getUnitData() {
		return unitData;
	}

	public boolean isStateDeck() {
		return (state == States.DECK_AREA);
	}

	@Override
	public boolean onAreaTouched(TouchEvent touchEvent, float touchAreaLocalX, float touchAreaLocalY) {
		switch (state) {
		case DECK_AREA:
			onDeckAreaTouched(touchEvent);
			break;
		case FIELD_AREA:
			onReDragAndDropUnit(touchEvent);
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * リスナーを削除する
	 */
	public void removeListener() {
		this.collisionListener = null;
	}

	/**
	 * リスナーを追加する
	 * 
	 * @param listener
	 */
	public void setCollisionListener(CollisionListener listener) {
		this.collisionListener = listener;
	}

	public void setState(States state) {
		this.state = state;
	}

	public void setText(MultiSceneActivity activity) {
		Text cost = PixelMplus.getStrokeTextRegular10B(activity,
				String.valueOf(unitData.getCost()), 7, 3);
		Text level = PixelMplus.getStrokeTextRegular10B(activity,
				String.valueOf(unitData.getLevel()), 48, 64);
		level.setPosition(this.getWidth() - level.getWidth() - 7,
				this.getHeight() - level.getHeight() - 3);
		this.attachChild(cost);
		this.attachChild(level);
	}

	public void setUnitData(PlayerUnit unitData) {
		this.unitData = unitData;
	}

	/*
	 * ユニット配置イベント
	 */
	private void onDeckAreaTouched(final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			fx = this.getX();
			fy = this.getY();
			this.registerEntityModifier(flashModifier);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			this.collisionListener.onCollisionMove(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			if (!this.collisionListener.onCollisionUp(this)) {
				// 移動キャンセル
				this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX()
						- this.getParent().getX() - this.getWidth() / 2, fx, pSceneTouchEvent
						.getY() - this.getParent().getY() - this.getHeight() / 2, fy));
			}
			this.setAlpha(1);
			this.unregisterEntityModifier(flashModifier);
			return;
		}
		// 移動
		this.setPosition(pSceneTouchEvent.getX() - this.getParent().getX() - this.getWidth() / 2,
				pSceneTouchEvent.getY() - this.getParent().getY() - this.getHeight() / 2);
	}

	/*
	 * ユニット再配置イベント
	 */
	private void onReDragAndDropUnit(final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			fx = this.getX();
			fy = this.getY();
			this.registerEntityModifier(flashModifier);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			this.collisionListener.onCollisionMove(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			if (!this.collisionListener.onCollisionReUp(this)) {
				// 移動キャンセル
				this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX()
						- this.getParent().getX() - this.getWidth() / 2, fx, pSceneTouchEvent
						.getY() - this.getParent().getY() - this.getHeight() / 2, fy));
			}
			this.setAlpha(1);
			this.unregisterEntityModifier(flashModifier);
			return;
		}
		// 移動
		this.setPosition(pSceneTouchEvent.getX() - this.getParent().getX() - this.getWidth() / 2,
				pSceneTouchEvent.getY() - this.getParent().getY() - this.getHeight() / 2);
	}
}

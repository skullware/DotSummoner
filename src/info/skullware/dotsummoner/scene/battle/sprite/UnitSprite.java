package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class UnitSprite extends Sprite {

	public static enum States {
		ACTIVE,
		// デックエリア
		DECK_AREA,
		// フィールド
		FIELD_AREA, // フィールド（未決定）
		PRE_FIELD
	}

	/**
	 * 衝突検知リスナ
	 */
	private CollisionListener collisionListener;

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
	 * ユニット位置変化リスナ
	 */
	private UnitPositionListener unitPositionListener;

	/**
	 * コンストラクタ
	 * 
	 * @param sprite
	 */
	public UnitSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite.getVertexBufferObjectManager());
		state = States.DECK_AREA;
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
			onDragAndDropUnit(touchEvent);
			break;
		case PRE_FIELD:
			onReDragAndDropUnit(touchEvent);
			break;

		default:
			break;
		}
		return true;
	}

	private void onDragAndDropUnit(final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			fx = this.getX();
			fy = this.getY();
			this.collisionListener.onCollisionAtFieldWithDown(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			this.collisionListener.onCollisionAtFieldWithMove(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			if (!this.collisionListener.onCollisionAtFieldWithUp(this)) {
				// 移動キャンセル
				this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX(), fx,
						pSceneTouchEvent.getY(), fy));
			}
			return;
		}
		// 移動
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
	}

	public void onFieldUnitSprite(UnitSprite unit) {
		unitPositionListener.onFieldUnitSprite(unit);
	}

	private void onReDragAndDropUnit(final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			fx = this.getX();
			fy = this.getY();
			this.collisionListener.onCollisionAtFieldWithDown(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			this.collisionListener.onCollisionAtFieldWithMove(this);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			if (!this.collisionListener.onCollisionAtFieldWithUp(this)) {
				// 移動キャンセル
				this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX(), fx,
						pSceneTouchEvent.getY(), fy));
			}
			return;
		}
		// 移動
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
	}

	/**
	 * リスナーを削除する
	 */
	public void removeListener() {
		this.unitPositionListener = null;
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

	public void setUnitData(PlayerUnit unitData) {
		this.unitData = unitData;
	}

	/**
	 * リスナーを追加する
	 * 
	 * @param listener
	 */
	public void setUnitPositionListener(UnitPositionListener listener) {
		this.unitPositionListener = listener;
	}
}

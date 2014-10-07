package info.skullware.dotsummoner.scenes.battle.sprite;

import info.skullware.dotsummoner.common.KeyListenScene;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scenes.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scenes.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.utils.Effects;

import java.util.ArrayList;
import java.util.List;

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

	private static List<FieldSprite> fields = new ArrayList<FieldSprite>();

	private static void setFieldAlpha(float pAlpha) {
		for (FieldSprite field : fields) {
			if (!field.isUse())
				field.setAlpha(pAlpha);
		}
	}

	public static void setTileData(KeyListenScene scene) {
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
				FieldSprite field = new FieldSprite(scene.getBaseActivity().getResourceUtil()
						.getSprite("battle/field.png"));
				field.setPosition(430 + n * 20 + i * 100, 280 - n * 65);
				field.setAlpha(0);
				fields.add(field);
				scene.attachChild(field);
			}
		}
	}

	private float fx;
	private float fy;
	/**
	 * ユニット位置変化リスナ
	 */
	private UnitPositionListener unitPositionListener;
	/**
	 * 衝突検知リスナ
	 */
	private CollisionListener collisionListener;

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
	public UnitSprite(Sprite sprite) {
		super(sprite.getX(), sprite.getY(), sprite.getTextureRegion(), sprite.getVertexBufferObjectManager());
		state = States.DECK_AREA;
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
			// フィールドの可視化
			setFieldAlpha(0.8f);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			this.collisionListener.onCollisionAtFieldWithUp(this);

			// boolean isCollision = false;
			// // フィールドとの衝突判定
			// for (Sprite field : fields) {
			// if (this.collidesWith(field) && !isCollision) {
			// // フィールドの点滅
			// Effects.Flash(field, 0.3f);
			// isCollision = true;
			// } else {
			// field.clearEntityModifiers();
			// field.setAlpha(0.8f);
			// }
			// }
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			// フィールドとの衝突判定
			for (FieldSprite field : fields) {
				if (this.collidesWith(field)) {
					// フィールドに貼り付け
					float centerX = field.getX() + field.getWidth() / 2;
					float centerY = field.getY() + 40;
					this.registerEntityModifier(new MoveModifier(1.0f, this.getX(), centerX - this.getWidth() / 2, this
							.getY(), centerY - this.getHeight()));
					this.state = States.PRE_FIELD;
					this.unitPositionListener.onFieldUnitSprite(this);
					field.setUse(true);
					field.clearEntityModifiers();
					return;
				}
			}
			// 移動キャンセル
			this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX(), fx, pSceneTouchEvent.getY(), fy));
			// タイル不可視
			setFieldAlpha(0);
			return;
		}
		// 移動
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
	}

	private void onReDragAndDropUnit(final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			fx = this.getX();
			fy = this.getY();
			// フィールドの可視化
			setFieldAlpha(0.8f);
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
			boolean isCollision = false;
			// フィールドとの衝突判定
			for (Sprite field : fields) {
				if (this.collidesWith(field) && !isCollision) {
					// フィールドの点滅
					Effects.Flash(field, 0.3f);
					isCollision = true;
				} else {
					field.clearEntityModifiers();
					field.setAlpha(0.8f);
				}
			}
		} else if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
			// フィールドとの衝突判定
			for (FieldSprite field : fields) {
				if (this.collidesWith(field)) {
					// フィールドに貼り付け
					float centerX = field.getX() + field.getWidth() / 2;
					float centerY = field.getY() + 40;
					this.registerEntityModifier(new MoveModifier(1.0f, this.getX(), centerX - this.getWidth() / 2, this
							.getY(), centerY - this.getHeight()));
					this.state = States.PRE_FIELD;
					this.unitPositionListener.onFieldUnitSprite(this);
					field.setUse(true);
					field.clearEntityModifiers();
					return;
				}
			}
			// 移動キャンセル
			this.registerEntityModifier(new MoveModifier(0.5f, pSceneTouchEvent.getX(), fx, pSceneTouchEvent.getY(), fy));
			// タイル不可視
			setFieldAlpha(0);
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
	public void setUnitPositionListener(UnitPositionListener listener) {
		this.unitPositionListener = listener;
	}

	/**
	 * リスナーを追加する
	 * 
	 * @param listener
	 */
	public void setCollisionListener(CollisionListener listener) {
		this.collisionListener = listener;
	}

	public void setUnitData(PlayerUnit unitData) {
		this.unitData = unitData;
	}
}

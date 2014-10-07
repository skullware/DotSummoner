package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.database.DBAdapter;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite.States;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.modifier.MoveModifier;

public class InitCallPhase extends AbstractPhase implements UnitPositionListener, CollisionListener {

	private List<UnitSprite> playerUnits;
	private DeckArea deckArea;
	private List<FieldSprite> fields;

	public InitCallPhase(List<UnitSprite> playerUnits, DeckArea deckArea, List<FieldSprite> fields) {
		this.playerUnits = playerUnits;
		this.deckArea = deckArea;
		this.fields = fields;
		for (UnitSprite unit : playerUnits) {
			unit.setCollisionListener(this);
			unit.setUnitPositionListener(this);
		}
	}

	@Override
	public void finish() {
		this.nextPhaseListener.nextPhase(this);
	}

	@Override
	public void init(KeyListenScene scene) {
		// 背景を追加
		scene.attachChild(scene.getBaseActivity().getResourceUtil().getSprite("battle/background.png"));

		DBAdapter adapter = new DBAdapter(scene.getBaseActivity()).open();
		List<PlayerUnit> initialUnits = adapter.getBinder().getUnitList();
		// スプライト生成
		for (PlayerUnit unit : initialUnits) {
			UnitSprite uSprite = new UnitSprite(scene.getBaseActivity().getResourceUtil()
					.getSprite("unit/" + unit.getImagePath()));
			uSprite.setUnitData(unit);
			uSprite.setZIndex(3);
			uSprite.setFlippedHorizontal(true);
			uSprite.setCollisionListener(this);
			uSprite.setUnitPositionListener(this);
			playerUnits.add(uSprite);
//			scene.attachChild(uSprite);
			scene.registerTouchArea(uSprite);
			scene.setTouchAreaBindingOnActionDownEnabled(true);
		}
		// デックエリア設定
		deckArea = new DeckArea(scene);
		deckArea.setDeckUnits(playerUnits);
		// フィールド生成
		fields = new ArrayList<FieldSprite>();
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
				FieldSprite field = new FieldSprite(scene.getBaseActivity().getResourceUtil()
						.getSprite("battle/field.png"));
				field.setPosition(430 + n * 20 + i * 100, 280 - n * 65);
				field.setVisible(false);
				fields.add(field);
				scene.attachChild(field);
			}
		}
		scene.sortChildren();
	}

	/**
	 * フィールド衝突判定（ユニットドラッグ）
	 */
	@Override
	public void onCollisionAtFieldWithDown(UnitSprite unit) {
		// フィールドの可視化
		for (FieldSprite fieldSprite : fields) {
			fieldSprite.setVisible(true);
		}
	}

	/**
	 * フィールド衝突判定（ユニット移動）
	 */
	@Override
	public void onCollisionAtFieldWithMove(UnitSprite unit) {
		boolean isCollision = false;
		// フィールドとの衝突判定
		for (FieldSprite field : fields) {
			if (unit.collidesWith(field) && !isCollision) {
				// フィールドの点滅
				Effects.Flash(field, 0.3f);
				isCollision = true;
			} else {
				field.clearEntityModifiers();
				field.setAlpha(0.8f);
			}
		}
	}

	/**
	 * フィールド衝突判定（ユニットドロップ）
	 */
	@Override
	public boolean onCollisionAtFieldWithUp(UnitSprite unit) {
		// フィールドとの衝突判定
		for (FieldSprite field : fields) {
			if (unit.collidesWith(field)) {
				// フィールドに貼り付け
				float centerX = field.getX() + field.getWidth() / 2;
				float centerY = field.getY() + 40;
				unit.registerEntityModifier(new MoveModifier(1.0f, unit.getX(), centerX - unit.getWidth() / 2, unit
						.getY(), centerY - unit.getHeight()));
				unit.setState(States.PRE_FIELD);
				unit.onFieldUnitSprite(unit);
				field.setUse(true);
				field.clearEntityModifiers();
				return true;
			}
		}
		// タイル不可視
		for (FieldSprite fieldSprite : fields) {
			fieldSprite.setVisible(false);
		}
		return false;
	}

	@Override
	public void onFieldUnitSprite(UnitSprite unit) {
		// デックエリア再設定
		deckArea.setDeckUnits(playerUnits);
	}

}

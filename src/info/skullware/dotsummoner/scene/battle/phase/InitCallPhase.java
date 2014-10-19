package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.MainActivity;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.scene.battle.BattleScene;
import info.skullware.dotsummoner.scene.battle.dto.BattleSceneDto;
import info.skullware.dotsummoner.scene.battle.listener.CollisionListener;
import info.skullware.dotsummoner.scene.battle.listener.UnitPositionListener;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite.States;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

public class InitCallPhase extends AbstractPhase implements UnitPositionListener, CollisionListener {

	private BattleSceneDto dto;
	private Music bgm;
	private Sound onEnemySound;

	public InitCallPhase(BattleSceneDto dto) {
		this.dto = dto;
		for (CardSprite card : dto.getCards()) {
			card.setCollisionListener(this);
			card.setUnitPositionListener(this);
		}
	}

	@Override
	public void finish() {
		bgm.stop();
		this.nextPhaseListener.nextPhase(this);
	}

	@Override
	public void init(final BattleScene scene) {
		// 背景
		scene.attachChild(dto.getBackground());
		// カード
		for (CardSprite card : dto.getCards()) {
			card.setCollisionListener(this);
			card.setUnitPositionListener(this);
			scene.registerTouchArea(card);
			scene.setTouchAreaBindingOnActionDownEnabled(true);

		}
		// デックエリア設定
		scene.attachChild(dto.getDeckArea());
		scene.registerTouchArea(dto.getDeckArea());
		dto.getDeckArea().setDeckUnits(dto.getCards());

		// コスト表示
		Text cost = PixelMplus.getStrokeTextRegular12(scene.getBaseActivity(), "COST 000/123", 0,
				10);
		cost.setX(MainActivity.WIDTH - cost.getWidth() - 10);
		scene.attachChild(cost);

		// フィールド生成
		for (FieldSprite field : dto.getPlayerFields()) {
			scene.attachChild(field);
		}
		for (FieldSprite field : dto.getEnemyFields()) {
			scene.attachChild(field);
		}

		// サウンド
		onEnemySound = scene.getBaseActivity().getResourceUtil().getSound("battle/onEnemy.wav");
		// BGM
		bgm = scene.getBaseActivity().getResourceUtil().getMusic("battle/initcall.wav");
		bgm.setLooping(true);
		bgm.play();

		scene.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler arg0) {
				onEnemyEvent(scene, 0);
			}
		}));
	}

	private void onEnemyEvent(final Scene scene, final int index) {
		if (index >= dto.getEnemys().size()) {
			scene.sortChildren();
			scene.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler arg0) {
					onSummonEvent(scene, 0);
				}
			}));
		} else {
			// Enemy設定
			UnitSprite enemy = dto.getEnemys().get(index);
			FieldSprite field = dto.getEnemyFields().get(enemy.getPosition());

			enemy.setPosition(field.getWidth() / 2 - (enemy.getWidth() * 1.5f) / 2,
					40 - (enemy.getHeight() * 1.5f));
			enemy.setScale(1.5f);
			enemy.setZIndex(field.getZIndex() + 1);
			enemy.registerEntityModifier(new FadeInModifier(2f, new IEntityModifierListener() {

				@Override
				public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
				}

				@Override
				public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {
					onEnemyEvent(scene, index + 1);
				}
			}));
			field.attachChild(enemy);
			onEnemySound.play();

			scene.sortChildren();
		}
	}

	private void onSummonEvent(final Scene scene, final int index) {

	}

	/**
	 * フィールド衝突判定（ユニットドラッグ）
	 */
	@Override
	public void onCollisionAtFieldWithDown(CardSprite card) {
	}

	/**
	 * フィールド衝突判定（ユニット移動）
	 */
	@Override
	public void onCollisionAtFieldWithMove(CardSprite card) {
		boolean isCollision = false;
		// フィールドとの衝突判定
		for (FieldSprite field : dto.getPlayerFields()) {
			if (card.collidesWith(field) && !isCollision) {
				// フィールドの点滅
				field.flash();
				isCollision = true;
			} else {
				field.clear();
			}
		}
	}

	/**
	 * フィールド衝突判定（ユニットドロップ）
	 */
	@Override
	public boolean onCollisionAtFieldWithUp(CardSprite card) {
		// フィールドとの衝突判定
		for (FieldSprite field : dto.getPlayerFields()) {
			if (card.collidesWith(field) && field.getUnitData() == null) {
				// フィールドに貼り付け
				// card.onFieldUnitSprite(card);
				card.detachSelf();
				
				field.attachChild(card);
				card.setState(States.FIELD_AREA);
				card.setPosition(field.getWidth() / 2 - card.getWidth() / 2, 40 - card.getHeight());
				card.setZIndex(field.getZIndex() + 1);
				card.setRotation(10f);
				field.setUnitData(card.getUnitData());
				field.clear();
				
				// デックエリア再設定
				dto.getDeckArea().setDeckUnits(dto.getCards());
				return true;
			}
		}
		return false;
	}

	@Override
	public void onFieldUnitSprite(CardSprite card) {
		// デックエリア再設定
		dto.getDeckArea().setDeckUnits(dto.getCards());
	}

	@Override
	public void onCollisionAtDeckWithDown(CardSprite unit) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onCollisionAtDeckWithUp(CardSprite card) {
		// デックエリアとの衝突判定
		if (card.collidesWith(dto.getDeckArea())) {
			// フィールドに貼り付け
			FieldSprite field = (FieldSprite) card.getParent();
			field.detachChild(card);
			// デックエリア再設定
			dto.getDeckArea().setDeckUnits(dto.getCards());
			return true;
		}
		return false;
	}

	@Override
	public void onCollisionAtDeckWithMove(CardSprite unit) {
		// TODO 自動生成されたメソッド・スタブ

	}

}

package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.MainActivity;
import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.thread.AsyncTaskLoader;
import info.skullware.dotsummoner.common.thread.IAsyncCallback;
import info.skullware.dotsummoner.common.util.Effects;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.database.DBAdapter;
import info.skullware.dotsummoner.param.unit.EnemyUnit;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;
import info.skullware.dotsummoner.scene.quest.Quest;

import java.util.List;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.IModifier;

import android.view.KeyEvent;

public class LoadingScene extends KeyListenScene {

	private int questId;
	private Text loadText;
	private AnimatedSprite loadSprite;

	public LoadingScene(MultiSceneActivity baseActivity, int questId) {
		super(baseActivity);
		this.questId = questId;
		init();
	}

	@Override
	public void init() {
		final BattleSceneDto dto = new BattleSceneDto();

		loadText = PixelMplus.getTextRegular10(getBaseActivity(), "Now Loading ...", 0, 0);
		loadText.setPosition(MainActivity.WIDTH - loadText.getWidth() - 10, MainActivity.HEIGHT
				- loadText.getHeight() - 10);
		Effects.flash(loadText, 1f);
		this.attachChild(loadText);

		loadSprite = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/loading.png", 1,
				6);
		loadSprite.animate(100);
		loadSprite.setPosition(loadText.getX() - loadSprite.getWidth() - 5, loadText.getY());
		this.attachChild(loadSprite);

		IAsyncCallback callback = new IAsyncCallback() {

			@Override
			public void workToDo() {

				new DelayModifier(10, new IEntityModifierListener() {
					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
					}
				});

				// DB設定
				DBAdapter adapter = new DBAdapter(getBaseActivity()).open();
				// 背景読み込み
				dto.setBackground(getBaseActivity().getResourceUtil().getSprite(
						"battle/background.png"));
				// クエスト読み込み
				dto.setQuest(new Quest(getBaseActivity(), questId));
				// デック生成
				List<PlayerUnit> initialUnits = adapter.getBinder().getUnitList();
				for (PlayerUnit unit : initialUnits) {
					CardSprite card = new CardSprite(getBaseActivity().getResourceUtil().getSprite(
							"unit/" + unit.getImagePath().replace(".png", "_c.png")), unit);
					card.setText(getBaseActivity());
					dto.getCards().add(card);
				}
				// デックエリア
				dto.setDeckArea(new DeckArea(getBaseActivity()));
				// フィールド（プレイヤー）
				for (int i = 0; i < 3; i++) {
					for (int n = 0; n < 3; n++) {
						FieldSprite field = new FieldSprite(getBaseActivity().getResourceUtil()
								.getSprite("battle/field.png"));
						field.setPosition(430 + n * 20 + i * 100, 280 - n * 65);
						field.setZIndex((3 - n) * 10);
						dto.getPlayerFields().add(field);
					}
				}
				// フィールド（エネミー）
				for (int i = 0; i < 3; i++) {
					for (int n = 0; n < 3; n++) {
						FieldSprite field = new FieldSprite(getBaseActivity().getResourceUtil()
								.getSprite("battle/field.png"));
						field.setPosition(370 - n * 20 - i * 100 - field.getWidth(), 280 - n * 65);
						field.setZIndex((3 - n) * 10);
						field.setFlippedHorizontal(true);
						dto.getEnemyFields().add(field);
					}
				}
				// エネミー
				for (EnemyUnit unit : dto.getQuest().getEnemys()) {
					UnitSprite enemy = new UnitSprite(getBaseActivity().getResourceUtil()
							.getSprite("unit/" + unit.getImagePath()), unit);
					enemy.setPosition(unit.getPosition());
					dto.getEnemys().add(enemy);
				}
			}

			@Override
			public void onComplete() {
				finish(dto);
			}
		};

		// 非同期ロード
		new AsyncTaskLoader().execute(callback);
	}

	private void finish(BattleSceneDto dto) {
		// load開放
		this.loadText.setVisible(false);
		this.loadSprite.setVisible(false);
		// リソースを開放せずに移動
		KeyListenScene scene = new BattleScene(getBaseActivity(), dto);
		// MainSceneへ移動
		getBaseActivity().getEngine().setScene(scene);
		// 遷移管理用配列に追加
		getBaseActivity().appendScene(scene);
	}

	@Override
	public void prepareSoundAndMusic() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}

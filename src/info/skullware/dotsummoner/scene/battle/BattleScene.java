package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.database.DBAdapter;
import info.skullware.dotsummoner.param.unit.PlayerUnit;
import info.skullware.dotsummoner.scene.battle.phase.InitCallPhase;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;

import android.view.KeyEvent;

public class BattleScene extends KeyListenScene implements OnClickListener {

	public static enum States {
		// 初回召喚フェイズ
		INIT_CALL,
		// 先攻後攻判定フェイズ
		INITIATIVE,
		// 先攻攻撃フェイズ
		FIRST_PLAY,
		// 後攻攻撃フェイズ
		SECOND_PLAY,
		// 状態リセットフェイズ
		RESET_STATE,
		// 召喚フェイズ
		UNIT_CALL
	};

	private DBAdapter adapter;
	private States state;

	public BattleScene(MultiSceneActivity baseActivity) {
		super(baseActivity);
		adapter = new DBAdapter(getBaseActivity()).open();
		init();
	}

	private List<UnitSprite> playerUnits = new ArrayList<UnitSprite>();
	private DeckArea deckArea;
	private List<FieldSprite> fields;

	/*
	 * 初期処理
	 * 
	 * @see info.skullware.dotsummoner.common.KeyListenScene#init()
	 */
	@Override
	public void init() {
		// 背景を追加
		attachChild(getBaseActivity().getResourceUtil().getSprite("battle/background.png"));

		List<PlayerUnit> initialUnits = adapter.getBinder().getUnitList();
		// スプライト生成
		for (PlayerUnit unit : initialUnits) {
			UnitSprite uSprite = new UnitSprite(getBaseActivity().getResourceUtil().getSprite(
					"unit/" + unit.getImagePath()));
			uSprite.setUnitData(unit);
			uSprite.setZIndex(3);
			uSprite.setFlippedHorizontal(true);
			playerUnits.add(uSprite);
			this.attachChild(uSprite);
			this.registerTouchArea(uSprite);
			this.setTouchAreaBindingOnActionDownEnabled(true);
		}
		// デックエリア設定
		deckArea = new DeckArea(this);
		deckArea.setDeckUnits(playerUnits);
		// フィールド生成
		fields = new ArrayList<FieldSprite>();
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 3; n++) {
				FieldSprite field = new FieldSprite(getBaseActivity().getResourceUtil()
						.getSprite("battle/field.png"));
				field.setPosition(430 + n * 20 + i * 100, 280 - n * 65);
				field.setVisible(false);
				fields.add(field);
				this.attachChild(field);
			}
		}

		// UnitSprite.setTileData(this);
		this.sortChildren();

		// 初回召喚フェイズ
		this.state = States.INIT_CALL;

		InitCallPhase phase = new InitCallPhase(playerUnits, deckArea, fields);
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO 自動生成されたメソッド・スタブ

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
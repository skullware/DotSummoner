package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.scene.battle.listener.NextPhaseListener;
import info.skullware.dotsummoner.scene.battle.phase.AbstractPhase;
import info.skullware.dotsummoner.scene.battle.phase.InitCallPhase;
import info.skullware.dotsummoner.scene.battle.phase.InitiativePhase;
import info.skullware.dotsummoner.scene.battle.sprite.DeckArea;
import info.skullware.dotsummoner.scene.battle.sprite.FieldSprite;
import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;

import android.view.KeyEvent;

public class BattleScene extends KeyListenScene implements OnClickListener, NextPhaseListener {

	// public static enum States {
	// // 初回召喚フェイズ
	// INIT_CALL,
	// // 先攻後攻判定フェイズ
	// INITIATIVE,
	// // 先攻攻撃フェイズ
	// FIRST_PLAY,
	// // 後攻攻撃フェイズ
	// SECOND_PLAY,
	// // 状態リセットフェイズ
	// RESET_STATE,
	// // 召喚フェイズ
	// UNIT_CALL
	// };
	//
	// private DBAdapter adapter;
	// private States state;

	public BattleScene(MultiSceneActivity baseActivity) {
		super(baseActivity);
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
		// 初回召喚フェイズ
		InitCallPhase initCall = new InitCallPhase(playerUnits, deckArea, fields);
		initCall.setNextPhaseListener(this);
		initCall.init(this);
	}

	@Override
	public void nextPhase(AbstractPhase phase) {
		if (phase instanceof InitCallPhase) {
			// 先攻後攻判定フェイズ
			InitiativePhase initiative = new InitiativePhase();
			initiative.setNextPhaseListener(this);
		}
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
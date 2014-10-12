package info.skullware.dotsummoner.scene.battle;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.scene.battle.listener.NextPhaseListener;
import info.skullware.dotsummoner.scene.battle.phase.AbstractPhase;
import info.skullware.dotsummoner.scene.battle.phase.InitCallPhase;
import info.skullware.dotsummoner.scene.battle.phase.InitiativePhase;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;

import android.view.KeyEvent;

public class BattleScene extends KeyListenScene implements OnClickListener, NextPhaseListener {

	private BattleSceneDto dto;

	public BattleScene(MultiSceneActivity baseActivity, BattleSceneDto dto) {
		super(baseActivity);
		this.dto = dto;
		init(dto.getQuest().getQuestId());
	}

	/*
	 * 初期処理
	 * 
	 * @see info.skullware.dotsummoner.common.KeyListenScene#init()
	 */
	public void init(int questId) {
		// 初回召喚フェイズ
		InitCallPhase initCall = new InitCallPhase(dto);
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

	@Override
	public void init() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
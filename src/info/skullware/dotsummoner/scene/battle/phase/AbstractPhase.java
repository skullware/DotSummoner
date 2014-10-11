package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.scene.battle.BattleScene;
import info.skullware.dotsummoner.scene.battle.listener.NextPhaseListener;

public abstract class AbstractPhase {

	protected NextPhaseListener nextPhaseListener;

	public NextPhaseListener getNextPhaseListener() {
		return nextPhaseListener;
	}

	public void setNextPhaseListener(NextPhaseListener nextPhaseListener) {
		this.nextPhaseListener = nextPhaseListener;
	}

	public abstract void init(BattleScene scene);

	public abstract void finish();
}

package info.skullware.dotsummoner.scene.battle.phase;

import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.scene.battle.listener.NextPhaseListener;

public abstract class AbstractPhase {

	protected NextPhaseListener nextPhaseListener;

	public NextPhaseListener getNextPhaseListener() {
		return nextPhaseListener;
	}

	public void setNextPhaseListener(NextPhaseListener nextPhaseListener) {
		this.nextPhaseListener = nextPhaseListener;
	}

	public abstract void init(KeyListenScene scene);

	public abstract void finish();
}

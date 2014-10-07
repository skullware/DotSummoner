package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.scene.battle.phase.AbstractPhase;

import java.util.EventListener;

public interface NextPhaseListener extends EventListener {

	public void nextPhase(AbstractPhase phase);
}

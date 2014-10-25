package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.param.unit.PlayerUnit;

import java.util.EventListener;

public interface StatusListener extends EventListener {

	public void onVisibleStatus(PlayerUnit unit);

	public void onHiddenStatus(PlayerUnit unit);
}

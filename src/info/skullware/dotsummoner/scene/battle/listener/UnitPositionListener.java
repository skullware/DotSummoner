package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import java.util.EventListener;

/**
 * ユニットの位置変化リスナ
 * @author chibo
 *
 */
public interface UnitPositionListener extends EventListener {

	public void onFieldUnitSprite(UnitSprite unit);
}

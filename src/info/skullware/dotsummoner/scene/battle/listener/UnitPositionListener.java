package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;

import java.util.EventListener;

/**
 * ユニットの位置変化リスナ
 * @author chibo
 *
 */
public interface UnitPositionListener extends EventListener {

	public void onFieldUnitSprite(CardSprite unit);
}

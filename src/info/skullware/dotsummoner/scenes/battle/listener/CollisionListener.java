package info.skullware.dotsummoner.scenes.battle.listener;

import info.skullware.dotsummoner.scenes.battle.sprite.UnitSprite;

import java.util.EventListener;

/**
 * ユニットの衝突検出リスナ
 * @author chibo
 *
 */
public interface CollisionListener extends EventListener {

	public void onCollisionAtFieldWithUp(UnitSprite unit);
	public void onCollisionAtFieldWithMove(UnitSprite unit);
}

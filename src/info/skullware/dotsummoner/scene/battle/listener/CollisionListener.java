package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.scene.battle.sprite.UnitSprite;

import java.util.EventListener;

/**
 * ユニットの衝突検出リスナ
 * 
 * @author chibo
 *
 */
public interface CollisionListener extends EventListener {

	public void onCollisionAtFieldWithDown(UnitSprite unit);

	public boolean onCollisionAtFieldWithUp(UnitSprite unit);

	public void onCollisionAtFieldWithMove(UnitSprite unit);
}

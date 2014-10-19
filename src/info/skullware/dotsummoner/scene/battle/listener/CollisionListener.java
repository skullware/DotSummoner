package info.skullware.dotsummoner.scene.battle.listener;

import info.skullware.dotsummoner.scene.battle.sprite.CardSprite;

import java.util.EventListener;

/**
 * ユニットの衝突検出リスナ
 * 
 * @author chibo
 *
 */
public interface CollisionListener extends EventListener {

	public boolean onCollisionAtFieldWithUp(CardSprite unit);

	public void onCollisionAtFieldWithMove(CardSprite unit);
	
	public boolean onCollisionAtDeckWithUp(CardSprite unit);
	
	public void onCollisionAtDeckWithMove(CardSprite unit);
}

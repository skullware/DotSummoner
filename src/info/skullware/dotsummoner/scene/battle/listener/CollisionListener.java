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

	public boolean onCollisionUp(CardSprite card);

	public void onCollisionMove(CardSprite card);
	
	public boolean onCollisionReUp(CardSprite card);
}

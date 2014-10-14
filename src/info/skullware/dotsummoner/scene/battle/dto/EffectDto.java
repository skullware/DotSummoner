package info.skullware.dotsummoner.scene.battle.dto;

import org.andengine.entity.sprite.AnimatedSprite;


public class EffectDto {

	private AnimatedSprite summonEnemy;
	private AnimatedSprite summonPlayer;

	public AnimatedSprite getSummonEnemy() {
		return summonEnemy;
	}

	public void setSummonEnemy(AnimatedSprite summonEnemy) {
		this.summonEnemy = summonEnemy;
	}

	public AnimatedSprite getSummonPlayer() {
		return summonPlayer;
	}
	
	public void setSummonPlayer(AnimatedSprite summonPlayer) {
		this.summonPlayer = summonPlayer;
	}
}

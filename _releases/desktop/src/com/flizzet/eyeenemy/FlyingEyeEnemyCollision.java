package com.flizzet.eyeenemy;

import com.flizzet.enemies.EnemyCollision;
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.player.Player;

/**
 * Handles flying eye enemy collision.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class FlyingEyeEnemyCollision extends EnemyCollision {

	private Player player = Player.getInstance();
	private FlyingEyeEnemy enemy;

	/** Default instantiable constructor */
	public FlyingEyeEnemyCollision(FlyingEyeEnemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(float delta) {
		/* Collision with player */
		if (enemy.getBounds().overlaps(player.getBounds())) {
			if (enemy.getAi().getState() != FlyingEnemyState.DEAD) {
				player.hit();
			}
		}
	}

}

package com.flizzet.flyzombieenemy;

import com.flizzet.enemies.EnemyAi;
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.player.Player;

/**
 * AI controller for an flying zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingZombieEnemyAi extends EnemyAi {

	private FlyingZombieEnemy enemy;
	private Player player = Player.getInstance();

	private float yVel = 5;
	private float speed = 2;

	/** Default instantiable constructor */
	public FlyingZombieEnemyAi(FlyingZombieEnemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public void update(float delta) {

		if (enemy.getState() == FlyingEnemyState.CHASING_PLAYER) {
			float distanceX = 10 * ((player.getCenterX() - enemy.getX()) / 2)
					* delta;
			float distanceY = 10 * ((player.getCenterY() - enemy.getY()) / 2)
					* delta;
			if (distanceX != 0 && distanceY != 0) {
				float factor = (float) (speed / Math
						.sqrt(distanceX * distanceX + distanceY * distanceY));
				distanceX *= factor;
				distanceY *= factor;
			}
			enemy.setX(enemy.getX() + distanceX);
			enemy.setY(enemy.getY() + distanceY);
		} else if (enemy.getState() == FlyingEnemyState.DEAD) {
			yVel -= 0.5f;
			enemy.setY(enemy.getY() + yVel);
		}

		if (enemy.getY() < -100) {
			FlyingZombieEnemySpawner.getInstance().removeFromZombies(enemy);
		}

	}

}

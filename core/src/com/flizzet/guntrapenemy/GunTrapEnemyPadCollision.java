package com.flizzet.guntrapenemy;

import com.flizzet.enemies.EnemyCollision;
import com.flizzet.player.Player;

/**
 * Handles collision for the GunTrap pad.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GunTrapEnemyPadCollision extends EnemyCollision {

	private GunTrapEnemy enemy;
	private GunTrapEnemyPad pad;
	private Player player = Player.getInstance();

	/** Default instantiable constructor */
	public GunTrapEnemyPadCollision(GunTrapEnemyPad pad, GunTrapEnemy enemy) {
		this.pad = pad;
		this.enemy = enemy;
	}

	@Override
	public void update(float delta) {

		if (player.getCollisionBounds().overlaps(pad.getBounds())) {
			enemy.shoot();
		}

	}

}

package com.flizzet.flyzombieenemy;

import com.flizzet.enemies.EnemyCollision;
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.player.Player;

/**
 * Handles collision for the flying zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingZombieEnemyCollision extends EnemyCollision {

    private FlyingZombieEnemy enemy;
    private Player player = Player.getInstance();
    
    /** Default instantiable constructor */
    public FlyingZombieEnemyCollision(FlyingZombieEnemy enemy) {
	this.enemy = enemy;
    }

    @Override
    public void update(float delta) {
	
	/** Collision with player */
	if (enemy.getState() != FlyingEnemyState.DEAD) {
	    if (enemy.getCollisionBounds().overlaps(player.getCollisionBounds())) {
		player.hit();
	    }
	}
    }

}

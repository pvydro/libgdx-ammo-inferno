package com.flizzet.batenemy;

import com.flizzet.enemies.EnemyCollision;
import com.flizzet.enemies.FlyingEnemyState;
import com.flizzet.player.Player;

/**
 * Handles collision for the flying bat enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingBatEnemyCollision extends EnemyCollision {

    private FlyingBatEnemy enemy;
    private Player player = Player.getInstance();
    
    /** Default instantiable constructor */
    public FlyingBatEnemyCollision(FlyingBatEnemy enemy) {
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

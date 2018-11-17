package com.flizzet.spikeenemy;

import com.flizzet.enemies.EnemyCollision;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;

/**
 * Handles collision for the spike enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class SpikeEnemyCollision extends EnemyCollision {

    private SpikeEnemy enemy;
    
    /** Default instantiable constructor */
    public SpikeEnemyCollision(SpikeEnemy enemy) {
	this.enemy = enemy;
    }

    @Override
    public void update(float delta) {
	if (enemy.getCollisionBounds().overlaps(Player.getInstance().getCollisionBounds())) {
	    Player.getInstance().hit();
	    if (CurrentSettings.getInstance().blood) {
		GameManager.getInstance().particleFunctions.addBloodParticle(Player.getInstance().getCenterX(), Player.getInstance().getCenterY(), 
			CurrentSettings.getInstance().bloodAmount);
	    }
	}
    }

}

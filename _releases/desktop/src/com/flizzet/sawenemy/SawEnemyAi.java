package com.flizzet.sawenemy;

import com.flizzet.enemies.EnemyAi;

/**
 * AI controller for the saw enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class SawEnemyAi extends EnemyAi {
    
    private SawEnemy enemy;
    
    private int direction = 0;
    private float speed = 1;

    /** Default instantiable constructor */
    public SawEnemyAi(SawEnemy saw) {
	this.enemy = saw;
    }

    @Override
    public void update(float delta) {
	if (direction == 0) {
	    enemy.setX(enemy.getX() - speed);
	    enemy.setRotation(enemy.getRotation() + speed * 10);
	} else if (direction == 1) {
	    enemy.setX(enemy.getX() + speed);
	    enemy.setRotation(enemy.getRotation() - speed * 10);
	}
	
	if (enemy.getCenterX() < enemy.getPlatform().getX()) {
	    direction = 1;
	}
	if (enemy.getCenterX() > enemy.getPlatform().getX() + enemy.getPlatform().getWidth()) {
	    direction = 0;
	}
    }

}

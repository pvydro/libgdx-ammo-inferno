package com.flizzet.sawenemy;

import java.util.Random;

/**
 * Randomly spawns a rolling saw enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SawEnemySpawner {

    private Random random = new Random();
    private int chance = 0;
    
    /** Default instantiable constructor */
    public SawEnemySpawner() {
	chance = random.nextInt(2);
    }
    
    /** Returns whether a zombie will spawn or not */
    public boolean isSpawning() {
	if (chance <= 0) {
	    return true;
	} else {
	    return false;
	}
    }

}

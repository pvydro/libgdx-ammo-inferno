package com.flizzet.map;

import java.util.Random;

/**
 * Spawns platform lamps randomly.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PlatformLampSpawner {

    private Random random = new Random();
    private int chance = 0;

    /** Default instantiable constructor */
    public PlatformLampSpawner() {
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

package com.flizzet.map;

import java.util.Random;

/**
 * Spawns treasure chests on the platform randomly.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformChestSpawner {

    private Random random = new Random();
    private int chance = 0;

    /** Default instantiable constructor */
    public PlatformChestSpawner() {}
    
    /** Returns whether a zombie will spawn or not */
    public boolean isSpawning() {
	chance = random.nextInt(25);
	if (chance <= 0) {
	    return true;
	} else {
	    return false;
	}
    }

}

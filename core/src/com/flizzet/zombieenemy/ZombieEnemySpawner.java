package com.flizzet.zombieenemy;

import java.util.Random;

/**
 * Spawns zombies on platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ZombieEnemySpawner {

	private Random random = new Random();
	private int chance = 0;

	/** Default instantiable constructor */
	public ZombieEnemySpawner() {
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

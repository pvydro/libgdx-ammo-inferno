package com.flizzet.spikeenemy;

import java.util.Random;

/**
 * Spawns spike enemies.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SpikeEnemySpawner {

	private Random random = new Random();
	private int chance = 0;

	/** Default instantiable constructor */
	public SpikeEnemySpawner() {
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

package com.flizzet.dink;

import com.flizzet.interfaces.Updatable;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;

/**
 * Handles collision for the Dink upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also Dink
 */
class DinkCollision implements Updatable {

	private Dink dink;

	/** Default instantiable constructor */
	public DinkCollision(Dink dink) {
		this.dink = dink;
	}

	@Override
	public void update(float delta) {
		for (Platform p : PlatformGenerator.getInstance().getPlatforms()) {
			if (p.getZombieEnemy() != null) {
				if (dink.getBounds()
						.overlaps(p.getZombieEnemy().getCollisionBounds())) {
					p.getZombieEnemy().hit();
				}
			}
		}
	}

}

package com.flizzet.zombieenemy;

import com.flizzet.entities.Entity;
import com.flizzet.ingamegui.HealthBar;

/**
 * Zombie health bar that appears above the zombie head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ZombieHealthBar extends HealthBar {

    /** Default instantiable constructor */
    public ZombieHealthBar(Entity target) {
	super(target);
    }

}

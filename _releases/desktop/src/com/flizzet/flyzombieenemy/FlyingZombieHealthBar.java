package com.flizzet.flyzombieenemy;

import com.flizzet.ingamegui.HealthBar;

/**
 * Concrete health bar for the flying zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingZombieHealthBar extends HealthBar {

    /** Default instantiable constructor */
    public FlyingZombieHealthBar(FlyingZombieEnemy zombie) {
	super(zombie);
    }

}

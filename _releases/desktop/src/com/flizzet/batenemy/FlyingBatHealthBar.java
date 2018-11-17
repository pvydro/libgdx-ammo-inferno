package com.flizzet.batenemy;

import com.flizzet.ingamegui.HealthBar;

/**
 * Concrete health bar for the flying bat enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class FlyingBatHealthBar extends HealthBar {

    /** Default instantiable constructor */
    public FlyingBatHealthBar(FlyingBatEnemy bat) {
	super(bat);
    }

}

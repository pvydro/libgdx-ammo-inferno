package com.flizzet.player;

import com.flizzet.entities.Entity;
import com.flizzet.ingamegui.HealthBar;

/**
 * Player health that appears above the player head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PlayerHealthBar extends HealthBar {
    
    /** Default instantiable constructor */
    public PlayerHealthBar(Entity target) {
	super(target);
    }

}

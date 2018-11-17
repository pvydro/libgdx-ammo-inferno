package com.flizzet.score;

import com.flizzet.interfaces.Updatable;
import com.flizzet.player.Player;
import com.flizzet.player.PlayerState;

/**
 * Handles collision for the treasure chest object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TreasureChestCollision implements Updatable {

    private TreasureChest chest;
    
    /** Default instantiable constructor */
    public TreasureChestCollision(TreasureChest chest) {
	this.chest = chest;
    }

    @Override
    public void update(float delta) {
	
	/* Player collision */
	if (Player.getInstance().getBounds().overlaps(chest.getBounds())) {
	    if (!chest.isOpened()
		    && Player.getInstance().getState() != PlayerState.DYING
		    && Player.getInstance().getState() != PlayerState.DEAD) {
		chest.open();
	    }
	}
	
    }

}

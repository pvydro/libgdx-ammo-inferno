package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete dink upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DinkUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public DinkUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/dink.png", Texture.class),
		"dink", 400);
	
	this.description = "follows player and\nkills zombies";
    }

}

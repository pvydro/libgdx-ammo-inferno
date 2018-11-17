package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Lava Boots upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LavaBootsUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public LavaBootsUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/lavaBoots.png", Texture.class),
		"lava boots", 500);
	
	this.description = "bounce off lava";
    }

}

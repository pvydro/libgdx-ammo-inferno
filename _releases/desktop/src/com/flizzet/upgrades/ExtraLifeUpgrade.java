package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Desc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ExtraLifeUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public ExtraLifeUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/extraLife.png", Texture.class),
		"extra life", 600);
	
	this.description = "one extra life";
    }

}

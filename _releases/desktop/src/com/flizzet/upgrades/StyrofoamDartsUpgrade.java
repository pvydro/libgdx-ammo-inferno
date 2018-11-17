package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Styrofoam Darts upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StyrofoamDartsUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public StyrofoamDartsUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/styroDarts.png", Texture.class),
		"styro darts", 100);
	this.description = "bullets ricochet once\noff platforms";
    }

}

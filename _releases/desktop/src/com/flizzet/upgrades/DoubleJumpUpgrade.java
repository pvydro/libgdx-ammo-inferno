package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete double jump upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DoubleJumpUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public DoubleJumpUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/doubleJump.png", Texture.class),
		"double jump", 400);
	
	this.description = "ability to jump twice";
    }

}

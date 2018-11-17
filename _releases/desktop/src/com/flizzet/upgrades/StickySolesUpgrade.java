package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Sticky Soles upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StickySolesUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public StickySolesUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/stickySoles.png", Texture.class),
		"sticky soles", 200);
	
	this.description = "recoil doesn't affect\nmovement";
    }

}

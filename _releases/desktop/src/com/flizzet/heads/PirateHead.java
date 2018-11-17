package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete pirate head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PirateHead extends Head {

    /** Default instantiable constructor */
    public PirateHead() {
	super(GameManager.getInstance().assetManager.get("player/head/pirate.png", Texture.class),
		"pirate");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/pirate.png");
    }

}

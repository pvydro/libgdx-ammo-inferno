package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete astronaut head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AstronautHead extends Head {

    /** Default instantiable constructor */
    public AstronautHead() {
	super(GameManager.getInstance().assetManager.get("player/head/astronaut.png", Texture.class),
		"astronaut");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/astronaut.png");
    }

}

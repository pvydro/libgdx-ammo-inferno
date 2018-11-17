package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete firefighter head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FirefighterHead extends Head {

    /** Default instantiable constructor */
    public FirefighterHead() {
	super(GameManager.getInstance().assetManager.get("player/head/firefighter.png", Texture.class),
		"firefighter");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/firefighter.png");
    }

}

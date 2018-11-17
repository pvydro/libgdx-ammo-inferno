package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete angel head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AngelHead extends Head {

    /** Default instantiable constructor */
    public AngelHead() {
	super(GameManager.getInstance().assetManager.get("player/head/angel.png", Texture.class),
		"angel");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/angel.png");
    }

}

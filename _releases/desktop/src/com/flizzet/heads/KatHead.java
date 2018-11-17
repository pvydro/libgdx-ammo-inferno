package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete Kat head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class KatHead extends Head {

    /** Default instantiable constructor */
    public KatHead() {
	super(GameManager.getInstance().assetManager.get("player/head/kat.png", Texture.class),
		"kat");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/kat.png");
    }

}

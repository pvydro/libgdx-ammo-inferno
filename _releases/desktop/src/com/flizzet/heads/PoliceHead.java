package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete police head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PoliceHead extends Head {

    /** Default instantiable constructor */
    public PoliceHead() {
	super(GameManager.getInstance().assetManager.get("player/head/police.png", Texture.class),
		"police");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/police.png");
    }

}

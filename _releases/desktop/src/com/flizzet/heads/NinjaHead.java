package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete ninja head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class NinjaHead extends Head {

    /** Default instantiable constructor */
    public NinjaHead() {
	super(GameManager.getInstance().assetManager.get("player/head/ninja.png", Texture.class),
		"ninja");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/ninja.png");
    }

}

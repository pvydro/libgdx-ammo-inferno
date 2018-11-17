package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete eskimo head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EskimoHead extends Head {
    /** Default instantiable constructor */
    public EskimoHead() {
	super(GameManager.getInstance().assetManager.get("player/head/eskimo.png", Texture.class),
		"eskimo");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/eskimo.png");
    }

}

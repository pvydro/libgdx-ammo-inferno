package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete nario head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class NarioHead extends Head {

    /** Default instantiable constructor */
    public NarioHead() {
	super(GameManager.getInstance().assetManager.get("player/head/nario.png", Texture.class),
		"nario");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/nario.png");
    }

}

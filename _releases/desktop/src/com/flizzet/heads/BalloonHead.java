package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete balloon head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BalloonHead extends Head {

    /** Default instantiable constructor */
    public BalloonHead() {
	super(GameManager.getInstance().assetManager.get("player/head/balloon.png", Texture.class),
		"balloon");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/balloon.png");
    }

}

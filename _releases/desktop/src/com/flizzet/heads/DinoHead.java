package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete dino head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DinoHead extends Head {

    /** Default instantiable constructor */
    public DinoHead() {
	super(GameManager.getInstance().assetManager.get("player/head/dino.png", Texture.class),
		"dino");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/dino.png");
    }

}

package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete skeleton head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SkeletonHead extends Head {

    /** Default instantiable constructor */
    public SkeletonHead() {
	super(GameManager.getInstance().assetManager.get("player/head/skeleton.png", Texture.class),
		"skeleton");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/skeleton.png");
    }

}

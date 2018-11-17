package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete elvis head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ElvisHead extends Head {

	/** Default instantiable constructor */
	public ElvisHead() {
		super(GameManager.getInstance().assetManager
				.get("player/head/elvis.png", Texture.class), "elvis");
		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/headShop/icons/elvis.png");
	}

}

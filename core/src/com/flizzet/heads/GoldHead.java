package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete gold head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GoldHead extends Head {

	/** Default instantiable constructor */
	public GoldHead() {
		super(GameManager.getInstance().assetManager.get("player/head/gold.png",
				Texture.class), "gold");
		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/headShop/icons/gold.png");
	}

}

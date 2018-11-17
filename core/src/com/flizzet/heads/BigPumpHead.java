package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete big pump head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BigPumpHead extends Head {

	/** Default instantiable constructor */
	public BigPumpHead() {
		super(GameManager.getInstance().assetManager
				.get("player/head/bigPump.png", Texture.class), "big pump");
		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/headShop/icons/bigPump.png");
	}

}

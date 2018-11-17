package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete racer head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RacerHead extends Head {

	/** Default instantiable constructor */
	public RacerHead() {
		super(GameManager.getInstance().assetManager
				.get("player/head/racer.png", Texture.class), "racer");
		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/headShop/icons/racer.png");
	}

}

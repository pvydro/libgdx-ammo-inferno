package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete miner head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MinerHead extends Head {

	/** Default instantiable constructor */
	public MinerHead() {
		super(GameManager.getInstance().assetManager
				.get("player/head/miner.png", Texture.class), "miner");
		this.iconImage = GameManager.getInstance().assetManager
				.get("gui/headShop/icons/miner.png");
		this.bought = true;
	}

}

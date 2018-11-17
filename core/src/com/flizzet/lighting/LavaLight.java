package com.flizzet.lighting;

import com.flizzet.main.GameManager;
import com.flizzet.map.Lava;

/**
 * Concrete light for lava.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LavaLight extends Light {

	/** Default instantiable constructor */
	public LavaLight(Lava target) {
		this.target = target;
		this.lightOverlay = GameManager.getInstance().assetManager
				.get("lights/lavaLight.png");
		adjustBoundsToImage();
	}

}

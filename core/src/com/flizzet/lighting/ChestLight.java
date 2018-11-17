package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.score.TreasureChest;

/**
 * Light that follows iron chests.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ChestLight extends Light {

	/** Default instantiable constructor */
	public ChestLight(TreasureChest target) {
		this.target = target;
		this.jitterDimension = true;
		this.jitterVertically = true;
		this.jitterPositionAmt = 3;

		this.lightOverlay = GameManager.getInstance().assetManager
				.get("lights/chestLight.png", Texture.class);

		adjustBoundsToImage();
	}

}

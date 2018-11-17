package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.particles.BugParticle;

/**
 * Light that follows bugs around.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BugLight extends Light {

	/** Default instantiable constructor */
	public BugLight(BugParticle target) {
		this.target = target;
		this.lightOverlay = GameManager.getInstance().assetManager
				.get("lights/bugLight.png", Texture.class);
		this.adjustBoundsToImage();
	}

}

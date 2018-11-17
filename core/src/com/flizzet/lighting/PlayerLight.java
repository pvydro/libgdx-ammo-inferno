package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;

/**
 * Concrete light to follow the player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerLight extends Light {

	public PlayerLight(Player target) {
		this.target = target;
		this.lightOverlay = GameManager.getInstance().assetManager
				.get("lights/playerLight.png", Texture.class);
		this.adjustBoundsToImage();
	}

}

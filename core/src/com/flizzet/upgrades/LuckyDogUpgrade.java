package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Lucky Dog upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LuckyDogUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public LuckyDogUpgrade() {
		super(GameManager.getInstance().assetManager
				.get("upgrades/icons/luckyDog.png", Texture.class), "lucky dog",
				800);

		this.description = "higher chance of\nchests spawning";
	}

}

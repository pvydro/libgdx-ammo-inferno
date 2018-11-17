package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Early Bird upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EarlyBirdUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public EarlyBirdUpgrade() {
		super(GameManager.getInstance().assetManager
				.get("upgrades/icons/earlyBird.png", Texture.class),
				"early bird", 300);

		this.description = "get a head start and\nspawn in stage 2";
	}

}

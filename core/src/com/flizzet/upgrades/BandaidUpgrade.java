package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Bandaid upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BandaidUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public BandaidUpgrade() {
		super(GameManager.getInstance().assetManager.get(
				"upgrades/icons/bandaid.png", Texture.class), "bandaid", 200);

		this.description = "get extra time\nbetween enemy attacks";
	}

}

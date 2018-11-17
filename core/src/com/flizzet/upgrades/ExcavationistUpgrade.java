package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete excavationist upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ExcavationistUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public ExcavationistUpgrade() {
		super(GameManager.getInstance().assetManager
				.get("upgrades/icons/excavationist.png", Texture.class),
				"excavationist", 800);

		this.description = "double loot from\nchests";
	}

}

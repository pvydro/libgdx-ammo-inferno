package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete trap jammer upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TrapJammerUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public TrapJammerUpgrade() {
		super(GameManager.getInstance().assetManager
				.get("upgrades/icons/trapJammer.png", Texture.class),
				"trap jammer", 500);

		this.description = "disables gun traps";
	}

}

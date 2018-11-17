package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete Swift upgrade class.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SwiftUpgrade extends Upgrade {

	/** Default instantiable constructor */
	public SwiftUpgrade() {
		super(GameManager.getInstance().assetManager
				.get("upgrades/icons/swift.png", Texture.class), "swift", 300);

		this.description = "shorter enemy\nselection cooldown";
	}

}

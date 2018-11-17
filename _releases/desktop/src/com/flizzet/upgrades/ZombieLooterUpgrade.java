package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Concrete zombie looter upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ZombieLooterUpgrade extends Upgrade {

    /** Default instantiable constructor */
    public ZombieLooterUpgrade() {
	super(GameManager.getInstance().assetManager.get("upgrades/icons/zombieLooter.png", Texture.class),
		"zombie looter", 400);
	
	this.description = "double coins from\nzombies";
    }

}

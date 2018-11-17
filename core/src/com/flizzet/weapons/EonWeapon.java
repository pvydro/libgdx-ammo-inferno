package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Eon concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EonWeapon extends Weapon {

	/** Default instantiable constructor */
	public EonWeapon() {

		this.setImage(GameManager.getInstance().assetManager
				.get("weapons/eon.png", Texture.class));
		this.setImageNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/eon.png", Texture.class));
		this.setImageGold(GameManager.getInstance().assetManager
				.get("weapons/gold/eon.png", Texture.class));
		this.setImageGoldNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/gold/eon.png", Texture.class));
		this.topFireRate = 12;
		this.muzzleLength = 17;
		this.muzzleHeight = -8;
		this.recoilAmt = 8;

		this.name = "eon";
		this.price = 1000;
		this.killsToGold = 2800;

	}

}

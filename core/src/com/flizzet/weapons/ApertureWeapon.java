package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Aperture concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ApertureWeapon extends Weapon {

	/** Default instantiable constructor */
	public ApertureWeapon() {

		this.setImage(GameManager.getInstance().assetManager
				.get("weapons/aperture.png", Texture.class));
		this.setImageNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/aperture.png", Texture.class));
		this.setImageGold(GameManager.getInstance().assetManager
				.get("weapons/gold/aperture.png", Texture.class));
		this.setImageGoldNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/gold/aperture.png", Texture.class));
		this.topFireRate = 9;
		this.muzzleLength = 17;
		this.muzzleHeight = -8;
		this.recoilAmt = 6;

		this.name = "aperture";
		this.price = 1200;
		this.killsToGold = 3100;

	}

}

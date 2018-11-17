package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Rift concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RiftWeapon extends Weapon {

	/** Default instantiable constructor */
	public RiftWeapon() {

		this.setImage(GameManager.getInstance().assetManager
				.get("weapons/rift.png", Texture.class));
		this.setImageNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/rift.png", Texture.class));
		this.setImageGold(GameManager.getInstance().assetManager
				.get("weapons/gold/rift.png", Texture.class));
		this.setImageGoldNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/gold/rift.png", Texture.class));
		this.topFireRate = 10;
		this.muzzleLength = 17;
		this.muzzleHeight = -8;
		this.recoilAmt = 5;

		this.name = "rift";
		this.price = 1150;
		this.killsToGold = 3000;

	}

}

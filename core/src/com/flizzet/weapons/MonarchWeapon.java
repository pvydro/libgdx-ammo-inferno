package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Monarch concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MonarchWeapon extends Weapon {

	/** Default instantiable constructor */
	public MonarchWeapon() {

		this.setImage(GameManager.getInstance().assetManager
				.get("weapons/monarch.png", Texture.class));
		this.setImageNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/monarch.png", Texture.class));
		this.setImageGold(GameManager.getInstance().assetManager
				.get("weapons/gold/monarch.png", Texture.class));
		this.setImageGoldNoHand(GameManager.getInstance().assetManager
				.get("weapons/noHand/gold/monarch.png", Texture.class));
		this.topFireRate = 14;
		this.muzzleLength = 17;
		this.muzzleHeight = -8;
		this.recoilAmt = 12;

		this.name = "monarch";
		this.price = 750;
		this.killsToGold = 2700;

	}

}

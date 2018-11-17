package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Ash concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AshWeapon extends Weapon {

    /** Default instantiable constructor */
    public AshWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/ash.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/ash.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/ash.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/ash.png", Texture.class));
	this.topFireRate = 16;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 13;
	
	this.name = "ash";
	this.price = 500;
	this.killsToGold = 2600;
	
    }

}

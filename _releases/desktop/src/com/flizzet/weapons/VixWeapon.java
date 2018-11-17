package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Vix concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class VixWeapon extends Weapon {

    /** Default instantiable constructor */
    public VixWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/vix.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/vix.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/vix.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/vix.png", Texture.class));
	this.topFireRate = 8;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 7;
	
	this.name = "vix";
	this.price = 1300;
	this.killsToGold = 3200;
	
    }

}

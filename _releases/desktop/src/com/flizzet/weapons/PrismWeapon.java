package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * GoodGame concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PrismWeapon extends Weapon {

    /** Default instantiable constructor */
    public PrismWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/prism.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/prism.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/prism.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/prism.png", Texture.class));
	this.topFireRate = 17;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 8;
	
	this.name = "prism";
	this.price = 200;
	this.killsToGold = 2500;
	
    }

}

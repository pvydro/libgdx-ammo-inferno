package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Manta concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MantaWeapon extends Weapon {

    /** Default instantiable constructor */
    public MantaWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/manta.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/manta.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/manta.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/manta.png", Texture.class));
	this.topFireRate = 7;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 5;
	
	this.name = "manta";
	this.price = 1400;
	this.killsToGold = 3500;
	
    }

}

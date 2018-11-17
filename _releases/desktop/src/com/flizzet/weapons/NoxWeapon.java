package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Nox concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class NoxWeapon extends Weapon {

    /** Default instantiable constructor */
    public NoxWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/nox.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/nox.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/nox.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/nox.png", Texture.class));
	this.topFireRate = 18;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 12;
	
	this.bought = true;
	this.name = "nox";
	this.price = 0;
	this.killsToGold = 2450;
	
    }

}

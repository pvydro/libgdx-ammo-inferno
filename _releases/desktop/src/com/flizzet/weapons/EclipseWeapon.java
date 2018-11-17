package com.flizzet.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Eclipse concrete weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EclipseWeapon extends Weapon {

    /** Default instantiable constructor */
    public EclipseWeapon() {
	
	this.setImage(GameManager.getInstance().assetManager.get("weapons/eclipse.png", Texture.class));
	this.setImageNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/eclipse.png", Texture.class));
	this.setImageGold(GameManager.getInstance().assetManager.get("weapons/gold/eclipse.png", Texture.class));
	this.setImageGoldNoHand(GameManager.getInstance().assetManager.get("weapons/noHand/gold/eclipse.png", Texture.class));
	this.topFireRate = 11;
	this.muzzleLength = 17;
	this.muzzleHeight = -8;
	this.recoilAmt = 7;
	
	this.name = "eclipse";
	this.price = 1100;
	this.killsToGold = 2900;
	
    }

}

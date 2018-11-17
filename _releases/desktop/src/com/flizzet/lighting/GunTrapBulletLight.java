package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.projectiles.GunTrapBullet;

/**
 * Concrete light to follow the gun trap bullet.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GunTrapBulletLight extends Light {

    /** Default instantiable constructor */
    public GunTrapBulletLight(GunTrapBullet target) {
	this.target = target;
	this.lightOverlay = GameManager.getInstance().assetManager.get("lights/bulletLight.png", Texture.class);
	this.adjustBoundsToImage();
    }

}

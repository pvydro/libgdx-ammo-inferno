package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.projectiles.Bullet;

/**
 * Concrete light to follow the bullet.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BulletLight extends Light {

    public BulletLight(Bullet target) {
	this.target = target;
	this.lightOverlay = GameManager.getInstance().assetManager.get("lights/bulletLight.png", Texture.class);
	this.adjustBoundsToImage();
    }

}

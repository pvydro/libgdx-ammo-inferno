package com.flizzet.lighting;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformLamp;

/**
 * Concrete platform light to show on platform lamps.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformLight extends Light {

    /** Default instantiable constructor */
    public PlatformLight(PlatformLamp target) {
	this.target = target;
	this.jitterPosition = true;
	this.jitterHorizontally = true;
	this.jitterVertically = true;
	this.jitterPositionAmt = 3;
	
	/* Randomly place a light shine */
	if (new Random().nextInt(2) == 1) {
	    this.lightOverlay = GameManager.getInstance().assetManager.get("lights/platformLight.png", Texture.class);
	} else {
	    this.lightOverlay = GameManager.getInstance().assetManager.get("lights/platformLightLine.png", Texture.class);
	}
	adjustBoundsToImage();
    }

}

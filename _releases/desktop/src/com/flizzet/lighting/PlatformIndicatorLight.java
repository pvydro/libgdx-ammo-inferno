package com.flizzet.lighting;

import com.flizzet.ingamegui.PlatformIndicator;
import com.flizzet.main.GameManager;

/**
 * Concrete platform indicator light.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformIndicatorLight extends Light {

    /** Default instantiable constructor */
    public PlatformIndicatorLight(PlatformIndicator target) {
	this.target = target;
	
	this.lightOverlay = GameManager.getInstance().assetManager.get("lights/platformIndicatorLight.png");
	adjustBoundsToImage();
    }

}

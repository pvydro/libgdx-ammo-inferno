package com.flizzet.lighting;

import com.flizzet.main.GameManager;
import com.flizzet.weaponshop.ScrollView;

/**
 * Concrete light for the weapon view.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponViewLight extends Light {

    /** Default instantiable constructor */
    public WeaponViewLight(ScrollView guiTarget) {
	this.guiTarget = guiTarget;
	this.lightOverlay = GameManager.getInstance().assetManager.get("lights/weaponViewLight.png");
	adjustBoundsToImage();
    }
    
}

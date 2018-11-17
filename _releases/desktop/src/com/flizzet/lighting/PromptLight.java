package com.flizzet.lighting;

import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete prompt light.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PromptLight extends Light {

    /** Default instantiable constructor */
    public PromptLight(GuiComponent target) {
	this.guiTarget = target;
	this.lightOverlay = GameManager.getInstance().assetManager.get("lights/promptLight.png");
	this.adjustBoundsToImage();
    }

}

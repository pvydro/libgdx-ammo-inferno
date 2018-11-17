package com.flizzet.lighting;

import com.flizzet.loadingmenu.LoadingMenu;
import com.flizzet.main.GameManager;

/**
 * Concrete loading bar light.
 *
 * @author flizzet (2017)
 * @version 1.0
 */
public class LoadingBarLight extends Light {

	/** Default instantiable constructor */
	public LoadingBarLight(LoadingMenu menu) {
		this.guiTarget = menu;
		this.lightOverlay = GameManager.getInstance().assetManager
				.get("lights/loadingBarLight.png");
		this.adjustBoundsToImage();
	}
}

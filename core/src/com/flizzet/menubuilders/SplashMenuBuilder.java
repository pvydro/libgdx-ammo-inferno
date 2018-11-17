package com.flizzet.menubuilders;

import com.flizzet.guicomponents.SplashBackgroundComponent;
import com.flizzet.guicomponents.SplashTitleComponent;
import com.flizzet.main.GameManager;

/**
 * Builds splash menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SplashMenuBuilder extends MenuBuilder {

	private SplashBackgroundComponent background;
	private SplashTitleComponent title;

	/** Default instantiable constructor */
	public SplashMenuBuilder() {
	}

	@Override
	public void buildMenu() {

		float centerX = GameManager.getInstance().getCamera().getWidth() / 2;
		float centerY = GameManager.getInstance().getCamera().getHeight() / 2;

		/*
		 * Setting up background (Automatically sets position inside of
		 * SplashBackgroundComponent class This is because it's a background and
		 * it's not meant to be in the top corner or something
		 */
		background = new SplashBackgroundComponent(0, 0);

		/* Setting up title */
		title = new SplashTitleComponent(0, 2000);
		title.setX(centerX - (title.getWidth() / 2));
		title.setTargetX(centerX - (title.getWidth() / 2));
		title.setTargetY(centerY - (title.getHeight() / 2));

		/* Adding all gui components to the GuiContainer */
		GameManager.getInstance().guiContainer.addToGui(background);
		GameManager.getInstance().guiContainer.addToGui(title);
	}

}

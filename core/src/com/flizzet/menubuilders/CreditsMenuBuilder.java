package com.flizzet.menubuilders;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.creditsmenu.CreditsView;
import com.flizzet.main.GameManager;

/**
 * Menu builder for the credits menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also CreditsMenu
 */
public class CreditsMenuBuilder extends MenuBuilder {

	private BackgroundComponent background;
	private CreditsView view;

	/** Default instantiable constructor */
	public CreditsMenuBuilder() {
	}

	@Override
	public void buildMenu() {
		background = new BackgroundComponent();
		view = new CreditsView();

		/* Position view */
		view.setY(MainCamera.getInstance().getHeight() - 50);

		GameManager.getInstance().guiContainer.addToGui(background);
		GameManager.getInstance().guiContainer.addToGui(view);
	}

}

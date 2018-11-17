package com.flizzet.generalsettingsmenu;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.deathmenu.DeathMenu;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Back button for the death menu section of the gui.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class BackButton extends ButtonComponent {

	/** Default instantiable constructor */
	public BackButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/backButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/backButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager.get(
				"gui/deathMenu/shopSelection/backButtonPushed.png",
				Texture.class));
	}

	@Override
	public void triggered() {
		GameManager.getInstance().guiContainer.clear();
		GameManager.getInstance().stateManager
				.enterState(GameManager.getInstance().stateId_settings);
		DeathMenu.getInstance().disable();
	}

}

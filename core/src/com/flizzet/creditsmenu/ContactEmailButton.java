package com.flizzet.creditsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Email button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ContactEmailButton extends ButtonComponent {

	/** Default instantiable constructor */
	public ContactEmailButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/creditsMenu/contactEmailButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/creditsMenu/contactEmailButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager.get(
				"gui/creditsMenu/contactEmailButtonPushed.png", Texture.class));
	}

	@Override
	public void triggered() {
		Gdx.net.openURI("mailto:pedro@flizzet.com?subject=[SCJ]");
	}

}

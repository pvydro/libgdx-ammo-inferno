package com.flizzet.creditsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Contact instagram button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ContactInstaButton extends ButtonComponent {

    /** Default instantiable constructor */
    public ContactInstaButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/creditsMenu/contactInstaButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/creditsMenu/contactInstaButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/creditsMenu/contactInstaButtonPushed.png", Texture.class));
    }

    @Override
    public void triggered() {
	Gdx.net.openURI("https://www.instagram.com/");
    }
    
}

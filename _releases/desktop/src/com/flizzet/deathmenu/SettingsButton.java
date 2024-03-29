package com.flizzet.deathmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete settings button for the DeathMenu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also DeathMenu
 */
public class SettingsButton extends ButtonComponent {
    
    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/deathMenu/buttonShine.png");

    /** Default instantiable constructor */
    public SettingsButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/deathMenu/settingsButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/deathMenu/settingsButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/deathMenu/settingsButtonPushed.png", Texture.class));
	
	this.subtext = "settings";
    }
    
    @Override
    public void render(SpriteBatch batch) {
	super.render(batch);
	if (isPushed()) {
	    batch.draw(shineImage, bounds.x + 4, subtextY + 1 + 4, bounds.width - 8, bounds.height - 8);
	} else {
	    batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width, bounds.height);
	}
    }
    
    @Override
    public void triggered() {
	GameManager.getInstance().stateManager.enterState(GameManager.getInstance().stateId_settings);
    }

}

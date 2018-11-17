package com.flizzet.settingsmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete effects settings button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EffectsButton extends ButtonComponent {

    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/deathMenu/shopSelection/buttonShine.png");
    
    /** Default instantiable constructor */
    public EffectsButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/settingsMenu/categories/effectsButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/settingsMenu/categories/effectsButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/settingsMenu/categories/effectsButtonPushed.png", Texture.class));
	
	this.subtext = "effects";
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
	GameManager.getInstance().stateManager.enterState(GameManager.getInstance().stateId_effectsSettings);
    }
    
}

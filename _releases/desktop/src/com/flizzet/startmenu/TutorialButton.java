package com.flizzet.startmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.RatePrompt;
import com.flizzet.guicomponents.RewardPrompt;
import com.flizzet.main.GameManager;
import com.flizzet.utils.FontUtils;

/**
 * Concrete tutorial button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TutorialButton extends ButtonComponent {

    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/startMenu/buttonShine.png");
    
    /** Default instantiable constructor */
    public TutorialButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/startMenu/tutorialButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/startMenu/tutorialButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/startMenu/tutorialButtonPushed.png", Texture.class));

	this.subtextFont = FontUtils.UPHEAVAL_70;
	this.subtextFont.setUseIntegerPositions(false);
	this.subtext = "tutorial";
    }
    
    @Override
    public void render(SpriteBatch batch) {
	super.render(batch);
	if (isPushed()) {
	    batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2, bounds.width - 4, bounds.height - 4);
	} else {
	    batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width, bounds.height);
	}
    }
    
    @Override
    public void triggered() {
	/** Suppress if a daily reward is shown */
	for (GuiComponent g : GameManager.getInstance().guiContainer.getGuiComponents()) {
    	    if (g instanceof RewardPrompt || g instanceof RatePrompt) {
    		return;
    	    }
    	}
	
	GameManager.getInstance().stateManager.enterState(GameManager.getInstance().stateId_tutorial);
    }

}

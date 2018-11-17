package com.flizzet.upgradeshop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete shop button for the DeathMenu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also DeathMenu
 */
class PromptCancelButton extends ButtonComponent {

    private UpgradeScrollView scrollView;
    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/prompt/buttonShine.png");
    
    /** Default instantiable constructor */
    public PromptCancelButton(UpgradeScrollView scrollView) {
	super(0, 0);
	
	this.scrollView = scrollView;
	this.setImage(GameManager.getInstance().assetManager.get("gui/prompt/cancelButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/prompt/cancelButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/prompt/cancelButtonPushed.png", Texture.class));
    }
    
    @Override
    public void render(SpriteBatch batch) {
	super.render(batch);
	if (isPushed()) {
	    batch.draw(shineImage, bounds.x + 4, bounds.y + 4, bounds.width - 8, bounds.height - 8);
	} else {
	    batch.draw(shineImage, bounds.x, bounds.y, bounds.width, bounds.height);
	}
    }
    
    @Override
    public void triggered() {
	scrollView.closePrompt();
    }

}

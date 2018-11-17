package com.flizzet.headshop;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete next button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PreviousButton extends ButtonComponent {

    private EquipView view;
    
    /** Default instantiable constructor */
    public PreviousButton(EquipView view) {
	super(0, 0);
	
	this.view = view;
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/headShop/equipView/previousButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/headShop/equipView/previousButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/headShop/equipView/previousButtonPushed.png", Texture.class));

    }
    
    @Override
    public void triggered() {
	view.previousHead();
    }

}

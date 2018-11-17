package com.flizzet.weaponshop;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Concrete gold button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class GoldButton extends ButtonComponent {

    private WeaponView view;
    
    /** Default instantiable constructor */
    public GoldButton(WeaponView view) {
	super(0, 0);
	this.view = view;
    	
	this.setImage(GameManager.getInstance().assetManager.get("gui/weaponShop/goldButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/weaponShop/goldButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/weaponShop/goldButtonPushed.png", Texture.class));

    }
    
    @Override
    public void triggered() {
	view.getWeapon().setGold(!view.getWeapon().isGold());
    }

}

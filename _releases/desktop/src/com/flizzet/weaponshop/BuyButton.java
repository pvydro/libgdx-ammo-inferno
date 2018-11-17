package com.flizzet.weaponshop;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.weapons.Weapon;
import com.flizzet.weaponsystem.Weapons;

/**
 * Concrete buy button for the Weapons shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also WeaponShop
 */
class BuyButton extends ButtonComponent {

    private ScrollView view;
    private Weapon weapon;
    private int state = 0;	// 0 = Buy; 1 = Already bought;
    
    /** Default instantiable constructor */
    public BuyButton(ScrollView view) {
	super(0, 0);
	
	this.view = view;
	this.setImage(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButtonPushed.png", Texture.class));
    }
    
    @Override
    public void triggered() {
	GameNotification notification = null;
	switch (state) {
	case 0:
	    if (CurrentSettings.getInstance().brokeBoi) {			// Buy for free
		Sounds.play(Sounds.getInstance().buySound, 1.0f);
		Weapons.getInstance().buy(weapon.getClass(), false);
		Weapons.getInstance().equip(weapon.getClass());
		setState(1);
		/* Successfully purchased notification */
		notification = new GameNotification("bought " + weapon.getName());
		view.getCurrentView().triggered();
	    } else if (Player.getInstance().getScore().getTotalCoins() >	// Buy with money
	    Weapons.getInstance().getWeapon(weapon.getClass()).getPrice()) {
		Sounds.play(Sounds.getInstance().buySound, 1.0f);
		Weapons.getInstance().buy(weapon.getClass(), true);
		Weapons.getInstance().equip(weapon.getClass());
		setState(1);
		/* Successfully purchased notification */
		notification = new GameNotification("bought " + weapon.getName());
		view.getCurrentView().triggered();
	    } else {								// Not enough money
		notification = new GameNotification("insufficient funds");
	    }
	    break;
	case 1:
	    Weapons.getInstance().equip(weapon.getClass());
	    setState(2);
	    notification = new GameNotification("equipped " + weapon.getName());
	    break;
	case 2:
	    notification = new GameNotification("already equipped");
	    break;
	}
	GameManager.getInstance().guiContainer.addToGui(notification);
    }
    
    /** Sets a new weapon for actions to be triggered on */
    public void setWeapon(Weapon newWeapon) {
	this.weapon = newWeapon;
	/* Set the state of the button depending on whether or not the weapon is already bought/equipped */
	if (Weapons.getInstance().isEquipped(newWeapon.getClass())) {
	    setState(2);
	} else if (Weapons.getInstance().isBought(newWeapon.getClass())) {
	    setState(1);
	} else {
	    setState(0);
	}
    }
    
    /** Changes the state of the button between Buy, Equip, and Equipped */
    public void setState(int newState) {
	this.state = newState;
	/* Set a new image based on the newly defined state */
	switch(newState) {
	case 0:
	    // Buy
	    this.setImage(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/weaponShop/buyButtonPushed.png", Texture.class));
	    break;
	case 1:
	    // Equip
	    this.setImage(GameManager.getInstance().assetManager.get("gui/weaponShop/equipButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/weaponShop/equipButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/weaponShop/equipButtonPushed.png", Texture.class));
	    break;
	case 2:
	    // Equipped
	    this.setImage(GameManager.getInstance().assetManager.get("gui/weaponShop/equippedButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/weaponShop/equippedButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/weaponShop/equippedButtonPushed.png", Texture.class));
	    break;
	}
    }
    
}

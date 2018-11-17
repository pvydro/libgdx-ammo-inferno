package com.flizzet.upgradeshop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Concrete shop button for the DeathMenu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also DeathMenu
 */
class PromptBuyButton extends ButtonComponent {

    private Upgrade upgrade;
    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/upgradeShop/prompt/buttonShine.png");
    private int state = 0;	// 0 = Buy; 1 = Equip; 2 = Equipped/Not pushable
    
    /** Default instantiable constructor */
    public PromptBuyButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/prompt/buyButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/prompt/buyButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/prompt/buyButtonPushed.png", Texture.class));
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
	switch (state) {
	case 0:
	    if (CurrentSettings.getInstance().brokeBoi) {
		Sounds.play(Sounds.getInstance().buySound, 1.0f);
		Upgrades.getInstance().buy(upgrade.getClass(), false);
		Upgrades.getInstance().equip(upgrade.getClass());
		setState(2);
		/* Successfully purchased notification */
		GameNotification notification = new GameNotification("bought " + upgrade.getName());
		GameManager.getInstance().guiContainer.addToGui(notification);
	    } else if (Player.getInstance().getScore().getTotalCoins() >
	    Upgrades.getInstance().getUpgrade(upgrade.getClass()).getPrice()) {
		Sounds.play(Sounds.getInstance().buySound, 1.0f);
		Upgrades.getInstance().buy(upgrade.getClass(), true);
		Upgrades.getInstance().equip(upgrade.getClass());
		setState(2);
		/* Successfully purchased notification */
		GameNotification notification = new GameNotification("bought " + upgrade.getName());
		GameManager.getInstance().guiContainer.addToGui(notification);
	    } else {
		GameNotification notification = new GameNotification("insufficient funds");
		GameManager.getInstance().guiContainer.addToGui(notification);
	    }
	    break;
	case 1:
	    Upgrades.getInstance().equip(upgrade.getClass());
	    setState(2);
	    break;
	case 2:
	    GameNotification notification = new GameNotification("already equipped");
	    GameManager.getInstance().guiContainer.addToGui(notification);
	    break;
	}
    }
    
    /** Sets a new upgrade for actions to be triggered on */
    public void setUpgrade(Upgrade newUpgrade) {
	this.upgrade = newUpgrade;
	/* Set the state of the button depending on whether or not the upgrade is already bought/equipped */
	if (Upgrades.getInstance().isEquipped(newUpgrade.getClass())) {
	    setState(2);
	} else if (Upgrades.getInstance().isBought(newUpgrade.getClass())) {
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
	    this.setImage(GameManager.getInstance().assetManager.get("gui/prompt/buyButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/prompt/buyButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/prompt/buyButtonPushed.png", Texture.class));
	    break;
	case 1:
	    this.setImage(GameManager.getInstance().assetManager.get("gui/prompt/equipButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/prompt/equipButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/prompt/equipButtonPushed.png", Texture.class));
	    break;
	case 2:
	    this.setImage(GameManager.getInstance().assetManager.get("gui/prompt/equippedButton.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/prompt/equippedButton.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/prompt/equippedButtonPushed.png", Texture.class));
	    break;
	}
    }
    
}

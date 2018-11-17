package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.camera.MainCamera;
import com.flizzet.deathmenu.HeadShopButton;
import com.flizzet.deathmenu.UpgradeShopButton;
import com.flizzet.deathmenu.WeaponShopButton;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Builds all elements of the shop selection menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ShopSelectionMenuBuilder extends MenuBuilder {
    
    private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();
    private final int STACK_MARGIN = 1;
    private final int MARGIN = 80;
    
    /** Default instantiable constructor */
    public ShopSelectionMenuBuilder() {}

    @Override
    public void buildMenu() {
	buttons.add(new HeadShopButton());
	buttons.add(new WeaponShopButton());
	buttons.add(new UpgradeShopButton());
	
	int totalButtons = 0;
	for (ButtonComponent b : buttons) {
	    b.setX(MainCamera.getInstance().getCenterX() - (b.getWidth() / 2) + 400);
	    b.setY(MainCamera.getInstance().getHeight() - MARGIN - b.getHeight() - (b.getHeight() * totalButtons) - (STACK_MARGIN * totalButtons));
	    totalButtons++;
	    
	    GameManager.getInstance().guiContainer.addToGui(b);
	}
    }
    
    public ArrayList<ButtonComponent> getButtons()	{ return this.buttons; }

}

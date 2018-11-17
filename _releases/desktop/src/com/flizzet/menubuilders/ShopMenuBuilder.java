package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.deathmenu.HeadShopButton;
import com.flizzet.deathmenu.UpgradeShopButton;
import com.flizzet.deathmenu.WeaponShopButton;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;

/**
 * Menu builder for the shop menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also ShopMenu
 */
public class ShopMenuBuilder extends MenuBuilder {

    private final int STACK_MARGIN = 1;
    private final int MARGIN = 80;
    
    private BackgroundComponent background;
    private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();
    
    /** Default instantiable constructor */
    public ShopMenuBuilder() {}

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	buttons.add(new HeadShopButton());
	buttons.add(new WeaponShopButton());
	buttons.add(new UpgradeShopButton());
	
	GameManager.getInstance().guiContainer.addToGui(background);
	
	int totalButtons = 0;
	for (ButtonComponent b : buttons) {
	    b.setX(MainCamera.getInstance().getCenterX() - (b.getWidth() / 2));
	    b.setY(MainCamera.getInstance().getHeight() - MARGIN - b.getHeight() - (b.getHeight() * totalButtons) - (STACK_MARGIN * totalButtons));
	    totalButtons++;
	    
	    GameManager.getInstance().guiContainer.addToGui(b);
	}
	
    }

}

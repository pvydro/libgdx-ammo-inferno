package com.flizzet.menubuilders;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.guicomponents.EconomyComponent;
import com.flizzet.main.GameManager;
import com.flizzet.weaponshop.ScrollView;

/**
 * Menu builder for the weapon shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponShopBuilder extends MenuBuilder {

    private BackgroundComponent background;
    private ScrollView scrollView;
    private EconomyComponent economyComponent;
    
    /** Default instantiable constructor */
    public WeaponShopBuilder() {
    }

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	scrollView = new ScrollView();
	economyComponent = new EconomyComponent();
	
	GameManager.getInstance().guiContainer.addToGui(background);
	GameManager.getInstance().guiContainer.addToGui(scrollView);
	GameManager.getInstance().guiContainer.addToGui(economyComponent);
    }

}

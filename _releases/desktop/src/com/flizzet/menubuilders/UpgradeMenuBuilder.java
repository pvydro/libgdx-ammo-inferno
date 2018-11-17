package com.flizzet.menubuilders;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.main.GameManager;
import com.flizzet.upgradeshop.CurrentUpgradesBar;
import com.flizzet.upgradeshop.UpgradeScrollView;

/**
 * Menu Builder for the UpgradeShop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class UpgradeMenuBuilder extends MenuBuilder {

    private BackgroundComponent background;
    private UpgradeScrollView scrollView;
    private CurrentUpgradesBar currentBar;
    
    /** Default instantiable constructor */
    public UpgradeMenuBuilder() {
    }

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	scrollView = new UpgradeScrollView(this);
	scrollView.setY(-500);
	currentBar = new CurrentUpgradesBar();
	
	/* Add elements to background */
	GameManager.getInstance().guiContainer.addToGui(background);
	GameManager.getInstance().guiContainer.addToGui(scrollView);
	GameManager.getInstance().guiContainer.addToGui(currentBar);
    }
    
    public UpgradeScrollView getScrollView()	{ return this.scrollView; }
    public CurrentUpgradesBar getCurrentBar()	{ return this.currentBar; }

}

package com.flizzet.menubuilders;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.headshop.EquipView;
import com.flizzet.headshop.IconView;
import com.flizzet.main.GameManager;

/**
 * Builds head menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HeadMenuBuilder extends MenuBuilder {

    private BackgroundComponent background;
    private IconView iconView;
    private EquipView equipView;
    
    /** Default instantiable constructor */
    public HeadMenuBuilder() {}

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	iconView = new IconView();
	equipView = new EquipView(iconView);
	
	GameManager.getInstance().guiContainer.addToGui(background);
	GameManager.getInstance().guiContainer.addToGui(iconView);
	GameManager.getInstance().guiContainer.addToGui(equipView);
    }
    
    public IconView getIconView()	{ return iconView; }

}

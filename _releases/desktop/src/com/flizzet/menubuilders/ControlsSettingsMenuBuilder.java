package com.flizzet.menubuilders;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.controlsettingsmenu.ControlSelector;
import com.flizzet.main.GameManager;

/**
 * Builds the controls settings menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ControlsSettingsMenuBuilder extends MenuBuilder {

    private BackgroundComponent background;
    private ControlSelector selector;
    
    /** Default instantiable constructor */
    public ControlsSettingsMenuBuilder() {}

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	selector = new ControlSelector();
	
	GameManager.getInstance().guiContainer.addToGui(background);
	GameManager.getInstance().guiContainer.addToGui(selector);
    }

}

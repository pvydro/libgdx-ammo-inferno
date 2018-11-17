package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.settingsmenu.ControlsButton;
import com.flizzet.settingsmenu.EffectsButton;
import com.flizzet.settingsmenu.GeneralButton;

/**
 * Concrete menu builder for the settings menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SettingsMenuBuilder extends MenuBuilder {

    private BackgroundComponent background;
    private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();
    private final int MARGIN = 80;
    private final int STACK_MARGIN = 1;
    
    /** Default instantiable constructor */
    public SettingsMenuBuilder() {}

    @Override
    public void buildMenu() {
	buttons.add(new ControlsButton());
	buttons.add(new EffectsButton());
	buttons.add(new GeneralButton());
	background = new BackgroundComponent();
	
	
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

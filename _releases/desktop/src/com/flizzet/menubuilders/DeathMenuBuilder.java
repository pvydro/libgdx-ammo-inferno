package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.camera.MainCamera;
import com.flizzet.deathmenu.DeathMenu;
import com.flizzet.deathmenu.QuitButton;
import com.flizzet.deathmenu.ReplayButton;
import com.flizzet.deathmenu.SettingsButton;
import com.flizzet.deathmenu.ShopButton;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.ShineOverlay;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;

/**
 * Builds the death menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DeathMenuBuilder {
    
    private DeathMenu menu;
    
    private ScreenDarkener darkener;
    private ShineOverlay overlay;
    
    private ReplayButton replayButton;
    private ShopButton shopButton;
    private SettingsButton settingsButton;
    private QuitButton quitButton;
    
    private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();
    
    private final float MARGIN = 2;
    private final int STACK_MARGIN = 1;

    /** Default instantiable constructor */
    public DeathMenuBuilder(DeathMenu menu) {
	this.menu = menu;
    }

    /** Adds all gui elements to the screen */
    public void buildMenu() {
	
	darkener = new ScreenDarkener(0.5f);
	overlay = new ShineOverlay();
	
	/* Set overlay */
	GameManager.getInstance().ambience.setAboveGui(true);

	/* Building button objects */
	buttons.add(replayButton = new ReplayButton());
	buttons.add(shopButton = new ShopButton());
	buttons.add(settingsButton = new SettingsButton());
	buttons.add(quitButton = new QuitButton());
	
	/* Adding aesthetics */
	GameManager.getInstance().guiContainer.addToGui(darkener);
	GameManager.getInstance().guiContainer.addToGui(overlay);
	
	/* Setting button positions */
	replayButton.setX(MainCamera.getInstance().getCenterX() - replayButton.getWidth() - MARGIN);
	replayButton.setY(MainCamera.getInstance().getCenterY());
	shopButton.setX(MainCamera.getInstance().getCenterX() + MARGIN);
	shopButton.setY(MainCamera.getInstance().getCenterY());
	replayButton.setY(MainCamera.getInstance().getCenterY());
	settingsButton.setX(MainCamera.getInstance().getCenterX() - settingsButton.getWidth() - MARGIN);
	settingsButton.setY(replayButton.getY() - settingsButton.getHeight() - STACK_MARGIN);
	quitButton.setX(MainCamera.getInstance().getCenterX() + MARGIN);
	quitButton.setY(shopButton.getY() - quitButton.getHeight() - STACK_MARGIN);

	/* Adding buttons to guiContainer */
	for (ButtonComponent b : buttons) {
	    GameManager.getInstance().guiContainer.addToGui(b);
	}
	
	menu.setBuilt(true);
	
    }

    public float getMargin() 				{ return this.MARGIN; }
    public float getStackMargin()			{ return this.STACK_MARGIN; }
    public ArrayList<ButtonComponent> getButtons()	{ return this.buttons; }
    
}

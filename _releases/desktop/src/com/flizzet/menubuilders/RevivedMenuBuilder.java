package com.flizzet.menubuilders;

import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.revivemenu.ResumeButton;

/**
 * Menu builder for the RevivedMenu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also RevivedMenu
 */
public class RevivedMenuBuilder extends MenuBuilder {

    private ScreenDarkener screenDarkener;
    private ResumeButton resumeButton;
    
    /** Default instantiable constructor */
    public RevivedMenuBuilder() {}

    @Override
    public void buildMenu() {
	screenDarkener = new ScreenDarkener(0.5f);
	resumeButton = new ResumeButton();
	
	resumeButton.setX(MainCamera.getInstance().getCenterX() - (resumeButton.getWidth() / 2));
	resumeButton.setY(MainCamera.getInstance().getCenterY() - (resumeButton.getHeight() / 2));
	
	GameManager.getInstance().guiContainer.addToGui(screenDarkener);
	GameManager.getInstance().guiContainer.addToGui(resumeButton);
    }
    
    /** Removes all menu builder elements from the screen */
    public void disable() {
	GameManager.getInstance().guiContainer.removeFromGui(screenDarkener);
	GameManager.getInstance().guiContainer.removeFromGui(resumeButton);
    }

}

package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.pausemenu.QuitButton;
import com.flizzet.pausemenu.ResumeButton;
import com.flizzet.pausemenu.SettingsButton;

/**
 * Pause Menu Builder
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PauseMenuBuilder extends MenuBuilder {

	private ScreenDarkener screenDarkener;
	private ResumeButton resumeButton;
	private SettingsButton settingsButton;
	private QuitButton quitButton;

	private ArrayList<GuiComponent> allElements = new ArrayList<GuiComponent>();
	private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();

	private final int STACK_MARGIN = 5;

	public PauseMenuBuilder() {
	}

	public void buildMenu(float totalMargin) {
		screenDarkener = new ScreenDarkener(0.5f);
		resumeButton = new ResumeButton();
		settingsButton = new SettingsButton();
		quitButton = new QuitButton();

		buttons.add(resumeButton);
		buttons.add(settingsButton);
		buttons.add(quitButton);
		for (ButtonComponent b : buttons) {
			allElements.add(b);
		}
		allElements.add(screenDarkener);
		GameManager.getInstance().guiContainer.addToGui(screenDarkener);

		/* Positioning buttons */
		int totalButtons = 0;
		for (ButtonComponent b : buttons) {
			b.setX(MainCamera.getInstance().getCenterX()
					- (resumeButton.getWidth() / 2));
			b.setY(totalMargin - STACK_MARGIN
					- ((b.getHeight() + STACK_MARGIN) * totalButtons));
			totalButtons++;
			GameManager.getInstance().guiContainer.addToGui(b);
		}

	}

	/** Removes every element built from the GuiContainer */
	public void removeAll() {
		for (GuiComponent g : allElements) {
			GameManager.getInstance().guiContainer.removeFromGui(g);
		}
		buttons.clear();
	}
	@Override
	public void buildMenu() {
	}

	public ButtonComponent getResumeButton() {
		return this.resumeButton;
	}

}

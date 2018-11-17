package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.TitleComponent;
import com.flizzet.main.GameManager;
import com.flizzet.startmenu.PlayButton;
import com.flizzet.startmenu.SettingsButton;
import com.flizzet.startmenu.ShopButton;
import com.flizzet.startmenu.TutorialButton;

/**
 * Builds start menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also StartMenuState
 */
public class StartMenuBuilder extends MenuBuilder {

	private BackgroundComponent backgroundComponent;
	private TitleComponent title;
	private ArrayList<ButtonComponent> buttons = new ArrayList<ButtonComponent>();

	private final int STACK_MARGIN = 5;
	private final int MARGIN = 20;

	/** Default instantiable constructor */
	public StartMenuBuilder() {
	}

	/** Sets buttons */
	public void buildMenu() {
		backgroundComponent = new BackgroundComponent();
		title = new TitleComponent();
		buttons.add(new ShopButton());
		buttons.add(new TutorialButton());
		buttons.add(new SettingsButton());
		buttons.add(new PlayButton());

		/* Position buttons */
		int totalButtons = 0;
		float fullWidth = (buttons.get(0).getWidth()) * buttons.size()
				+ (STACK_MARGIN * (buttons.size() - 1));
		for (ButtonComponent b : buttons) {
			b.setX(MainCamera.getInstance().getCenterX() - (fullWidth / 2)
					+ ((b.getWidth() + STACK_MARGIN) * totalButtons));
			b.setY(MARGIN);

			totalButtons++;
		}

		/* Title */
		title.setX(
				MainCamera.getInstance().getCenterX() - (title.getWidth() / 2));
		title.setY(MainCamera.getInstance().getHeight() - title.getHeight()
				- MARGIN);

		/* Adding to gui container */
		GameManager.getInstance().guiContainer.addToGui(backgroundComponent);
		GameManager.getInstance().guiContainer.addToGui(title);
		for (ButtonComponent b : buttons) {
			GameManager.getInstance().guiContainer.addToGui(b);
		}
	}

	/** Renders all components in this menu */
	public void render(SpriteBatch batch) {
	}

	/** Updates all components in this menu */
	public void update(float delta) {
	}

	public ArrayList<ButtonComponent> getButtons() {
		return this.buttons;
	}

}

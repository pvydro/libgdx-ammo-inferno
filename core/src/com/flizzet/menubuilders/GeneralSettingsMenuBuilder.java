package com.flizzet.menubuilders;

import java.util.ArrayList;
import java.util.List;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.generalsettingsmenu.CreditsButton;
import com.flizzet.guicomponents.CheckboxComponent;
import com.flizzet.guicomponents.SeparatorComponent;
import com.flizzet.guicomponents.SeparatorFillerComponent;
import com.flizzet.main.GameManager;
import com.flizzet.music.MusicHandler;
import com.flizzet.settings.CurrentSettings;

/**
 * General settings menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GeneralSettingsMenuBuilder extends MenuBuilder {

	private CheckboxComponent checkbox_music;
	private CheckboxComponent checkbox_sound;
	private CheckboxComponent checkbox_doubleTap;
	private SeparatorComponent separator;
	private SeparatorComponent finalSeparator;
	private SeparatorFillerComponent filler;
	private BackgroundComponent background;
	private CreditsButton creditsButton;

	private List<CheckboxComponent> checkboxes = new ArrayList<CheckboxComponent>();

	public final int MARGIN = 60;

	/** Default instantiable constructor */
	public GeneralSettingsMenuBuilder() {
	}

	@Override
	public void buildMenu() {
		background = new BackgroundComponent();

		separator = new SeparatorComponent(2);
		finalSeparator = new SeparatorComponent(2);
		filler = new SeparatorFillerComponent(separator, finalSeparator);
		creditsButton = new CreditsButton();

		GameManager.getInstance().guiContainer.addToGui(background);
		GameManager.getInstance().guiContainer.addToGui(filler);
		GameManager.getInstance().guiContainer.addToGui(separator);
		GameManager.getInstance().guiContainer.addToGui(finalSeparator);
		GameManager.getInstance().guiContainer.addToGui(creditsButton);

		/* Checkboxes */
		checkbox_music = new CheckboxComponent("music",
				"play the soundtrack during gameplay and menu browsing.",
				CurrentSettings.getInstance().music) {
			@Override
			public void triggered() {
				switch (state) {
					case ON:
						CurrentSettings.getInstance().music = true;
						break;
					case OFF:
						if (MusicHandler.getInstance().getMenuMusic()
								.isPlaying()) {
							MusicHandler.getInstance().stop(
									MusicHandler.getInstance().getMenuMusic());
						}
						CurrentSettings.getInstance().music = false;
						break;
				}
			}
		};
		checkbox_sound = new CheckboxComponent("sound",
				"play sounds during gameplay and menu browsing.",
				CurrentSettings.getInstance().sound) {
			@Override
			public void triggered() {
				switch (state) {
					case ON:
						CurrentSettings.getInstance().sound = true;
						break;
					case OFF:
						CurrentSettings.getInstance().sound = false;
						break;
				}
			}
		};
		checkbox_doubleTap = new CheckboxComponent("double tap select",
				"double tap to enable enemy selection mode.",
				CurrentSettings.getInstance().doubleTapSelect) {
			@Override
			public void triggered() {
				switch (state) {
					case ON:
						CurrentSettings.getInstance().doubleTapSelect = true;
						break;
					case OFF:
						CurrentSettings.getInstance().doubleTapSelect = false;
						break;
				}
			}
		};

		/* Add to checkboxes ArrayList */
		checkboxes.add(checkbox_music);
		checkboxes.add(checkbox_sound);
		checkboxes.add(checkbox_doubleTap);

		/* Separate */
		separator.setY(MainCamera.getInstance().getHeight() - MARGIN);

		/* Position checkboxes */
		int totalCheckboxes = 0;
		for (CheckboxComponent c : checkboxes) {
			totalCheckboxes++;

			c.setX(MainCamera.getInstance().getWidth() - 20 - c.getWidth());
			c.setY(separator.getY() - 3
					- (c.getHeight() + 5) * totalCheckboxes);

			GameManager.getInstance().guiContainer.addToGui(c);
		}

		/* Separate */
		finalSeparator.setY(checkboxes.get(checkboxes.size() - 1).getY() - 10);

		/* Position credits button */
		creditsButton.setX(MainCamera.getInstance().getCenterX()
				- (creditsButton.getWidth() / 2));
		creditsButton
				.setY(finalSeparator.getY() - creditsButton.getHeight() - 10);
	}

}

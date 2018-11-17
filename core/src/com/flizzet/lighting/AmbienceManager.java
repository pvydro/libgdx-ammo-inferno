package com.flizzet.lighting;

import com.flizzet.main.GameManager;

/**
 * Handles switching of the ambience.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AmbienceManager {

	private static final AmbienceManager INSTANCE = new AmbienceManager();

	private int ambienceIndex = 0;
	private static final int TOTAL_AMBIENCES = AmbienceColor.values().length;
	private AmbienceColor currentAmbience;

	/** Returns an instance of the AmbienceManager class */
	public static AmbienceManager getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public AmbienceManager() {
	}
	/** Moves to the next ambience in the rotation */
	public void nextAmbience() {
		ambienceIndex++;
		/* Loop at max */
		if (ambienceIndex == TOTAL_AMBIENCES) {
			ambienceIndex = 1;
		}

		/* Find color depending on index */
		if (ambienceIndex == 1) {
			currentAmbience = AmbienceColor.PURPLE;
		} else if (ambienceIndex == 2) {
			currentAmbience = AmbienceColor.ORANGE;
		} else if (ambienceIndex == 3) {
			currentAmbience = AmbienceColor.GREEN;
		} else if (ambienceIndex == 4) {
			currentAmbience = AmbienceColor.PINK;
		} else if (ambienceIndex == 5) {
			currentAmbience = AmbienceColor.BLUE;
		} else if (ambienceIndex == 6) {
			currentAmbience = AmbienceColor.BROWN;
		}

		setAmbience(currentAmbience);
	}

	/** Sets an ambience using the color values of the Ambience provided */
	private void setAmbience(AmbienceColor newAmbience) {
		GameManager.getInstance().ambience.setColor(newAmbience.getColor());
	}

	/** Resets to the initial state */
	public void reset() {
		ambienceIndex = 0;
		nextAmbience();
	}

}

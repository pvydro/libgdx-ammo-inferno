package com.flizzet.map;

import com.flizzet.main.GameManager;

/**
 * Desc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MapBuilder {

	private MapBackground background;
	private PlatformGenerator platformGenerator;
	private Lava lava;
	private MapForeground foreground;

	/** Default instantiable constructor */
	public MapBuilder() {
	}

	/** Puts background element on the screen */
	public void buildBackground() {
		background = MapBackground.getInstance();
		background.reset();

		GameManager.getInstance().entityContainer.add(background);
	}

	/** Puts foreground element on the screen */
	public void buildForeground() {
		foreground = MapForeground.getInstance();
		foreground.reset();

		GameManager.getInstance().entityContainer.add(foreground);
	}

	/** Puts lava element on the screen */
	public void buildLava() {
		lava = Lava.getInstance();
		lava.reset();

		GameManager.getInstance().entityContainer.add(lava);
	}

	/** Puts platform generator element on the screen */
	public void buildPlatforms() {
		platformGenerator = PlatformGenerator.getInstance();
		platformGenerator.reset();

		GameManager.getInstance().entityContainer.add(platformGenerator);
	}

}

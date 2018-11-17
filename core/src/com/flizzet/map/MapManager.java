package com.flizzet.map;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.lighting.AmbienceManager;
import com.flizzet.main.GameManager;

/**
 * Manages map switching and setting.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MapManager {

	private static final MapManager INSTANCE = new MapManager();
	/* Map */
	private int currentMapIndex = 0;
	private MapName currentMap = MapName.LOWER_CAVE;
	private static final int TOTAL_MAPS = 6;

	/** Returns an instance of the MapManager class */
	public static MapManager getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public MapManager() {
	}

	/** Moves to the next map in the rotation */
	public void nextMap() {
		currentMapIndex++;

		/* Loop when reaching the maximum map */
		if (currentMapIndex == TOTAL_MAPS + 1) {
			currentMapIndex = 1;
			AmbienceManager.getInstance().nextAmbience();
		}

		if (currentMapIndex == 1) {
			currentMap = MapName.LOWER_CAVE;
		}
		if (currentMapIndex == 2) {
			currentMap = MapName.DUNGEON_CAVE;
		} else if (currentMapIndex == 3) {
			currentMap = MapName.MODERN_CAVE;
		} else if (currentMapIndex == 4) {
			currentMap = MapName.ICE_CAVE;
		} else if (currentMapIndex == 5) {
			currentMap = MapName.CASTLE_CAVE;
		} else if (currentMapIndex == 6) {
			currentMap = MapName.INDUSTRIAL_CAVE;
		} else {
			currentMap = MapName.LOWER_CAVE;
		}

		setMap(currentMap);

		GameManager.getInstance().particleFunctions.addScreenFlash(1.0f);
	}

	/** Resets the index and returns to the first map */
	public void reset() {
		currentMapIndex = 0;
		nextMap();
	}

	/** Sets the map based on the Map Name defined */
	public void setMap(MapName name) {
		switch (name) {
			case LOWER_CAVE: // Set images for the lower cave map
				setMapImages("map/foregrounds/fgLowerLeft.png",
						"map/foregrounds/fgLowerRight.png",
						"map/backgrounds/bgLower.png",
						"map/platforms/platformLower.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconLower.png",
										Texture.class));
				break;
			case DUNGEON_CAVE: // Set images for the dungeon cave map
				setMapImages("map/foregrounds/fgDungeonLeft.png",
						"map/foregrounds/fgDungeonRight.png",
						"map/backgrounds/bgDungeon.png",
						"map/platforms/platformDungeon.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconDungeon.png",
										Texture.class));
				break;
			case MODERN_CAVE: // Set images for the modern cave map
				setMapImages("map/foregrounds/fgModernLeft.png",
						"map/foregrounds/fgModernRight.png",
						"map/backgrounds/bgModern.png",
						"map/platforms/platformModern.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconModern.png",
										Texture.class));
				break;
			case CASTLE_CAVE: // Set images for the castle cave map
				setMapImages("map/foregrounds/fgCastleLeft.png",
						"map/foregrounds/fgCastleRight.png",
						"map/backgrounds/bgCastle.png",
						"map/platforms/platformCastle.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconCastle.png",
										Texture.class));
				break;
			case ICE_CAVE: // Set images for the ice cave map
				setMapImages("map/foregrounds/fgIceLeft.png",
						"map/foregrounds/fgIceRight.png",
						"map/backgrounds/bgIce.png",
						"map/platforms/platformIce.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconIce.png",
										Texture.class));
				break;
			case WOOD_CAVE:
				break;
			case INDUSTRIAL_CAVE:
				setMapImages("map/foregrounds/fgIndustrialLeft.png",
						"map/foregrounds/fgIndustrialRight.png",
						"map/backgrounds/bgIndustrial.png",
						"map/platforms/platformIndustrial.png");
				InGameGui.getInstance().getScoreCounter()
						.setPlatformIcon(GameManager.getInstance().assetManager
								.get("gui/icons/platformIconIndustrial.png",
										Texture.class));
				break;
			default:
				break;
		}
	}

	/**
	 * Sets images for all maps using the parameters defined.
	 * 
	 * @param foregroundRight
	 *            - Right foreground image reference
	 * @param foregroundLeft
	 *            - Left foreground image reference
	 * @param background
	 *            - Background image reference
	 * @param platform
	 *            - Platform image reference
	 */
	private void setMapImages(String foregroundLeft, String foregroundRight,
			String background, String platform) {
		MapForeground.getInstance().setImages(foregroundLeft, foregroundRight);
		MapBackground.getInstance().setImage(background);
		PlatformGenerator.getInstance().setImage(platform);
		/* Set every existing platform image */
		for (Platform p : PlatformGenerator.getInstance().getPlatforms()) {
			p.setImage(GameManager.getInstance().assetManager.get(platform,
					Texture.class));
		}
	}

}

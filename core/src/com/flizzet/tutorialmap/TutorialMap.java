package com.flizzet.tutorialmap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;

/**
 * Full map for the tutorial.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TutorialMap extends Entity {

	private static final TutorialMap INSTANCE = new TutorialMap();

	private Background background = new Background();
	private Floor floor = new Floor();
	private Platform platform = new Platform();
	private Screen screen = new Screen();

	/** Returns an instance of the TutorialMap class */
	public static TutorialMap getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public TutorialMap() {
		init();
	}

	/** Initiates all objects */
	private void init() {
		platform.setSide(2);
		platform.setImage(GameManager.getInstance().assetManager
				.get("tutorial/platform.png", Texture.class));
		platform.setX(MainCamera.getInstance().getCenterX()
				- (platform.getWidth() / 2));
		platform.setY(150);
	}

	@Override
	public void update(float delta) {
		background.update(delta);
		platform.update(delta);
		floor.update(delta);
		screen.update(delta);
	}
	@Override
	public void render(SpriteBatch batch) {
		background.render(batch);
		platform.render(batch);
		floor.render(batch);
		screen.render(batch);
	}

	public Platform getPlatform() {
		return this.platform;
	}
	public Floor getFloor() {
		return this.floor;
	}

}

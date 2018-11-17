package com.flizzet.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.eyeenemy.FlyingEyeEnemy;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.ingamegui.PlatformIndicator;
import com.flizzet.main.GameManager;

/**
 * Builds platforms for the player to jump on.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformGenerator extends Entity {

	private static final PlatformGenerator INSTANCE = new PlatformGenerator();

	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private Queue<Platform> toBeRemoved = new LinkedList<Platform>();
	private PlatformIndicator indicator = PlatformIndicator.getInstance();
	private Texture platformImage;

	private int totalPlatforms = 0;
	private boolean mid = false;
	private float platformSpeed = -1f;

	/** Returns an instance of the PlatformGenerator */
	public static PlatformGenerator getInstance() {
		return INSTANCE;
	}

	/** Single use constructor */
	private PlatformGenerator() {
	}

	@Override
	public void update(float delta) {

		platformSpeed = -1f;

		platforms.removeAll(toBeRemoved);
		toBeRemoved.removeAll(toBeRemoved);

		/* Decrease platform position */
		for (Platform p : platforms) {
			p.update(delta);
			p.setY(p.getY() + platformSpeed);

			if (p.getY() < -100) { // Delete platform when off the bottom of the
									// screen
				toBeRemoved.add(p); // Add to a removal queue for the next cycle
				for (FlyingEyeEnemy e : FlyingEyeEnemySpawner.getInstance()
						.getEyes()) {
					e.getAi().platformRemoved();
				}
			}
		}

		/* Check if a new platform should be added or not */
		if (platforms.size() != 0) {
			if (platforms.get(platforms.size() - 1).getY() < 400) {
				createNewPlatform(); // Create a new platform if the last one
										// travelled enough
			}
		} else {
			createNewPlatform();
		}

		indicator.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		for (Platform p : platforms) {
			p.render(batch);
		}

		indicator.render(batch);

	}

	/** Adds a new platform to the map */
	private void createNewPlatform() {

		Platform newPlatform = new Platform();
		newPlatform.setImage(platformImage);

		if (platforms.size() == 0) { // Check if it's the first platform
			newPlatform.setSide(0);
			newPlatform.setX(MapForeground.getInstance().getLeftX());
			newPlatform.setY(MainCamera.getInstance().getHeight() + 100);
			newPlatform.setHit(true);
			indicator.setTargetPlatform(newPlatform);
		} else {
			if (!mid) {
				mid = new Random().nextBoolean();
			} else {
				mid = false;
			}

			if (mid) { // If the platform is a mid, place it as such
				newPlatform.setSide(2);
				newPlatform.setX(MainCamera.getInstance().getCenterX()
						- (newPlatform.getWidth() / 2));
				newPlatform.setY(MainCamera.getInstance().getHeight() + 100);

				newPlatform.addRandomEnemy();
			} else if ((totalPlatforms % 2) == 0) { // Otherwise, check if the
													// current platform is odd.
													// If so, go on the left
													// side
				newPlatform.setSide(0);
				newPlatform.setX(MapForeground.getInstance().getLeftX());
				newPlatform.setY(MainCamera.getInstance().getHeight() + 100);

				newPlatform.addRandomEnemy();
			} else { // Otherwise, if it's even, go on the right side
				newPlatform.setSide(1);
				newPlatform.setX(MapForeground.getInstance().getRightX()
						- platforms.get(0).getWidth() + 11);
				newPlatform.setY(MainCamera.getInstance().getHeight() + 100);

				newPlatform.addRandomEnemy();
			}
		}

		newPlatform.addRandomLamp();
		newPlatform.addRandomChest();

		totalPlatforms++;
		platforms.add(newPlatform);

	}

	/** Respawn */
	public void reset() {
		toBeRemoved.addAll(platforms);
		totalPlatforms = 0;
	}

	/** Sets a new image for all platforms to adopt */
	public void setImage(String imageDir) {
		this.platformImage = GameManager.getInstance().assetManager
				.get(imageDir, Texture.class);
	}

	public ArrayList<Platform> getPlatforms() {
		return this.platforms;
	}
	public float getFirstPlatformY() {
		return this.platforms.get(0).getY();
	}
	public float getSpeed() {
		return this.platformSpeed;
	}

}
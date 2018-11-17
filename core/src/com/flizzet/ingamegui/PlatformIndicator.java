package com.flizzet.ingamegui;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.lighting.PlatformIndicatorLight;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;

/**
 * Indicates which entity is the current highest one reached.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformIndicator extends Entity {

	private static final PlatformIndicator INSTANCE = new PlatformIndicator();

	private Texture image;
	private Texture imageHigh;
	private PlatformIndicatorLight light = new PlatformIndicatorLight(this);
	private Platform targetPlatform;
	private Random random = new Random();
	private boolean sideSet = false;
	private int side = 0;

	public static PlatformIndicator getInstance() {
		return INSTANCE;
	}
	/** Default instantiable constructor */
	public PlatformIndicator() {
		image = GameManager.getInstance().assetManager
				.get("gui/inGame/platformIndicator.png");
		imageHigh = GameManager.getInstance().assetManager
				.get("gui/inGame/platformIndicatorHigh.png");
		adjustBoundsToImage();
	}

	@Override
	public void update(float delta) {
		float spacing = 5;
		float newX = 0;
		float newY = 0;

		if (targetPlatform != null) {
			/* Set target position */
			if (targetPlatform.getSide() == 0) {
				newX = targetPlatform.getX() + targetPlatform.getWidth()
						+ spacing;
			} else if (targetPlatform.getSide() == 2) {
				if (!sideSet) {
					sideSet = true;
					side = random.nextBoolean() ? 0 : 1;
				}

				if (side == 0)
					newX = targetPlatform.getX() + targetPlatform.getWidth()
							+ spacing;
				else if (side == 1)
					newX = targetPlatform.getX() - spacing - bounds.width;
			} else {
				newX = targetPlatform.getX() - spacing - bounds.width;
			}
			newY = targetPlatform.getCenterY() - (bounds.height / 2);
		} else {
			/* Set to center of the screen if there's no target */
			newX = MainCamera.getInstance().getCenterX() - (bounds.width / 2);
			newY = MainCamera.getInstance().getCenterY() - (bounds.height / 2);
		}

		/* Move position to target position */
		bounds.x += 10 * ((newX - bounds.x) / 2) * delta;
		bounds.y += 10 * ((newY - bounds.y) / 2) * delta;
		bounds.y += PlatformGenerator.getInstance().getSpeed();

		/* Update light */
		if (Player.getInstance().getScore().isHighScoreShown())
			light.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		if (Player.getInstance().getScore().isHighScoreShown()) {
			batch.draw(imageHigh, bounds.x, bounds.y);
			light.render(batch);
		} else {
			batch.draw(image, bounds.x, bounds.y);
		}
	}

	/** Sets a new platform to target */
	public void setTargetPlatform(Platform newPlatform) {
		targetPlatform = newPlatform;
		sideSet = false;
	}

	/** Sets dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
	}

}

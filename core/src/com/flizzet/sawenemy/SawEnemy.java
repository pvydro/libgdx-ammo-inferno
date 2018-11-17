package com.flizzet.sawenemy;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemies.Enemy;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.sound.Sounds;

/**
 * Saw enemy to spawn on platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SawEnemy extends Enemy {

	private SawEnemyCollision collision;
	private Platform platform;
	private Texture image;
	private Random random = new Random();
	private float rotation = 0;
	private float originX, originY;

	/** Default instantiable constructor */
	public SawEnemy(Platform platform) {
		this.platform = platform;
		this.setAi(new SawEnemyAi(this));
		image = GameManager.getInstance().assetManager
				.get("enemies/rollingSaw/saw.png");
		adjustBoundsToImage(image);

		this.collision = new SawEnemyCollision(this); // Create after creation
														// for bounds

		/* Create a random starting position */
		this.setX(platform.getX() + random.nextInt(100));

		Sounds.play(Sounds.getInstance().sawSound, 0.7f);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		/* Place on top of platform */
		this.setY(platform.getY() + platform.getHeight() - (this.getWidth() / 2)
				+ PlatformGenerator.getInstance().getSpeed());

		/* Set rotation origin */
		originX = image.getWidth() / 2;
		originY = image.getHeight() / 2;

		/* Set collision bounds */
		this.setCollisionX(this.getX());
		this.setCollisionY(this.getY());
		this.setCollisionWidth(this.getWidth());
		this.setCollisionHeight(this.getHeight());

		collision.update(delta);
		indicator.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, bounds.x, bounds.y, originX, originY, bounds.width,
				bounds.height, 1, 1, rotation, 0, 0, (int) bounds.width,
				(int) bounds.height, false, false);

		collision.render(batch);
		indicator.render(batch);
	}

	@Override
	public void die() {

	}

	public Platform getPlatform() {
		return this.platform;
	}
	public float getRotation() {
		return this.rotation;
	}

	public void setRotation(float newRotation) {
		this.rotation = newRotation;
	}

}

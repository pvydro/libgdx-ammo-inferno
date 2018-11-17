package com.flizzet.spikeenemy;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemies.Enemy;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;

/**
 * Concrete spike enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SpikeEnemy extends Enemy {

	private SpikeEnemyCollision collision;
	private Texture image;
	private Platform platform;
	private Random random = new Random();
	private int xOffset;

	/** Default instantiable constructor */
	public SpikeEnemy(Platform platform) {
		this.platform = platform;

		this.image = GameManager.getInstance().assetManager
				.get("enemies/spike/spike.png");
		this.adjustBoundsToImage(image);

		this.collision = new SpikeEnemyCollision(this);

		xOffset = random.nextInt(100);
	}

	@Override
	public void update(float delta) {
		this.setY(platform.getY() + platform.getHeight()
				+ PlatformGenerator.getInstance().getSpeed());
		this.setX(platform.getX() + xOffset);

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
		batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
		indicator.render(batch);
	}

	@Override
	public void die() {

	}

}

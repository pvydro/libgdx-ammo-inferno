package com.flizzet.zombieenemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Head of the zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ZombieEnemyHead extends Entity {

	private Texture image;
	private ZombieEnemy zombie;
	private float headXOffset = -4;
	private float yChange;
	private float headBounce = .9f;
	private float yVel = -6;
	private int lastDirection = 0;

	/** Default instantiable constructor */
	public ZombieEnemyHead(ZombieEnemy zombie) {
		this.zombie = zombie;

		image = GameManager.getInstance().assetManager
				.get("enemies/zombie/head/headDefault.png", Texture.class);
		adjustBoundsToImage(image);
	}

	@Override
	public void update(float delta) {

		if (zombie.getState() != ZombieState.DEAD) {
			/* Set head positions */
			headXOffset = 4.5f;
			this.setX(zombie.getX() + headXOffset);
			this.setY(zombie.getY() + 13);

			/* Bounce with head walking */
			int currentFrame = zombie.getAnimator().getWalkFrameIndex();

			if (currentFrame == 0) {
				yChange += 30 * ((0 - yChange) / 2) * delta;
			} else if (currentFrame > 1 && currentFrame < 5) {
				yChange = -headBounce;
			} else if (currentFrame > 4 && currentFrame < 8) {
				yChange = headBounce;
			} else if (currentFrame > 7 && currentFrame < 11) {
				yChange = -headBounce;
			} else if (currentFrame == 11 || currentFrame < 2) {
				yChange = headBounce;
			} else {
				yChange += 30 * ((0 - yChange) / 2) * delta;
			}
		} else {
			yVel += 0.3f;
			this.setY(this.getY() - yVel);
			if (CurrentSettings.getInstance().headSplatter) {
				GameManager.getInstance().particleFunctions.addBloodParticle(
						getCenterX(), getCenterY(),
						CurrentSettings.getInstance().headSplatterAmount);
			}
		}

		/* Setting direction without idleness */
		if (zombie.getAi().getDirection() == 1
				|| zombie.getAi().getDirection() == 0) {
			lastDirection = zombie.getAi().getDirection();
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		if (lastDirection == 1) {
			batch.draw(image, bounds.x, bounds.y + yChange);
		} else if (lastDirection == 0) {
			batch.draw(image, bounds.x, bounds.y + yChange, bounds.width,
					bounds.height, 0, 0, (int) bounds.width,
					(int) bounds.height, true, false);
		}

	}

}

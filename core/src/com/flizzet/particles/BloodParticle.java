package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.settings.CurrentSettings;

/**
 * Blood particle effect from hits.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class BloodParticle extends Particle {

	private Random random = new Random();
	private Color bloodColor;
	private ParticleCollision collision = new ParticleCollision(this);

	private int removeCooldown = CurrentSettings
			.getInstance().bloodParticleLifetime;
	private boolean draw = true;
	private float scale;
	private int direction = 0;

	/** Default instantiable constructor */
	public BloodParticle(float x, float y) {
		randomize();
		setX(x);
		setY(y);
	}

	@Override
	public void update(float delta) {

		super.update(delta);

		fall(delta);
		this.setY(getY() + yVel + PlatformGenerator.getInstance().getSpeed());

		/* Add or subtract horizontal speed based on direction */
		if (direction == 1) {
			this.setX(getX() + xVel);
		} else {
			this.setX(getX() - xVel);
		}

		/* Bring horizontal speed to zero */
		xVel += 10 * ((0 - xVel) / 2) * delta;

		/* Flash when on screen for a while */
		removeCooldown--;
		if (removeCooldown <= 20 && removeCooldown % 4 == 0) {
			draw = !draw;
		}

		/* Remove */
		if (removeCooldown <= 0 || bounds.y <= -100) {
			GameManager.getInstance().particleContainer.remove(this);
		}

		/* Update collision */
		if (CurrentSettings.getInstance().collideParticles) {
			collision.update(delta);
		}

	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		if (draw) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(bloodColor);
			shapeRenderer.rect(bounds.x, bounds.y, 5 * scale, 5 * scale);
			shapeRenderer.end();
			batch.begin();
		}
	}

	/** Builds a new random sized and colored blood particle */
	private void randomize() {
		/* Create a random primarily red color */
		float r = random.nextFloat();
		float g = random.nextFloat() / 3;
		float b = random.nextFloat() / 3;

		/* Build random variables */
		scale = random.nextFloat();
		direction = random.nextInt(2);
		xVel = random.nextInt(3);
		yVel = random.nextInt(5);
		bloodColor = new Color(r, g, b, 1.0f);

	}

}

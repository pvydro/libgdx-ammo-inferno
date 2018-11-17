package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.main.GameManager;

/**
 * Particle that emmits from lamps residing on platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class PlatformLampParticle extends Particle {

	private Random random = new Random();
	private ParticleCollision collision = new ParticleCollision(this);
	private Color color;
	private float removeCooldown = 100;
	private boolean draw = true;
	private float r, g, b; // Random color

	/** Default instantiable constructor */
	public PlatformLampParticle() {
		/* Randomize the particle */
		int scale = random.nextInt(3);
		float intensity = random.nextFloat() + .2f;
		this.setWidth(scale);
		this.setHeight(scale);
		if (random.nextBoolean()) {
			this.r = intensity;
			this.g = intensity - .1f;
			this.b = random.nextFloat() / 10;
		} else {
			this.r = 1f;
			this.g = 1f;
			this.b = 1f;
		}
		this.color = new Color(r, g, b, 1);
		this.yVel = random.nextFloat() - 1;
	}

	@Override
	public void update(float delta) {

		super.update(delta);

		/* Add velocities to position */
		this.setY(this.getY() + yVel);
		this.setX(this.getX() + xVel);

		/* Flashing and removal */
		removeCooldown--;
		if (removeCooldown <= 20) {
			if (removeCooldown % 4 == 0) {
				draw = !draw;
			}
		}
		if (removeCooldown <= 0) {
			GameManager.getInstance().particleContainer.remove(this);
		}

		collision.update(delta);

		/* Fall if hit by a bullet */
		if (hit) {
			fall(delta);
		}

	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

		if (draw) {
			batch.end();

			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(color);
			shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			shapeRenderer.end();

			Gdx.gl.glDisable(GL20.GL_BLEND);

			batch.begin();
		}

	}

}

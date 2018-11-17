package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;
import com.flizzet.map.Lava;
import com.flizzet.map.Platform;

/**
 * Abstract particle class for concrete particles to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public abstract class Particle extends Entity {

	protected Random random = new Random();

	protected float yVel;
	protected float xVel;
	protected boolean hit = false; // For particles that go constantly upwards
									// requiring collision

	/** Default instantiable constructor */
	public Particle() {

	}

	@Override
	public void update(float delta) {
		/* Disappear when in lava */
		if (bounds.overlaps(Lava.getInstance().getBounds())) {
			GameManager.getInstance().particleContainer.remove(this);
		}
	}
	@Override
	public void render(SpriteBatch batch) {
		throw new UnsupportedOperationException();
	}
	public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

	/** Pushes the particle up when hitting something and unsinks */
	public void bounce(Platform platform, boolean vertical,
			boolean horizontal) {
		if (vertical) {
			if (yVel < -.5) {
				yVel = (-yVel * 0.5f);
			} else {
				yVel = 0;
				unsink(platform);
			}
		}

		if (horizontal) {
			xVel = -xVel;
		}
	}
	/** Pushes the particle when hitting something */
	public void bounce(boolean vertical, boolean horizontal) {
		if (vertical) {
			if (yVel < -.5) {
				yVel = (-yVel * 0.5f);
			} else {
				yVel = 0;
			}
		}

		if (horizontal) {
			xVel = -xVel;
		}
	}

	/** Stops the particle, horizontally or vertically or both */
	public void stop(boolean horizontal, boolean vertical) {
		if (horizontal) {
			xVel = 0;
		}
		if (vertical) {
			yVel = 0;
		}
	}

	/**
	 * Decreases the vertical velocity of the particle if the particle doesn't
	 * explicitely have a drop
	 */
	protected void fall(float delta) {
		yVel -= 10 * delta;
	}

	/** Hit the particle and clap it */
	public void hit(float xVel) {
		// Die
		hit = true;
		this.yVel = random.nextInt(7);
		this.xVel = xVel;
	}

	/** Pulls the particle from the floor to fix a jitter effect */
	private void unsink(Platform platform) {
		this.setY(platform.getY() + platform.getHeight());
	}

}

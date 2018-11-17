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
 * Particle that emmits when the ground is interacted with.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class GroundParticle extends Particle {

	private Random random = new Random();
	private ParticleCollision collision = new ParticleCollision(this);
	private int removeCooldown = CurrentSettings
			.getInstance().groundParticleLifetime;
	private int scale = random.nextInt(5) + 1;
	private int side = 0; // 0 = left; 1 = right;
	private boolean draw = true;
	private boolean lightOrDark = random.nextBoolean();

	/** Default instantiable constructor */
	public GroundParticle() {
		float bounce = 0.3f;
		this.side = random.nextInt(1);
		this.xVel = bounce * random.nextInt(10);
		this.yVel = bounce * random.nextInt(10);
		this.setWidth(scale);
		this.setHeight(scale);
	}

	@Override
	public void update(float delta) {

		super.update(delta);

		/* Move */
		xVel += 10 * ((0 - xVel) / 2) * delta;
		if (side == 0) { // Left
			this.setX(this.getX() + xVel);
		} else { // Right
			this.setX(this.getX() - xVel);
		}

		/* Flash when on screen for a while */
		removeCooldown--;
		if (removeCooldown <= 20 && removeCooldown % 4 == 0) {
			draw = !draw;
		}

		/* Remove */
		if (removeCooldown <= 0 || bounds.y <= -100) {
			GameManager.getInstance().particleContainer.remove(this);
		}

		/* Fall */
		fall(delta);
		this.setY(this.getY() + yVel
				+ PlatformGenerator.getInstance().getSpeed());

		/* Update collision */
		collision.update(delta);

	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		if (draw) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer
					.setColor(lightOrDark ? Color.DARK_GRAY : Color.LIGHT_GRAY);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			shapeRenderer.end();
			batch.begin();
		}

	}

}

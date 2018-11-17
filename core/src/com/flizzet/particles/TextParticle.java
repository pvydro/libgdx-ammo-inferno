package com.flizzet.particles;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.utils.FontUtils;

/**
 * Particle with text.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TextParticle extends Particle {

	private String message;
	private float yVel = 4;
	private int removeCooldown = 80;
	private boolean draw = true;

	/** Default instantiable constructor */
	public TextParticle(String message) {
		this.message = message;
	}

	@Override
	public void update(float delta) {
		/* Decrease flaot speed */
		yVel += 5 * ((0 - yVel) / 2) * delta;

		/* Flash when on screen for a while */
		removeCooldown--;
		if (removeCooldown <= 20 && removeCooldown % 4 == 0) {
			draw = !draw;
		}

		/* Removal */
		if (removeCooldown <= 0) {
			GameManager.getInstance().particleContainer.remove(this);
		}

		/* Apply speed */
		this.setY(this.getY() + yVel);
		this.setY(this.getY() + PlatformGenerator.getInstance().getSpeed());
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		if (draw) {
			BitmapFont font = FontUtils.UPHEAVAL_PARTICLE_SMALL;
			font.setUseIntegerPositions(false);
			font.draw(batch, message, bounds.x, bounds.y);
		}
	}

}

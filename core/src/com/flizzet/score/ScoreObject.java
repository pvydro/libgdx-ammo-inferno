package com.flizzet.score;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.player.Player;

/**
 * Abstract score class for concrete scores to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
abstract class ScoreObject extends Entity {

	protected Texture image;
	private ScoreCollision collision = new ScoreCollision(this);
	private Random random = new Random();
	private Player player = Player.getInstance();

	private float yVel = 0;
	private float xVel = 0;

	/** Default instantiable constructor */
	public ScoreObject() {
		yVel = -random.nextFloat() * 20;
	}

	@Override
	public void update(float delta) {
		/* Ease velocities towards player */
		xVel = 10 * ((player.getCenterX() - getX()) / 2) * delta;
		yVel = 10 * ((player.getCenterY() - getY()) / 2) * delta;

		/* Add speeds */
		this.setX(this.getX() + xVel);
		this.setY(this.getY() + yVel);

		collision.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
	}

	/** Sets dimensions to the image width and height */
	protected void adjustBoundsToImage() {
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
	}

}

package com.flizzet.ingamegui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.player.Player;

/**
 * Health bar that appears above npc heads.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HealthBar extends Entity {

	private Entity target;

	private HealthHeart heart1 = new HealthHeart();
	private HealthHeart heart2 = new HealthHeart();
	private HealthHeart heart3 = new HealthHeart();
	private HealthHeart heart4 = new HealthHeart();

	private int health = 3;

	private int showCooldown = 6;

	/** Default instantiable constructor */
	public HealthBar(Entity target) {
		this.target = target;
	}

	@Override
	public void update(float delta) {

		/* Disabling show until the target is correctly positioned */
		if (showCooldown > 0) {
			showCooldown--;
			this.bounds.x = target.getX();
			this.bounds.y = target.getY();
		}

		if (heart2 == null)
			return; // Stop the function if no health

		/* Locals */
		float center = ((target.getCenterX() - (heart2.getWidth() / 2))); // Player
																			// center
		float heartX = heart2.getX()
				+ 60 * ((center - heart2.getX()) / 2) * delta; // Center base
																// heart
		float heartY = heart2.getY() + 60
				* ((target.getY() + target.getHeight() + 10 - heart2.getY())
						/ 2)
				* delta; // Constant heart y

		/* Update positions of all hearts */
		heart2.setX(heartX);
		heart2.setY(heartY);
		switch (health) {
			case 4:
				heart1.setX(heartX - 8);
				heart1.setY(heartY);
				heart3.setX(heartX + 8);
				heart3.setY(heartY);
				heart4.setX(heartX);
				heart4.setY(heartY + 8);
			case 3:
				heart1.setX(heartX - 8);
				heart1.setY(heartY);
				heart3.setX(heartX + 8);
				heart3.setY(heartY);
				break;
			case 2:
				heart1.setX(heartX - 4);
				heart1.setY(heartY);
				heart3.setX(heartX + 4);
				heart3.setY(heartY);
				break;
			case 1:
				heart1.setX(heartX);
				heart1.setY(heartY);
				break;
			default:
				break;
		}

		/* Add animation to middle heart if it's the player */
		if (target instanceof Player) {
			if (heart2 != null)
				heart2.update(delta);
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		/* Don't show until the target is correctly positioned */
		if (showCooldown > 0) {
			return;
		}

		if (health >= 1)
			heart1.render(batch);
		if (health >= 2)
			heart3.render(batch);
		if (health >= 3)
			heart2.render(batch);
		if (health >= 4)
			heart4.render(batch);

	}

	/** Respawn */
	public void reset() {
		health = 3;
	}

	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	public int getHealth() {
		return this.health;
	}

}

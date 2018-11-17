package com.flizzet.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 * Lets the player know the new Entity is an enemy.
 *
 * @author Pedro Dutra (2018)
 * @version 1.0
 */
public class EnemyIndicator extends Entity {

	private final Enemy enemy;
	private final Texture image;
	private float removalCooldown = 5;
	private float alpha = 1.0f;
	
	/** Default instantiable constructor */
	public EnemyIndicator(Enemy enemy) {
		this.image = GameManager.getInstance().assetManager
				.get("gui/inGame/enemyIndicator.png");
		
		this.adjustBoundsToImage(image);
		
		this.enemy = enemy;
	}

	@Override
	public void update(float delta) {
		/* Positioning */
		this.setX(enemy.getCenterX() - (this.getWidth() / 2));
		this.setY(enemy.getCenterY() - (this.getHeight() / 2));
		
		/* Removal */
		removalCooldown -= delta;
		if (removalCooldown <= 0) {
			alpha -= (delta * 2);
			if (alpha <= 0) {
				remove();
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		/* Apply opacity */
		Color c = batch.getColor();
		batch.setColor(new Color(c.r, c.g, c.b, this.alpha));
		
		/* Draw */
		batch.draw(this.image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		/* Reset color */
		batch.setColor(c);
	}
	
	private void remove() {
		GameManager.getInstance().entityContainer.remove(this);
	}

	public void setRemovalCooldown(float newRemovalCooldown) {
		this.removalCooldown = newRemovalCooldown;
	}
	
}

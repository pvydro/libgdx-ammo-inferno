package com.flizzet.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.entities.Entity;
import com.flizzet.lighting.BulletLight;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Bullet projectile that releases from the player weapon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Bullet extends Entity {

	private float rotation;
	private float radians;
	private float originX;
	private float originY;
	private float speed = 5;
	private Texture image;
	private Rectangle collisionBounds;
	private BulletCollision collision;
	private BulletLight light;

	private ShapeRenderer shapeRenderer;

	/** Default instantiable constructor */
	public Bullet(float x, float y, float rotation) {
		this.image = GameManager.getInstance().assetManager
				.get("items/bullet.png", Texture.class);
		adjustBoundsToImage(image);

		this.bounds.x = x - (bounds.width / 2);
		this.bounds.y = y - (bounds.height / 2);
		this.rotation = rotation;
		this.radians = (float) Math.toRadians(rotation);

		collisionBounds = new Rectangle(0, 0, 0, 0);

		collision = new BulletCollision(this);
		if (CurrentSettings.getInstance().bulletLight) {
			light = new BulletLight(this);
		}

		if (CurrentSettings.getInstance().showCollisions) {
			shapeRenderer = new ShapeRenderer();
		}

	}

	@Override
	public void update(float delta) {

		/* Setting center for rotation */
		originX = bounds.width / 2;
		originY = bounds.height / 2;

		/* Moving in direction of rotation */
		bounds.x += (speed * Math.cos(radians));
		bounds.y += (speed * Math.sin(radians));

		/* Moving down with the platforms */
		// bounds.y += (PlatformGenerator.getInstance().getSpeed());

		/* Update collision */
		collisionBounds.width = 5;
		collisionBounds.height = 5;
		collisionBounds.x = bounds.x + originX - (collisionBounds.width / 2);
		collisionBounds.y = bounds.y + originY - (collisionBounds.height / 2);

		collision.update(delta);
		if (light != null)
			light.update(delta);

	}

	@Override
	public void render(SpriteBatch batch) {

		batch.draw(image, bounds.x, bounds.y, originX, originY, bounds.width,
				bounds.height, 1, 1, rotation, 0, 0, (int) bounds.width,
				(int) bounds.height, false, false);

		if (CurrentSettings.getInstance().showCollisions) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.rect(collisionBounds.x, collisionBounds.y,
					collisionBounds.width, collisionBounds.height);
			shapeRenderer.end();
			batch.begin();
		}

		if (light != null)
			light.render(batch);

	}

	/* Ricochets the bullet */
	public void bounce() {
		this.radians = -radians;
		this.rotation = -rotation;
	}

	/** Removes bullet from the screen */
	public void remove() {
		GameManager.getInstance().entityContainer.remove(this);
	}

	public float getXVel() {
		return (float) (speed * Math.cos(radians));
	}
	public Rectangle getCollisionBounds() {
		return this.collisionBounds;
	}

}

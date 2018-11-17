package com.flizzet.sawenemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.enemies.EnemyCollision;
import com.flizzet.interfaces.Renderable;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.ShapeUtils;

/**
 * Handles collision for the saw enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SawEnemyCollision extends EnemyCollision implements Renderable {

	private Polygon collisionBounds;
	private Polygon playerBounds;
	private SawEnemy enemy;
	private ShapeRenderer shapeRenderer;

	/** Default instantiable constructor */
	public SawEnemyCollision(SawEnemy enemy) {
		this.enemy = enemy;
		Rectangle bounds = enemy.getBounds();
		collisionBounds = ShapeUtils.rectToPolygon(bounds);
		playerBounds = ShapeUtils
				.rectToPolygon(Player.getInstance().getCollisionBounds());

		if (CurrentSettings.getInstance().showCollisions) {
			shapeRenderer = new ShapeRenderer();
		}
	}

	@Override
	public void update(float delta) {
		collisionBounds.setPosition(enemy.getBounds().x, enemy.getBounds().y);
		collisionBounds.setRotation(enemy.getRotation());
		playerBounds.setPosition(Player.getInstance().getX(),
				Player.getInstance().getY());

		if (Intersector.overlapConvexPolygons(collisionBounds, playerBounds)) {
			Player.getInstance().hit();
			if (CurrentSettings.getInstance().blood) {
				GameManager.getInstance().particleFunctions.addBloodParticle(
						playerBounds.getX(), playerBounds.getY(),
						CurrentSettings.getInstance().bloodAmount);
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (CurrentSettings.getInstance().showCollisions) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.GOLDENROD); // lol golden rod
			shapeRenderer.polygon(collisionBounds.getTransformedVertices());
			shapeRenderer.end();
			batch.begin();
		}
	}

}

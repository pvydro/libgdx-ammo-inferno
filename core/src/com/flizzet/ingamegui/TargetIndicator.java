package com.flizzet.ingamegui;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.camera.MainCamera;
import com.flizzet.enemies.Enemy;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;

/**
 * Indicator to represent what the player weapon is currently aiming towards.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TargetIndicator extends GuiComponent {

	private Enemy target;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Random random = new Random();
	private float alpha = 1.0f;
	private float fillAlpha = 0.5f;
	private float bottomY;
	private float topY;
	private float rightX;
	private float leftX;
	private static final int MARGIN = 5;

	private Texture[] images = new Texture[]{
			GameManager.getInstance().assetManager
					.get("gui/inGame/targetTopLeft.png"), 		// Top Left - 0
			GameManager.getInstance().assetManager
					.get("gui/inGame/targetTopRight.png"), 		// Top Right - 1
			GameManager.getInstance().assetManager
					.get("gui/inGame/targetBottomRight.png"), 	// Bottom Right - 2
			GameManager.getInstance().assetManager
					.get("gui/inGame/targetBottomLeft.png") 	// Bottom Left - 3
	};

	private float iconWidth = images[0].getWidth();
	private float iconHeight = images[0].getHeight();

	/** Default instantiable constructor */
	public TargetIndicator() {
		super(0, 0);
	}

	@Override
	public void update(float delta) {
		target = Player.getInstance().getWeapon().getTarget();

		if (target != null) {
			if (target.isDead()) {
				alpha -= 0.1f;
			} else {
				alpha = 1.0f;
			}

			/* Setting corner positions */
			float targetTopY = target.getCollisionBounds().y
					+ target.getCollisionBounds().height - iconHeight + MARGIN;
			float targetBottomY = target.getCollisionBounds().y - MARGIN;
			float targetLeftX = target.getCollisionBounds().x - MARGIN;
			float targetRightX = target.getCollisionBounds().x
					+ target.getCollisionBounds().width + MARGIN - iconWidth;

			topY += (targetTopY - topY) / 5;
			bottomY += (targetBottomY - bottomY) / 5;
			leftX += (targetLeftX - leftX) / 5;
			rightX += (targetRightX - rightX) / 5;
			fillAlpha = random.nextFloat() / 10;
		} else {
			/* Set all positions to center */
			Vector2 center = new Vector2(MainCamera.getInstance().getCenterX(),
					MainCamera.getInstance().getCenterY());
			leftX += (center.x - leftX) / 5;
			rightX += (center.x - rightX) / 5;
			topY += (center.y - topY) / 5;
			bottomY += (center.y - bottomY) / 5;

			if (alpha > 0)
				alpha -= 0.1f;
			if (fillAlpha > 0)
				fillAlpha -= 0.1f;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Draw fill */
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		float fillWidth = rightX - leftX + iconWidth;
		float fillHeight = topY - bottomY + iconHeight;
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.CYAN.r, Color.CYAN.g, Color.CYAN.b,
				fillAlpha);
		shapeRenderer.rect(leftX, bottomY, fillWidth, fillHeight);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		batch.begin();

		/* Draw corners */
		Color tmp = batch.getColor();
		batch.setColor(new Color(tmp.r, tmp.g, tmp.b, alpha));
		batch.draw(images[0], leftX, topY);
		batch.draw(images[1], rightX, topY);
		batch.draw(images[2], rightX, bottomY);
		batch.draw(images[3], leftX, bottomY);
		batch.setColor(tmp);
	}

	public void setTarget(Enemy newTarget) {
		this.target = newTarget;
		alpha = 1.0f;
	}

	@Override
	public void triggered() {
	}

}

package com.flizzet.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 * Adds upscrolling walls to the side of the map.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MapForeground extends Entity {

	private static final MapForeground INSTANCE = new MapForeground();

	private Texture imageTopLeft;
	private Texture imageMiddleLeft;
	private Texture imageBottomLeft;

	private Texture imageTopRight;
	private Texture imageMiddleRight;
	private Texture imageBottomRight;

	private float leftX = 0;
	private float rightX = MainCamera.getInstance().getWidth();

	private float topY = 450;
	private float midY = -50;
	private float bottomY = -550;

	private float bgSpeed = -2;

	ShapeRenderer shapeRenderer = new ShapeRenderer();

	/** Returns an instance of the MapBackground */
	public static MapForeground getInstance() {
		return INSTANCE;
	}

	/** Default instantiable constructor */
	public MapForeground() {
		setImages("map/foregrounds/fgLowerLeft.png",
				"map/foregrounds/fgLowerRight.png");

		rightX -= imageTopRight.getWidth();
	}

	@Override
	public void update(float delta) {
		topY += bgSpeed;
		midY += bgSpeed;
		bottomY += bgSpeed;

		/* Positioning */
		if (bottomY <= -1050) {
			resetPositions();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(imageTopLeft, leftX, topY);
		batch.draw(imageMiddleLeft, leftX, midY);
		batch.draw(imageBottomLeft, leftX, bottomY);

		batch.draw(imageTopRight, rightX, topY);
		batch.draw(imageMiddleRight, rightX, midY);
		batch.draw(imageBottomRight, rightX, bottomY);

		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled); // Draw a stupid rectangle to fix
												// bg bleeding
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(rightX + imageTopRight.getWidth(), -50, 100,
				MainCamera.getInstance().getHeight() + 50);
		shapeRenderer.end();

		batch.begin();
	}

	/** Resets image positions */
	public void resetPositions() {
		topY = 450;
		midY = -50;
		bottomY = -550;
	}

	/** Respawn */
	public void reset() {
		resetPositions();
	}

	/** Sets new images for the foreground */
	public void setImages(String imageLeftDir, String imageRightDir) {
		imageTopLeft = imageMiddleLeft = imageBottomLeft = GameManager
				.getInstance().assetManager.get(imageLeftDir, Texture.class);
		imageTopRight = imageMiddleRight = imageBottomRight = GameManager
				.getInstance().assetManager.get(imageRightDir, Texture.class);
	}

	public float getLeftX() {
		return this.leftX;
	}
	public float getRightX() {
		return this.rightX;
	}

}

package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.main.GameManager;
import com.flizzet.sound.Sounds;

/**
 * Plays between states.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TransitionComponent extends GuiComponent {

	private Texture leftImage;
	private Texture rightImage;

	private float startLeftX;
	private float startRightX;
	private float leftX;
	private float rightX;
	private float leftTargetX;
	private float rightTargetX;
	private int state = 0; // 0 = Going to center (fade in), 1 = Returning to start (fade out)

	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private static boolean projectionMatrixSet;
	private float darkness = 0;

	float currenttime = 10;
	float duration = 100;
	float changeinval = -50;

	/** Default instantiable constructor */
	public TransitionComponent(float x, float y) {
		super(x, y);

		/* Setting images */
		leftImage = GameManager.getInstance().assetManager
				.get("gui/transition/leftDoor.png", Texture.class);
		rightImage = GameManager.getInstance().assetManager
				.get("gui/transition/rightDoor.png", Texture.class);

		/* Locals */
		float leftWidth = leftImage.getWidth();
		float rightWidth = rightImage.getWidth();
		float fullWidth = leftWidth + rightWidth;
		// Ternary to set the fullHeight to the tallest door
		float fullHeight = (leftImage.getHeight() > rightImage.getHeight())
				? leftImage.getHeight()
				: rightImage.getHeight();

		/* Set positions */
		float newCenterX = (GameManager.getInstance().getCamera().getWidth()
				/ 2);
		float newCenterY = (GameManager.getInstance().getCamera().getHeight()
				/ 2);
		float leftSide = newCenterX - (fullWidth / 2); // Set the left side to
														// the edge of the new
														// center
		float rightSide = newCenterX + (fullWidth / 2); // Set the right side to
														// the edge of the new
														// center
		startLeftX = leftSide - leftWidth; // Set start position of the left
											// door
		startRightX = rightSide; // Set the start position of the right door
		leftX = startLeftX; // Set the position of the left door
		rightX = startRightX; // Set the position of the right door
		leftTargetX = newCenterX - leftWidth; // Set the position eased into by
												// the left door
		rightTargetX = newCenterX; // Set the position eased into by the right
									// door

		this.setY(newCenterY - (fullHeight / 2));
		this.setX(leftX);

		/* Play close sound */
		Sounds.play(Sounds.getInstance().transitionCloseSound, 1.0f);
	}

	@Override
	public void update(float delta) {

		/* Easing */
		if (state == 0) {
			if (leftX < (leftTargetX - .5f) || rightX > (rightTargetX + .5f)) {
				leftX += (leftTargetX - leftX) / 7;
				rightX += (rightTargetX - rightX) / 7;

				darkness += (1 - darkness) / 15;
			} else {
				/* Play open sound */
				Sounds.play(Sounds.getInstance().transitionOpenSound, 1.0f);
				triggered();
				state = 1;
			}
		} else {
			if (leftX > (startLeftX + 1) || rightX < (rightTargetX - 1)) {

				leftX += (startLeftX - leftX) / 10;
				rightX += (startRightX - rightX) / 10;

				darkness += (0 - darkness) / 3;
			} else {
				GameManager.getInstance().transitionContainer
						.removeFromTransitions(this);
			}
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		/* Locals */
		float rectWidth = (rightX + rightImage.getWidth()) - leftX;

		/* Dark background */

		if (!projectionMatrixSet) {
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		}
		batch.end(); // End batch to begin ShapeRenderer drawing

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		/* Draw dark background overlay */

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0f, 0f, 0f, darkness));
		shapeRenderer.rect(leftX, bounds.y, rectWidth, 1000);
		shapeRenderer.end();

		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin(); // Re-begin batch with completion of shape drawing

		/* Draw doors */
		batch.draw(leftImage, leftX, bounds.y);
		batch.draw(rightImage, rightX, bounds.y);

	}

	@Override
	public void triggered() {
	}

}

package com.flizzet.ingamegui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;

/**
 * Joystick controller to control player movement.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class JoystickComponent extends GuiComponent {

	private String baseReference = "gui/inGame/movement/joystick/";
	private Texture currentImage;
	private Player player = Player.getInstance();

	private boolean mouseDown = false;
	private float initialX;
	private float initialY;
	private float xDifference = 0;
	private float yDifference = 0;
	private int xDirection = 0;
	private int yDirection = 0;
	private static final int TRIGGER_SPACING = 20;

	/** Default instantiable constructor */
	public JoystickComponent() {
		super(0, 0);
		adjustBoundsToImage();
	}

	@Override
	public void update(float delta) {

		/*
		 * * Initial controller tap and position setting
		 */
		/* Single call */
		if (Gdx.input.isTouched()) {
			if (!mouseDown) {
				mouseDown = true;
				initialX = MainCamera.getInstance().getMousePos().x;
				initialY = MainCamera.getInstance().getMousePos().y;
			}
			xDifference = MainCamera.getInstance().getMousePos().x - initialX;
			yDifference = MainCamera.getInstance().getMousePos().y - initialY;
		} else {
			mouseDown = false;
			xDifference = 0;
			yDifference = 0;
		}

		/*
		 * * Figuring out what the user is doing with the controller
		 */
		/* Getting horizontal and vertical positioning and calling functions */
		/* Horizontal */
		if (xDifference > TRIGGER_SPACING) {
			xDirection = 1;
			player.getController().setRightPushed(true);
			player.getController().setLeftPushed(false);
		} else if (xDifference < -TRIGGER_SPACING) {
			xDirection = -1;
			player.getController().setRightPushed(false);
			player.getController().setLeftPushed(true);
		} else {
			xDirection = 0;
			player.getController().setRightPushed(false);
			player.getController().setLeftPushed(false);
		}
		/* Vertical */
		if (yDifference > TRIGGER_SPACING) {
			yDirection = 1;
			player.getController().setUpPushed(true);
		} else if (yDifference < -TRIGGER_SPACING) {
			yDirection = -1;
		} else {
			yDirection = 0;
		}

		/*
		 * * Getting a new image based on the position
		 */
		/* Create a new reference using the newly found directions */
		String newReference = "joystick";
		String verticalReference = "";
		String horizontalReference = "";
		/* Find a vertical keyword */
		if (yDirection == 1) {
			verticalReference = "Top";
		} else if (yDirection == -1) {
			verticalReference = "Bottom";
		} else if (yDirection == 0) {
			verticalReference = "Mid";
		}
		/* Find a horizontal keyword */
		if (xDirection == 1) {
			horizontalReference = "Right";
		} else if (xDirection == -1) {
			horizontalReference = "Left";
		} else if (xDirection == 0) {
			horizontalReference = "";
		}
		/* Put together the new reference */
		newReference = newReference + verticalReference + horizontalReference
				+ ".png";
		/* Connect it to the image */
		currentImage = GameManager.getInstance().assetManager
				.get(baseReference + newReference);
	}

	@Override
	public void render(SpriteBatch batch) {
		float shakeXOffset = (MainCamera.getInstance().getCamera().position.x
				- MainCamera.getInstance().getTargetX());
		float shakeYOffset = (MainCamera.getInstance().getCamera().position.y
				- MainCamera.getInstance().getTargetY());

		batch.draw(currentImage, bounds.x + shakeXOffset,
				bounds.y + shakeYOffset, bounds.width, bounds.height);
	}

	@Override
	public void triggered() {

	}

	/** Sets dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(GameManager.getInstance().assetManager
				.get(baseReference + "joystickMid.png", Texture.class)
				.getWidth());
		this.setHeight(GameManager.getInstance().assetManager
				.get(baseReference + "joystickMid.png", Texture.class)
				.getHeight());
	}

}

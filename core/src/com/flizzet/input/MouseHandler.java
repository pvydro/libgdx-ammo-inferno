package com.flizzet.input;

import com.badlogic.gdx.Gdx;
import com.flizzet.main.GameManager;

/**
 * Gets mouse information.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MouseHandler {

	/** Default instantiable constructor */
	private MouseHandler() {
		throw new AssertionError();
	}

	/** Get mouse x with camera */
	public static float getMouseX() {
		return GameManager.getInstance().getCamera().getMousePos().x;
	}

	/** Get mouse y with camera */
	public static float getMouseY() {
		return GameManager.getInstance().getCamera().getMousePos().y;
	}

	/** Get mouse pushed */
	public static boolean isMousePushed() {
		return Gdx.input.isTouched();
	}

}

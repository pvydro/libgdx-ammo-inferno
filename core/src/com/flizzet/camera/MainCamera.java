package com.flizzet.camera;

import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Main OrthographicCamera.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MainCamera extends Camera {

	private static final MainCamera INSTANCE = new MainCamera();
	private float targetY = 200;
	private float targetX = 120;

	/** Private constructor for single use */
	private MainCamera() {
		this.setHeight(400);
	}

	@Override
	public void createCamera() {
		super.createCamera();
		viewport = new ExtendViewport(width, height, cam);
		viewport.apply();
	}

	/** Returns an instance of MenuCamera */
	public static MainCamera getInstance() {
		return INSTANCE;
	}

	public float getTargetY() {
		return this.targetY;
	}
	public float getTargetX() {
		return this.targetX;
	}

	public void setTargetX(float newTargetX) {
		this.targetX = newTargetX;
	}
	public void setTargetY(float newTargetY) {
		this.targetY = newTargetY;
	}

}

package com.flizzet.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Abstract class for inheritance from concrete cameras.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public abstract class Camera {

	protected OrthographicCamera cam;
	protected Viewport viewport;

	protected float height = 400;
	protected float pixelsPerUnit = Gdx.graphics.getHeight() / height;
	protected float width = Gdx.graphics.getWidth() / pixelsPerUnit;

	private float xShakeAmt = 0;
	private float yShakeAmt = 0;

	/** Creates camera */
	public void createCamera() {
		cam = new OrthographicCamera(width, height);
		cam.position.set(width / 2, height / 2, 0);
	}

	/** Returns Menu FillViewport */
	public OrthographicCamera getCamera() {
		return cam;
	}

	public void update(float delta) {
		cam.update();
		xShakeAmt += 10 * ((0 - xShakeAmt) / 2) * delta;
		yShakeAmt += 10 * ((0 - yShakeAmt) / 2) * delta;
	}

	/** Set height or "scale" */
	public void setHeight(float newHeight) {
		this.height = newHeight;
	}

	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public Vector3 getMousePos() {
		return cam
				.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	public Vector3 getMousePos(int pointer) {
		return cam.unproject(new Vector3(Gdx.input.getX(pointer),
				Gdx.input.getY(pointer), 0));
	}
	public Matrix4 getCombinedMatrix() {
		return cam.combined;
	}
	public float getCenterX() {
		return width / 2;
	}
	public float getCenterY() {
		return height / 2;
	}
	public float getXShakeAmt() {
		return xShakeAmt;
	}
	public float getYShakeAmt() {
		return yShakeAmt;
	}
	/** Returns the pixels per unit with the camera zoom */
	public float getPpu() {
		return this.pixelsPerUnit;
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		cam.position.set(this.width / 2, this.height / 2, 0);
	}

	public void shake(float amount, boolean horizontal, boolean vertical) {
		if (horizontal) {
			xShakeAmt += amount;
		}
		if (vertical) {
			yShakeAmt += amount;
		}
	}

}
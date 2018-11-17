/**
 * 
 */
package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Barebones Gui element, similar to an Entity.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see Entity
 */
public abstract class GuiComponent {

	protected Rectangle bounds; // Stores bounds of components

	/** Default constructor for abstraction */
	public GuiComponent(float x, float y) {
		bounds = new Rectangle(x, y, 16, 16);
	}

	/** Update method. */
	public abstract void update(float delta);

	/** Render method. */
	public abstract void render(SpriteBatch batch);

	/**
	 * Should be called when main event is triggered; for example, button is
	 * clicked. This will be extended by concrete components.
	 */
	public abstract void triggered();

	public void setWidth(float w) {
		bounds.setWidth(w);
	}

	public void setHeight(float h) {
		bounds.setHeight(h);
	}

	public float getWidth() {
		return bounds.getWidth();
	}

	public float getHeight() {
		return bounds.getHeight();
	}

	public void setX(float x) {
		bounds.setX(x);
	}

	public void setY(float y) {
		bounds.setY(y);
	}

	public float getX() {
		return bounds.getX();
	}

	public float getY() {
		return bounds.getY();
	}

	public float getCenterX() {
		return bounds.x + (bounds.width / 2);
	}

	public float getCenterY() {
		return bounds.y + (bounds.height / 2);
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

}

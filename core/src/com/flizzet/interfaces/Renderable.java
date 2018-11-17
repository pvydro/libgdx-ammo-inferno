package com.flizzet.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Allows the draw function to be added to non-entity or gui elements
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Renderable {

	/** Render the object */
	public abstract void render(SpriteBatch batch);

}

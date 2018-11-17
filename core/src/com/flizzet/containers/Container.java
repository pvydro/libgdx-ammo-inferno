package com.flizzet.containers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class for concrete containers to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also GameStateManager
 */
public abstract class Container {

	/** Updates all objects in the containers */
	public abstract void update(float delta);

	/** Renders all objects in the containers */
	public abstract void render(SpriteBatch batch);

	/** Called on application close */
	public abstract void dispose();

}

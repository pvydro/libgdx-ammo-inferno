package com.flizzet.interfaces;

/**
 * Allows the update function to be added to non-entity or gui elements.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Updatable {

    /** Update the object */
    public abstract void update(float delta);
    
}

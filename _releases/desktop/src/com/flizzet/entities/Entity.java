package com.flizzet.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    
    protected Rectangle bounds = new Rectangle(0, 0, 16, 16);
    
    /** Updates all elements of the entity */
    public abstract void update(float delta);
    /** Renders all elements of the entity */
    public abstract void render(SpriteBatch batch);
    
    public Rectangle getBounds()		{ return bounds; }
    public float getX() 			{ return bounds.x; }
    public float getY() 			{ return bounds.y; }
    public float getCenterX()			{ return bounds.x + (bounds.width / 2); }
    public float getCenterY()			{ return bounds.y + (bounds.height / 2); }
    public float getWidth() 			{ return bounds.width; }
    public float getHeight() 			{ return bounds.height; }
    public void setX(float newX) 		{ this.bounds.x = newX; }
    public void setY(float newY) 		{ this.bounds.y = newY; }
    public void setWidth(float newWidth) 	{ this.bounds.width = newWidth; }
    public void setHeight(float newHeight) 	{ this.bounds.height = newHeight; }
    
    /** Sets bounds to image width and height */
    public void adjustBoundsToImage(Texture image) {
	this.setWidth(image.getWidth());
	this.setHeight(image.getHeight());
    }
    /** Sets bounds to image width and height */
    protected void adjustBoundsToImage(TextureRegion image) {
	this.setWidth(image.getRegionWidth());
	this.setHeight(image.getRegionHeight());
    }
    
}

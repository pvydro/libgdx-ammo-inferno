package com.flizzet.lighting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;
import com.flizzet.interfaces.Renderable;

/**
 * Color overlay to set a tone/ambience to the screen.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Ambience implements Renderable {
   
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Color ambienceColor = new Color(0.5f, 0.2f, 0.4f, 0.1f);
    private boolean aboveGui = false;
    private boolean enabled = true;
    
    /** Default instantiable constructor */
    public Ambience() {}
    
    @Override
    public void render(SpriteBatch batch) {
	
	if (enabled) {
	    batch.end();
	    Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setColor(ambienceColor);
	    shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    
	    /* Draw surrounding boxes */
	    shapeRenderer.setColor(Color.BLACK);
	    /* Left */
	    shapeRenderer.rect(-200, 0, 200, MainCamera.getInstance().getHeight());
	    /* Right */
	    shapeRenderer.rect(MainCamera.getInstance().getWidth(), 0, 200, MainCamera.getInstance().getHeight());
	    
	    shapeRenderer.end();
	    Gdx.gl.glDisable(GL20.GL_BLEND);
	    batch.begin();
	}
	
    }
    
    /** Set the ambience color */
    public void setColor(Color newColor) {
	this.ambienceColor = newColor;
    }
    
    /** Set the ambience alpha */
    public void setAlpha(float newAlpha) {
	this.ambienceColor = new Color(this.ambienceColor.r, this.ambienceColor.g, this.ambienceColor.b, newAlpha);
    }
    
    /** Set the ambience to affect gui or not */
    public void setAboveGui(boolean aboveGui) {
	this.aboveGui = aboveGui;
    }
    
    /** Disables drawing of the ambience */
    public void disable() {
	this.enabled = false;
    }
    
    /** Enables drawing of the ambience */
    public void enable() {
	this.enabled = true;
    }
    
    public boolean isAboveGui()		{ return this.aboveGui; }
    public Color getColor()		{ return this.ambienceColor; }

}

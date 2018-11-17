package com.flizzet.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 * Adds upscrolling brick background to the map.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MapBackground extends Entity {

    private static final MapBackground INSTANCE = new MapBackground();
    
    private Texture imageTop;
    private Texture imageMiddle;
    private Texture imageBottom;
    
    private float topY = 450;
    private float midY = -50;
    private float bottomY = -550;
    
    private float bgSpeed = -.5f;
    
    /** Returns an instance of the MapBackground */
    public static MapBackground getInstance() {
	return INSTANCE;
    }

    /** Default instantiable constructor */
    public MapBackground() {
	setImage("map/backgrounds/bgLower.png");
    }

    @Override
    public void update(float delta) {
	
	topY += bgSpeed;
	midY += bgSpeed;
	bottomY += bgSpeed;
	
	/* Positioning */
	if (bottomY <= -1050) {
	    resetPositions();
	}

    }

    @Override
    public void render(SpriteBatch batch) {
	
	batch.draw(imageTop, bounds.x, topY);
	batch.draw(imageMiddle, bounds.x, midY);
	batch.draw(imageBottom, bounds.x, bottomY);
	
    }

    /** Resets image positions */
    public void resetPositions() {
	topY = 450;
	midY = -50;
	bottomY = -550;
    }
    
    /** Respawn */
    public void reset() {
	resetPositions();
    }
    
    /** Sets a new image for the map background */
    public void setImage(String imageDir) {
	imageTop = imageMiddle = imageBottom = GameManager.getInstance().assetManager.get(imageDir, Texture.class);
    }
    
}

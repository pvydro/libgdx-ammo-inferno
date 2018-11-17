package com.flizzet.lighting;

import java.util.Random;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Abstract light class for concrete lights
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
abstract class Light extends Entity {

    protected Texture lightOverlay;
    protected Entity target;
    protected GuiComponent guiTarget;
    protected boolean jitterDimension = false;
    protected boolean jitterPosition = false;
    protected boolean jitterHorizontally = false;
    protected boolean jitterVertically = false;
    protected int jitterPositionAmt = 5;
    protected int jitterDimensionAmt = 5;
    
    protected int jitterX, jitterY;
    protected int jitterWidth, jitterHeight;
    protected Random random = new Random();
    
    private Texture hardLightImage;
    
    /** Default instantiable constructor
     *  Takes a Texture as a light mask */
    public Light() {
	hardLightImage = GameManager.getInstance().assetManager.get("lights/hardLight.png", Texture.class);
    }
    
    @Override
    public void update(float delta) {
	/* Set position to the center of the target */
	if (guiTarget == null) {
	    this.setX(target.getCenterX() - (this.getWidth() / 2));
	    this.setY(target.getCenterY() - (this.getWidth() / 2));
	} else {
	    this.setX(guiTarget.getCenterX() - (this.getWidth() / 2));
	    this.setY(guiTarget.getCenterY() - (this.getWidth() / 2));
	}
	
	/* Set jitter horizontally */
	if (jitterHorizontally) {
	    /* Set jitter position */
	    if (jitterPosition) {
		jitterX = random.nextBoolean() ? random.nextInt(jitterPositionAmt) : -random.nextInt(jitterPositionAmt);
	    }
	    /* Set jitter dimension */
	    if (jitterDimension) {
		jitterWidth = random.nextBoolean() ? random.nextInt(jitterDimensionAmt) : -random.nextInt(jitterDimensionAmt);
	    }
	} else {
	    jitterX = 0;
	    jitterWidth = 0;
	}
	
	/* Set jitter vertically */
	if (jitterVertically) {
	    /* Set jitter position */
	    if (jitterPosition) {
		jitterY = random.nextBoolean() ? random.nextInt(jitterPositionAmt) : -random.nextInt(jitterPositionAmt);
	    }
	    /* Set jitter dimension */
	    if (jitterDimension) {
		jitterWidth = random.nextBoolean() ? random.nextInt(jitterDimensionAmt) : -random.nextInt(jitterDimensionAmt);
	    }
	} else {
	    jitterY = 0;
	    jitterHeight = 0;
	}
	
	/* Disable jittering if the setting is disabled */
	if (!CurrentSettings.getInstance().lightMovement) {
	    jitterWidth = 0;
	    jitterHeight = 0;
	    jitterX = 0;
	    jitterY = 0;
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	if (GameManager.getInstance().stateManager.getCurrentState() == GameManager.getInstance().stateId_tutorial)
	    return;
	
	batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
	if (CurrentSettings.getInstance().softLight) {
	    batch.draw(lightOverlay, bounds.x + jitterX, bounds.y + jitterY, bounds.width + jitterWidth,
		    bounds.height + jitterHeight);
	   
	} else if (!CurrentSettings.getInstance().softLight) {
	    batch.draw(hardLightImage, bounds.x + jitterX, bounds.y + jitterY, bounds.width + jitterWidth,
		    bounds.height + jitterHeight);
	}
	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    /** Sets dimensions to texture dimensions */
    public void adjustBoundsToImage() {
	this.bounds.width = lightOverlay.getWidth();
	this.bounds.height = lightOverlay.getHeight();
    }
    
}

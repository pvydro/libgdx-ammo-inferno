package com.flizzet.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Screen flash effect particle.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ScreenFlashParticle extends Particle {
    
    private float intensity;
    
    // Quality of 2
    private Texture image;

    /** Default instantiable constructor */
    public ScreenFlashParticle() {
	/* Construct the image if the quality is higher */
	if (CurrentSettings.getInstance().screenFlashQuality == 2) {
	    image = GameManager.getInstance().assetManager.get("particles/gameParticles/screenFlashHigh.png", Texture.class);
	}
    }

    @Override
    public void update(float delta) {
	
	intensity -= .1f;		// Decrease intensity
	
	/* Remove when transparent */
	if (intensity <= 0) {
	    GameManager.getInstance().particleContainer.remove(this);
	}
	
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	
	if (CurrentSettings.getInstance().screenFlashQuality == 1) {
	    batch.end();

	    Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setColor(new Color(1f, 1f, 1f, intensity));
	    shapeRenderer.rect(0, 0, MainCamera.getInstance().getWidth(), MainCamera.getInstance().getHeight());
	    shapeRenderer.end();

	    Gdx.gl.glDisable(GL20.GL_BLEND);

	    batch.begin();
	} else if (CurrentSettings.getInstance().screenFlashQuality == 2) {
	    Color tmp = batch.getColor();
	    batch.setColor(new Color(1f, 1f, 1f, intensity));
	    batch.draw(image, -200, -200, MainCamera.getInstance().getWidth() + 400, MainCamera.getInstance().getHeight() + 400);
	    batch.setColor(tmp);
	}
	
    }

    public void setIntensity(float newIntensity)	{ this.intensity = newIntensity; }
    
}

package com.flizzet.map;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.lighting.PlatformLight;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Light that has a chance of appearing on platforms.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlatformLamp extends Entity {

    private Platform platform;
    private PlatformLight light;
    private Random random = new Random();
    private int xOffset = 0;
    
    private int particleCooldown = CurrentSettings.getInstance().platformLightParticleAmount;
    
    private Texture image;
    
     /** Default instantiable constructor */
    public PlatformLamp(Platform platform) {
	this.platform = platform;
	
	/* Build offset from platform x */
	if (platform.getSide() == 0) {
	    this.xOffset = random.nextInt(115 - 11);
	} else {
	    this.xOffset = random.nextInt(115);
	}
	
	/* Build light if enabled */
	if (CurrentSettings.getInstance().platformLight) {
	    light = new PlatformLight(this);
	}
	
	this.image = GameManager.getInstance().assetManager.get("items/platformLight.png", Texture.class);
	adjustBoundsToImage(image);
    }

    @Override
    public void update(float delta) {
	
	if (platform.getSide() == 0) this.setX(platform.getX() + 11 + xOffset);
	else this.setX(platform.getX() + xOffset);
	this.setY(platform.getY() + platform.getHeight() + PlatformGenerator.getInstance().getSpeed());
	
	/* Add liddle particles */
	if (CurrentSettings.getInstance().platformLightParticles) {
	    particleCooldown--;
	    if (particleCooldown <= 0) {
		float xOffset = random.nextBoolean() ? random.nextFloat() : -random.nextFloat();
		GameManager.getInstance().particleFunctions.addPlatformLampParticle(this.getCenterX() + xOffset - .5f,
			bounds.y + 9);
		particleCooldown = CurrentSettings.getInstance().platformLightParticleAmount;
	    }
	}
	
	if (light != null) light.update(delta);
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	batch.draw(image, bounds.x, bounds.y);
	
	if (light != null) light.render(batch);
	
    }

}

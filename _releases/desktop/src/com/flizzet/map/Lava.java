package com.flizzet.map;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.lighting.LavaLight;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.player.PlayerState;
import com.flizzet.settings.CurrentSettings;

/**
 * Lava that appears at the bottom of the screen as an obstacle.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Lava extends Entity {

    private static final Lava INSTANCE = new Lava();
    
    private LavaLight light;
    private Texture image;
    private Random random = new Random();
    private float targetY = 0;
    private int state = 0;		// 0 = InGame; 1 = Death
    private int fireCooldown = 10;
  
    
    /** Returns an instance of the lava object */
    public static Lava getInstance() {
	return INSTANCE;
    }
    /** Default instantiable constructor */
    public Lava() {
	
	image = GameManager.getInstance().assetManager.get("map/lava.png", Texture.class);
	adjustBoundsToImage(image);
	
	if (CurrentSettings.getInstance().lavaLight) {
	    light = new LavaLight(this);
	}
	
	this.setY(0 - image.getHeight() + 20);
	
    }
    
    @Override
    public void update(float delta) {
	
	/* Moving up or down depending on dead or alive */
	if (state == 0) {
	    targetY = 0 - image.getHeight() + 20;
	} else if (state == 1) {
	    targetY = -50;
	}
	bounds.y += 10 * ((targetY - bounds.y) / 2) * delta;
	
	/* Lighting */
	if (light != null) light.update(delta);
	light.setY(this.getY() + this.getHeight() - (light.getWidth() / 1.7f));
	
	/* Adding fire particle */
	if (CurrentSettings.getInstance().lavaParticles) {
	    fireCooldown--;
	    if (fireCooldown <= 0 && Player.getInstance().getState() != PlayerState.DEAD) {
		addFireParticle();
	    }
	}
    }
    @Override
    public void render(SpriteBatch batch) {
	
	if (light != null) light.render(batch);
	batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
	
    }
    
    /** Resets lava. Used specifically in a respawn */
    public void reset() {
	this.setY(0 - image.getHeight() + 20);
	this.state = 0;
    }
    
    /** Adds a lava particle */
    private void addFireParticle() {
	GameManager.getInstance().particleFunctions.addFireParticle(
		random.nextInt((int) (MainCamera.getInstance().getWidth() - 22) + 11),
		random.nextInt(20));
	fireCooldown = random.nextInt(10);
    }
    
    public void setState(int newState) {
	this.state = newState;
    }

}

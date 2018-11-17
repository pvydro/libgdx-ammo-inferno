package com.flizzet.eyeenemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;

/**
 * Spawns enemy eyes.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingEyeEnemySpawner extends Entity {
    
    private static final FlyingEyeEnemySpawner INSTANCE = new FlyingEyeEnemySpawner();
    private ArrayList<FlyingEyeEnemy> eyes = new ArrayList<FlyingEyeEnemy>();
    private Queue<FlyingEyeEnemy> toBeRemoved = new LinkedList<FlyingEyeEnemy>();
    private int topCooldown = 500;
    private int cooldown = 50;
    
    private boolean enabled = false;
    
    
    public static FlyingEyeEnemySpawner getInstance() {
	return INSTANCE;
    }
    
    /** Single use constructor */
    private FlyingEyeEnemySpawner() {}

    @Override
    public void update(float delta) {
		
	eyes.removeAll(toBeRemoved);

	/* Only add when enabled */
	if (enabled) {
	    cooldown--;

	    if (cooldown <= 0) {
		cooldown = topCooldown;
		spawnEye();
	    }
	}
	
	for (FlyingEyeEnemy e : eyes) {
	    e.update(delta);
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	
	for (FlyingEyeEnemy e : eyes) {
	    e.render(batch);
	}
	
    }
    
    /** Builds a new enemy eye and adds it to the screen */
    private void spawnEye() {
	FlyingEyeEnemy newEye = new FlyingEyeEnemy();
	//newEye.setX(side == 0 ? 0 : MainCamera.getInstance().getWidth() - newEye.getWidth());
	newEye.setX(MainCamera.getInstance().getCenterX() - (newEye.getWidth() / 2));
	newEye.setY(-151);
	eyes.add(newEye);
    }
    
    /** Removes an eye from the ArrayList of eyes */
    public void removeFromEyes(FlyingEyeEnemy eye) { 
	this.toBeRemoved.add(eye);
    }
    
    /** Respawn */
    public void reset() {
	this.toBeRemoved.addAll(eyes);
    }

    public ArrayList<FlyingEyeEnemy> getEyes() 	{ return this.eyes; }
    
    public void setCooldown(int newCooldown)	{ this.topCooldown = newCooldown; }
    public void setEnabled(boolean isEnabled)	{ this.enabled = isEnabled; }

}

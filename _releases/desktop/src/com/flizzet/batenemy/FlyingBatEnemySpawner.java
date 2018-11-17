package com.flizzet.batenemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.map.Platform;
import com.flizzet.player.Player;

/**
 * Spawns enemy bats.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FlyingBatEnemySpawner extends Entity {
    
    private static final FlyingBatEnemySpawner INSTANCE = new FlyingBatEnemySpawner();
    private ArrayList<FlyingBatEnemy> bats = new ArrayList<FlyingBatEnemy>();
    private Queue<FlyingBatEnemy> toBeRemoved = new LinkedList<FlyingBatEnemy>();
    private int topCooldown = 500;
    private int cooldown = 200;
    
    private boolean enabled = false;
    
    /** Returns and instance of the FlyingBatEnemySpawner class */
    public static FlyingBatEnemySpawner getInstance() {
	return INSTANCE;
    }
    
    /** Single use constructor */
    private FlyingBatEnemySpawner() {}

    @Override
    public void update(float delta) {
	
	bats.removeAll(toBeRemoved);

	if (enabled) {
	    cooldown--;

	    if (cooldown <= 0) {
		cooldown = topCooldown;
		spawnBat();
	    }
	}
	
	for (FlyingBatEnemy e : bats) {
	    e.update(delta);
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	
	for (FlyingBatEnemy e : bats) {
	    e.render(batch);
	}
	
    }
    
    /** Builds a new enemy eye and adds it to the screen */
    private void spawnBat() {
	FlyingBatEnemy newBat = new FlyingBatEnemy();
	Platform targetPlatform = Player.getInstance().getController().getTargetPlatform();
	int side = targetPlatform.getSide();
	switch (side) {
	case 0:
	    newBat.setX(MainCamera.getInstance().getWidth() + 20 + newBat.getWidth());
	    break;
	case 1:
	    newBat.setX(-20);
	    break;
	case 2:
	    newBat.setX(new Random().nextBoolean() ? -20 : MainCamera.getInstance().getWidth() + 20 + newBat.getWidth());
	}
	
	newBat.setY(targetPlatform.getCenterY());
	
	bats.add(newBat);
    }
    
    /** Removes an eye from the ArrayList of eyes */
    public void removeFromBats(FlyingBatEnemy bat) { 
	this.toBeRemoved.add(bat);
    }
    
    /** Respawn */
    public void reset() {
	this.toBeRemoved.addAll(bats);
    }

    public ArrayList<FlyingBatEnemy> getBats() 	{ return this.bats; }
    
    public void setCooldown(int newCooldown)	{ this.topCooldown = newCooldown; }
    public void setEnabled(boolean isEnabled)	{ this.enabled = isEnabled; }

}

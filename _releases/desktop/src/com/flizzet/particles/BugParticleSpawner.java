package com.flizzet.particles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.settings.CurrentSettings;

/**
 * Spawns bugs on the map.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BugParticleSpawner extends Entity {
    
    private static final BugParticleSpawner INSTANCE = new BugParticleSpawner();
    
    private int totalBugs = 5;
    private Queue<BugParticle> toBeAdded = new LinkedList<BugParticle>();
    private Queue<BugParticle> toBeRemoved = new LinkedList<BugParticle>();
    private ArrayList<BugParticle> bugs = new ArrayList<BugParticle>();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static BugParticleSpawner getInstance() {
	return INSTANCE;
    }
    
    /** Default instantiable constructor */
    public BugParticleSpawner() {
	if (CurrentSettings.getInstance().bugs) {
	    for (int i = 0; i < totalBugs; i++) {
		spawnBug();
	    }
	}
    }

    /** Spawns multiple beginning bugs */
    public void spawnBug() {
	BugParticle newBug = new BugParticle();
	newBug.setY(800);
	newBug.setX(MainCamera.getInstance().getCenterX());
	toBeAdded.add(newBug);
    }

    @Override
    public void update(float delta) {
	
	bugs.addAll(toBeAdded);
	bugs.removeAll(toBeRemoved);
	toBeAdded.removeAll(toBeAdded);
	toBeRemoved.removeAll(toBeRemoved);
	
	for (BugParticle b : bugs) {
	    b.update(delta);
	    
	    if (b.getY() < -100) {
		toBeRemoved.add(b);
	    }
	}
	
    }
    
    @Override
    public void render(SpriteBatch batch) {
	
	for (BugParticle b : bugs) {
	    b.render(batch, shapeRenderer);
	}
	
    }
    
    /** Respawn */
    public void reset() {
	toBeRemoved.addAll(bugs);
	if (CurrentSettings.getInstance().bugs) {
	    for (int i = 0; i < totalBugs; i++) {
		spawnBug();
	    }
	}
    }
    
    public ArrayList<BugParticle> getBugs() {
	return this.bugs;
    }

}

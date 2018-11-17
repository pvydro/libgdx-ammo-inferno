package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.lighting.BugLight;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;

/**
 * Bug that flies around the player and stray.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BugParticle extends Particle {
    
    private Player player = Player.getInstance();
    private BugLight light;
    private Color bugColor = new Color(1f, 1f, 1f, 0.5f);
    private ParticleCollision collision = new ParticleCollision(this);

    private int state = 0; 		// 0 = Player following; 1 = Stray; 2 = Dead
    private int cooldown = 10;
    private float targetX = 0;
    private float targetY = 0;
    private float targetSize = 3;
    private float speed = 5;
    private float radius = 100;
    private Random random = new Random();

    /** Default instantiable constructor */
    public BugParticle() {
	this.setWidth(1);
	this.setHeight(1);
	
	this.state = 0;			// tmp
	
	if (CurrentSettings.getInstance().lighting) {
	    light = new BugLight(this);
	}
    }

    @Override
    public void update(float delta) {
	
	super.update(delta);
	
	/* Cool yourself down no ceiling fan */
	cooldown--;
	if (cooldown <= 0) {
	    findNewPosition();
	}
	
	/* Move to target if not dead
	 * Otherwise move with vels */
	if (state != 2) {
	    /* Move around move around */
	    bounds.x += 10 * ((((player.getCenterX() + (player.getWidth() / 2)) + targetX) - bounds.x) / speed) * delta;
	    bounds.y += 10 * ((((player.getCenterY() + (player.getHeight() / 2)) + targetY) - bounds.y) / speed) * delta;
	} else {	// Dead
	    xVel += 10 * ((0 - xVel) / 2) * delta;
	    fall(delta);
	    this.setX(this.getX() + xVel);
	    this.setY(this.getY() + yVel);
	    this.setY(this.getY() + PlatformGenerator.getInstance().getSpeed());
	}
	
	/* Resize */
	bounds.width += 10 * ((targetSize - bounds.width) / 2) * delta;
	bounds.height += 10 * ((targetSize - bounds.height) / 2) * delta;
	
	if (light != null) light.update(delta);

	/* Updating collision if dead */
	if (state == 2) {
	    if (CurrentSettings.getInstance().collideParticles) {
		collision.update(delta);
	    }
	}

    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	batch.end();
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(bugColor);
	shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	shapeRenderer.end();
	batch.begin();
	
	if (light != null) light.render(batch);
    }
    
    /** Finds a new random position to travel to  */
    private void findNewPosition() {
	if (state == 0) {
	    targetX = (float) (Math.random() * radius) - (radius / 2);
	    targetY = (float) (Math.random() * radius) - (radius / 2);
	}
	cooldown = random.nextInt(40);
	bugColor = new Color(1, 1, 1, random.nextFloat());
    }

    /** Hit the bug and claps it */
    @Override
    public void hit(float xVel) {
	super.hit(xVel);
	BugParticleSpawner.getInstance().spawnBug();
	this.state = 2; // Die
    }
    
    public int getState()	{ return this.state; }
    
}

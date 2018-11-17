package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformGenerator;

/**
 * Particle that emmits from fire related elements.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FireParticle extends Particle {
    
    private Random random = new Random();
    private Color color;
    
    private float xChange = 0;
    
    private int removeCooldown = 80;
    private int jitterCooldown = 20;
    private boolean draw = true;
    
    /** Default instantiable constructor */
    public FireParticle() {
	int scale = random.nextInt(4);
	color = new Color(random.nextFloat() * 3, random.nextFloat() * 3, random.nextFloat() / 5, 1.0f);
	yVel = (random.nextFloat() * 5) + 70;
	bounds.width = scale;
	bounds.height = scale;
    }
    
    @Override
    public void update(float delta) {
	
	/* Removal + flashing */
	removeCooldown --;
	if (removeCooldown <= 20) {
	    if (removeCooldown % 4 == 0) {
		draw = !draw;
	    }
	}
	if (removeCooldown <= 0) {
	    GameManager.getInstance().particleContainer.remove(this);
	}
	
	/* Jittering */
	jitterCooldown--;
	if (jitterCooldown <= 0) {
	    jitter();
	}
	
	/* Adding velocity to position */
	this.setY(this.getY() + (yVel * delta) + PlatformGenerator.getInstance().getSpeed());
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	if (draw) {
	    batch.end();
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setColor(color);
	    shapeRenderer.rect(bounds.x + xChange, bounds.y, bounds.width, bounds.height);
	    shapeRenderer.end();
	    batch.begin();
	}
    }
    
    /** Makes the particle move to the side randomly for a jitter effect */
    private void jitter() {
	xChange = random.nextBoolean() ? random.nextInt( 2) : -random.nextInt(2);
	jitterCooldown = random.nextInt(10) + 10;
    }

}

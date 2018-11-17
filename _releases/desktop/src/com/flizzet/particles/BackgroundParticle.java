package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;

/**
 * Background particles that emmit from the background.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BackgroundParticle extends Particle {

    //private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Texture image = GameManager.getInstance().assetManager.get("particles/menuParticles/bgParticle.png");
    private BackgroundComponent background;
    private Color color;
    private Random random = new Random();
    
    /** Default instantiable constructor */
    public BackgroundParticle(BackgroundComponent background) {
	this.background = background;
	
	/* Decide random speed, negative or positive */
	yVel = random.nextBoolean() ? (random.nextFloat() * 5) + 1 : -(random.nextFloat() * 5);
	
	/* Assigning a random color */
	float c = random.nextFloat();
	color = new Color(c, c, c, 1.0f);
	
	/* Assigning a random size */
	int scale = random.nextInt(200);
	bounds.width = scale;
	bounds.height = scale;
	
	/* Setting y */
	if (yVel > 0) {
	    this.setY(-this.getHeight());
	} else if (yVel < 0) {
	    this.setY(MainCamera.getInstance().getHeight());
	}
    }
    
    @Override
    public void update(float delta) {
	
	this.setY(this.getY() + yVel);
	
	/* Removal */
	if (yVel > 0 && this.getY() > MainCamera.getInstance().getHeight()) {
	    background.removeFromParticles(this);
	} else if (yVel < 0 && this.getY() < 0 - this.getHeight()) {
	    background.removeFromParticles(this);
	}
	
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	Color tmp = batch.getColor();
	batch.setColor(new Color(color.r, color.g, color.b, 0.2f));
	batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
	batch.setColor(tmp);
	
    }
}

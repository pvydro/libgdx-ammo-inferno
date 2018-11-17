package com.flizzet.particles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.weaponshop.WeaponStand;

/**
 * Concrete weapon shop particle.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponShopParticle extends Particle {

    private WeaponStand stand;
    private Random random = new Random();
    private float alpha = 0.5f;
    
    /** Default instantiable constructor */
    public WeaponShopParticle(WeaponStand stand) {
	
	this.stand = stand;
	
	yVel = (1 + random.nextInt(3)) / 3f;
	int scale = random.nextInt(5);
	this.setWidth(scale);
	this.setHeight(scale);
	alpha = random.nextFloat();
	
    }
    
    @Override
    public void update(float delta) {
	this.setY(this.getY() + yVel);
	
	/* Removal */
	alpha -= 0.01f;
	if (alpha <= 0) {
	    stand.removeParticle(this);
	}
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
	batch.end();

	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(new Color(1, 1, 1, alpha));
	shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	shapeRenderer.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
	batch.begin();
    }

}

package com.flizzet.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;
import com.flizzet.guntrapenemy.GunTrapEnemy;
import com.flizzet.lighting.GunTrapBulletLight;
import com.flizzet.main.GameManager;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.settings.CurrentSettings;

/**
 * Bullet projectile that releases from the GunTrapEnemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also GunTrapEnemy
 */
public class GunTrapBullet extends Entity {
    
    private Texture image;
    private GunTrapBulletLight light;
    private GunTrapBulletCollision collision = new GunTrapBulletCollision(this);
    private float speed = 5;
    private int direction = 0;

    /** Default instantiable constructor */
    public GunTrapBullet(GunTrapEnemy enemy) {
	this.direction = enemy.getSide() == 0 ? 1 : 0;
	
	this.setX(enemy.getCenterX());
	this.setY(enemy.getCenterY());
	
	this.image = GameManager.getInstance().assetManager.get("items/bullet.png");
	adjustBoundsToImage(image);
	
	if (CurrentSettings.getInstance().bulletLight) {
	    light = new GunTrapBulletLight(this);
	}
    }

    @Override
    public void update(float delta) {
	/* Add speed to the horizontal position */
	if (direction == 0) {
	    this.setX(this.getX() - speed);
	} else if (direction == 1) {
	    this.setX(this.getX() + speed);
	}
	
	/* Normalize */
	this.setY(this.getY() + PlatformGenerator.getInstance().getSpeed());
	
	light.update(delta);
	collision.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	/* Draw flipped or not based on the direction */
	if (direction == 1) {
	    batch.draw(image, bounds.x, bounds.y);
	} else {
	    batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height, 
		    0, 0, (int) bounds.width, (int) bounds.height, true, false);
	}
	
	light.render(batch);
    }
    
    /** Removes the bullet from the game */
    public void remove() {
	GameManager.getInstance().entityContainer.remove(this);
    }

}

package com.flizzet.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.entities.Entity;
import com.flizzet.ingamegui.HealthBar;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;

/**
 * Abstract enemy class for concrete enemies to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public abstract class Enemy extends Entity {
    
    private Rectangle collisionBounds = new Rectangle(0, 0, 0, 0);
    protected EnemyAi ai;
    protected HealthBar healthBar;
    private boolean dead = false;

    /** Default instantiable constructor */
    public Enemy() {}
    
    @Override
    public void update(float delta) {
	if (ai != null) ai.update(delta);
	if (healthBar != null) {
	    healthBar.update(delta);
	    
	    if (healthBar.getHealth() <= 0) {
		if (!dead) {
		    dead = true;
		    die();
		}
	    }
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	if (healthBar != null) {
	    healthBar.render(batch);
	}
    }
    
    /** Hit enemy */
    public void hit() {
	/* Play hit sound */
	Sounds.play(Sounds.getInstance().enemyHitSound, 0.5f);
	
	/* Remove one from the health */
	if (healthBar != null) {
	    healthBar.setHealth(healthBar.getHealth() - 1);
	}
    }
    /** Kill enemy */
    public void die() {
	/* Increase weapon total kills */
	Player.getInstance().getWeapon().setTotalKills(Player.getInstance().getWeapon().getTotalKills() + 1);
	
	/* Add a death particle to the screen */
	if (CurrentSettings.getInstance().enemyDeath) {
	    GameManager.getInstance().particleFunctions.addEnemyDeathParticle(getCenterX(), getCenterY());
	    GameManager.getInstance().particleFunctions.addEnemyDeathSlashParticle(getCenterX(), getCenterY());
	}
    }
    
    public Rectangle getCollisionBounds() 		{ return collisionBounds; }
    public void setCollisionWidth(float newWidth)	{ this.collisionBounds.setWidth(newWidth); }
    public void setCollisionHeight(float newHeight)	{ this.collisionBounds.setHeight(newHeight); }
    public void setCollisionX(float newX)		{ this.collisionBounds.setX(newX); }
    public void setCollisionY(float newY)		{ this.collisionBounds.setY(newY); }
    public void setAi(EnemyAi newAi) 			{ this.ai = newAi; }
    public void setHealthBar(HealthBar newHealthBar)	{ this.healthBar = newHealthBar; }
    
    public boolean isDead()				{ return this.dead; }
    
}

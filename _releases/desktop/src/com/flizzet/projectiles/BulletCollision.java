package com.flizzet.projectiles;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.flizzet.batenemy.FlyingBatEnemy;
import com.flizzet.batenemy.FlyingBatEnemySpawner;
import com.flizzet.enemies.Enemy;
import com.flizzet.eyeenemy.FlyingEyeEnemy;
import com.flizzet.eyeenemy.FlyingEyeEnemySpawner;
import com.flizzet.flyzombieenemy.FlyingZombieEnemy;
import com.flizzet.flyzombieenemy.FlyingZombieEnemySpawner;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.map.Lava;
import com.flizzet.map.MapForeground;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.particles.BugParticle;
import com.flizzet.particles.BugParticleSpawner;
import com.flizzet.particles.Particle;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.tutorial.DummyEnemy;
import com.flizzet.upgrades.StyrofoamDartsUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Handles collision for every bullet object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BulletCollision implements Updatable {
    
    private Bullet bullet;
    private Rectangle bulletBounds;
    private ArrayList<Platform> platforms;
    private Random random = new Random();
    private int hits = 0;
    private int cooldown = 5;

    /** Default instantiable constructor */
    public BulletCollision(Bullet bullet) {
	this.bullet = bullet;
	this.platforms = PlatformGenerator.getInstance().getPlatforms();
    }

    @Override
    public void update(float delta) {
	
	/* Cooldown */
	if (cooldown > 0) {
	    cooldown--;
	}
	
	bulletBounds = bullet.getCollisionBounds();
	
	/* *
	 * Enemy 
	 */
	/* Zombie collision */
	for (Platform p : platforms) {
	    if (p.getZombieEnemy() != null) {			// If there's an enemy on the platform
		performCollisionWithEnemy(p.getZombieEnemy());
	    }
	}
	/* Eye collision */
	for (FlyingEyeEnemy e : FlyingEyeEnemySpawner.getInstance().getEyes()) {
	    performCollisionWithEnemy(e);
	}
	/* Bat collision */
	for (FlyingBatEnemy e : FlyingBatEnemySpawner.getInstance().getBats()) {
	    performCollisionWithEnemy(e);
	}
	/* Flying zombie collision  */
	for (FlyingZombieEnemy e : FlyingZombieEnemySpawner.getInstance().getZombies()) {
	    performCollisionWithEnemy(e);
	}
	/* Dummy collision */
	if (GameManager.getInstance().stateManager.getCurrentState()
		== GameManager.getInstance().stateId_tutorial) {
	    performCollisionWithEnemy(DummyEnemy.getInstance());
	}
	
	
	/* *
	 * Other
	 */
	/* Platform collision */
	for (Platform p : platforms) {
	    if (bulletBounds.overlaps(p.getBounds())) {
		if (cooldown <= 0) {
		    if (Upgrades.getInstance().isEquipped(StyrofoamDartsUpgrade.class) && hits < 1) {
			bullet.bounce();
			hits++;
		    } else {
			bullet.remove();
		    }
		    cooldown = 10;
		}
	    }
	}
	
	/* Bug collision */
	for (BugParticle b : BugParticleSpawner.getInstance().getBugs()) {
	    if (bulletBounds.overlaps(b.getBounds())) {
		if (b.getState() != 2) {
		    b.hit(bullet.getXVel());
		}
	    }
	}

	/* Particle collision */
	if (CurrentSettings.getInstance().collideParticles) {
	    for (Particle p : GameManager.getInstance().particleContainer.getParticles()) {
		if (bulletBounds.overlaps(p.getBounds())) {
		    p.hit(bullet.getXVel());
		}
	    }
	}

	/* Removing on sides */
	if (bulletBounds.x < MapForeground.getInstance().getLeftX() + 11) {
	    /* Play wall hit sound */
	    if (random.nextInt(10) == 4)  Sounds.play(Sounds.getInstance().wallHitSound, 0.4f, 1.0f + random.nextFloat());
	    /* Remove bullet */
	    bullet.remove();
	    /* Add particles */
	    if (CurrentSettings.getInstance().bulletHits) {
		GameManager.getInstance().particleFunctions.addGroundParticle(
			bullet.getCenterX(), bullet.getCenterY(), 2);
	    }
	}
  
	if (bulletBounds.x + bulletBounds.getWidth() > MapForeground.getInstance().getRightX()) {
	    /* Play wall hit sound */
	    if (random.nextInt(10) == 4) Sounds.play(Sounds.getInstance().wallHitSound, 0.4f, 1.0f + random.nextFloat());
	    /* Remove bullet */
	    bullet.remove();
	    /* Add particles */
	    if (CurrentSettings.getInstance().bulletHits) {
		GameManager.getInstance().particleFunctions.addGroundParticle(
			bullet.getCenterX(), bullet.getCenterY(), 2);
	    }
	}
	
	/* Removing on top and bottom */
	if (bulletBounds.y < - 100) {
	    bullet.remove();
	}
	if (bulletBounds.y > 600) {
	    bullet.remove();
	}
	
	/* Removing on lava */
	if (bulletBounds.overlaps(Lava.getInstance().getBounds())) {
	    bullet.remove();
	}
	
    }
    
    /** Does collision trigger between a bullet and an enemy */
    private void performCollisionWithEnemy(Enemy enemy) {
	if (bulletBounds.overlaps(enemy.getCollisionBounds())) {
	    bullet.remove();
	    enemy.hit();
	    if (CurrentSettings.getInstance().enemyHits) {
		GameManager.getInstance().particleFunctions.addEnemyHitParticle(enemy.getCenterX(), bullet.getCenterY());
	    }
	}
    }

}

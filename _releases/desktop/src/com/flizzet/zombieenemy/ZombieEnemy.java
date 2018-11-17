package com.flizzet.zombieenemy;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.enemies.Enemy;
import com.flizzet.enemies.EnemyScoreDealer;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;

/**
 * Concrete zombie enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ZombieEnemy extends Enemy {
    
    private ZombieEnemyAnimator animator = new ZombieEnemyAnimator(this);
    private ZombieEnemyCollision collision = new ZombieEnemyCollision(this);
    private ZombieEnemyHead head = new ZombieEnemyHead(this);
    private ZombieEnemyDialogueGenerator dialogue;
    private ZombieState currentState = ZombieState.IDLE;
    private EnemyScoreDealer scoreDealer = new EnemyScoreDealer(this);
    private Platform platform;
    private Rectangle collisionBounds = new Rectangle(0, 0, 0, 0);
    private Random random = new Random();
    
    private int hitCooldown = 0;
    
    /** Default instantiable constructor */
    public ZombieEnemy(Platform platform) {
	this.platform = platform;
	this.setAi(new ZombieEnemyAiEasy(this, platform));
	this.setHealthBar(new ZombieHealthBar(this));
	this.getAi().setSide(platform.getSide());
	this.bounds.x = platform.getX();
	this.bounds.y = platform.getY();
	
	if (CurrentSettings.getInstance().zombieDialogue) {
	    dialogue = new ZombieEnemyDialogueGenerator(this);
	}
	
	/* Randomly play sound */
	if (random.nextInt(5) == 3) {
	    Sounds.play(Sounds.getInstance().zombieGroanSound, 1.5f, 0.5f + random.nextFloat());
	}
    }

    public void update(float delta) {
	
	/* Set collision rectangle */
	collisionBounds.setX(bounds.x + 6);
	collisionBounds.setY(bounds.y - 1);
	if (getState() != ZombieState.DEAD) {
	    collisionBounds.setWidth(bounds.width - 12);
	    collisionBounds.setHeight(bounds.height + 5);
	} else {
	    collisionBounds.setWidth(bounds.width - 2);
	    collisionBounds.setHeight(bounds.height - 8);
	}
	
	animator.update(delta);
	collision.update(delta);
	head.update(delta);
	healthBar.update(delta);
	if (dialogue != null && getState() != ZombieState.DEAD) {
	    dialogue.update(delta);
	}
	
	/* Hit cooldown */
	if (hitCooldown > 0) hitCooldown--;
	
	super.update(delta);
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	animator.render(batch);
	head.render(batch);
	healthBar.render(batch);
	if (dialogue != null && getState() != ZombieState.DEAD) {
	    dialogue.render(batch);
	}
	
    }
    
    /** Inflict damage on enemy */
    @Override
    public void hit() {
	if (hitCooldown <= 0) {
	    super.hit();
	    hitCooldown = 5;

	    if (CurrentSettings.getInstance().blood) {
		GameManager.getInstance().particleFunctions.addBloodParticle(getCenterX(), getCenterY(), CurrentSettings.getInstance().bloodAmount);
	    }
	    if (CurrentSettings.getInstance().textParticles && currentState != ZombieState.DEAD) {
		GameManager.getInstance().particleFunctions.addTextParticle(getCenterX(), getCenterY(), "-1");
	    }
	}
    }
    
    @Override
    public void die() {
	super.die();
	this.setState(ZombieState.DEAD);
	getAi().jump(-5);
	scoreDealer.newPoint();
    }
    
    public ZombieState getState()		{ return this.currentState; }
    public Rectangle getCollisionBounds() 	{ return this.collisionBounds; }
    public Platform getPlatform()		{ return this.platform; }
    public ZombieEnemyAiEasy getAi()		{ return (ZombieEnemyAiEasy) this.ai; }
    public ZombieEnemyAnimator getAnimator() 	{ return this.animator; }
    public ZombieEnemyHead getHead()		{ return this.head; }
    
    public void setState(ZombieState newState)	{ this.currentState = newState; }

}

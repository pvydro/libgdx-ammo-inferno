package com.flizzet.player;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.ads.AdManager;
import com.flizzet.deathmenu.DeathMenu;
import com.flizzet.entities.Entity;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.ingamegui.HealthBar;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.lighting.AmbienceManager;
import com.flizzet.lighting.PlayerLight;
import com.flizzet.main.GameManager;
import com.flizzet.map.Lava;
import com.flizzet.revivemenu.ReviveMenu;
import com.flizzet.saving.Saves;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.upgrades.BandaidUpgrade;
import com.flizzet.upgrades.ExtraLifeUpgrade;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.weapons.EonWeapon;
import com.flizzet.weapons.Weapon;

/**
 * Player class for the player entity.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Player extends Entity {

    private final static Player INSTANCE = new Player();
    
    private Rectangle collisionBounds = new Rectangle(0, 0, 0, 0);
    
    private PlayerAnimator animator = new PlayerAnimator(this);
    private PlayerController controller = new PlayerController(this);
    private PlayerCollision collision = new PlayerCollision(this);
    private PlayerHead head = new PlayerHead(this);
    private PlayerHealthBar healthBar = new PlayerHealthBar(this);
    private PlayerScore score = new PlayerScore();
    private PlayerLight light;
    private PlayerState state;
    private Random random = new Random();
    private ReviveMenu reviveMenu;
    private int hitCooldown = 20;
    private int topHitCooldown = 30;
    private boolean revived = false;
   
    private Weapon weapon = new EonWeapon();
    
    private int direction = 1;		// 1 = Right; 0 = Left
    
    /** Returns an instance of the Player */
    public static Player getInstance() {
	return INSTANCE;
    }
    
    /** Default instantiable constructor */    
    public Player() {
	if (CurrentSettings.getInstance().playerLight) {
	    light = new PlayerLight(this);
	}
    }

    @Override
    public void update(float delta) {
	
	controller.update(delta);		// Only update when below the screen to prevent jumping off screen
	
	/* Setting collision position */
	collisionBounds.setX(bounds.x + 6);
	collisionBounds.setY(bounds.y);
	collisionBounds.setWidth(bounds.width - 12);
	collisionBounds.setHeight(bounds.height + 5);
	
	animator.update(delta);
	collision.update(delta);
	head.update(delta);
	healthBar.update(delta);
	if (light != null) light.update(delta);
	
	/* Weapon */
	weapon.setX(this.getCenterX() - (weapon.getWidth() / 2));
	weapon.setY(this.getCenterY() - 3 - (weapon.getHeight() / 2));
	weapon.update(delta);
	
	/* Hit cooldown */
	if (hitCooldown > 0) {
	    hitCooldown--;
	}
	
	/* Dying */
	if (state != PlayerState.DEAD && healthBar.getHealth() <= 0) {
	    die();
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	healthBar.render(batch);
	if (light != null) light.render(batch);
	animator.render(batch);
	head.render(batch);
	weapon.render(batch);
    }
    
    /** Called when damaged */
    public void hit() {
	if (getState() != PlayerState.DYING
	&& getState() != PlayerState.DEAD) {
	    if (CurrentSettings.getInstance().loseHealth) {
		if (hitCooldown <= 0) {
		    /* Play hit sound */
		    Sounds.play(Sounds.getInstance().playerHitSound, 0.1f, 1.0f + random.nextFloat());
		    controller.setYVel(5);
		    controller.setXVel(random.nextBoolean() ? 5 : -5);
		    healthBar.setHealth(healthBar.getHealth() - 1);
		    if (Upgrades.getInstance().isEquipped(BandaidUpgrade.class)) {
			hitCooldown = 50;
			GameManager.getInstance().particleFunctions.addTextParticle(this.getCenterX(), this.getCenterY() - 15, "bandaid!");
		    } else {
			hitCooldown = topHitCooldown;
		    }
		    if (CurrentSettings.getInstance().textParticles) {
			GameManager.getInstance().particleFunctions.addTextParticle(getCenterX() - 80, getCenterY(), "hit!");
		    }
		}
	    }
	}
    }
    
    /** Called when clapped */
    public void die() {
	
	/* Single call */
	if (state != PlayerState.DYING) {
	    score.combine();
	    controller.setYVel(12);
	    Sounds.play(Sounds.getInstance().playerDeathSound, 0.5f);
	    InGameGui.getInstance().clear();
	    Saves.getInstance().save();
	}
	
	setState(PlayerState.DYING);
	
	if (this.getY() < 0 && this.getState() != PlayerState.DEAD) {
	    this.setState(PlayerState.DEAD);
	    reviveMenu = new ReviveMenu() {
		@Override
		public void triggered() {
		    super.triggered();
		    
		    controller.setDead(true);
		    setState(PlayerState.DEAD);
		    Lava.getInstance().setState(1);
		    TransitionComponent transition = new TransitionComponent(0, 0) {
			@Override
			public void triggered() {
			    DeathMenu.getInstance().enable();
			    AmbienceManager.getInstance().reset();
			    GameManager.getInstance().guiContainer.addToGui(DeathMenu.getInstance());
			}
		    };
		    
		    GameManager.getInstance().transitionContainer.addToTransitions(transition);
		    
		    /* Show an interstitial ad 1 in 3 times if the player has played before */
		    if (CurrentSettings.getInstance().playedBefore) {
			if (random.nextInt(3) == 2) AdManager.getInstance().showInterstitialAd();
		    }
		}
		
 	    };
 	    if (revived) {
 		reviveMenu.triggered();
 	    }
 	    else if (score.getTotalBills() < 15 && !CurrentSettings.getInstance().adsPlayable) {
 		reviveMenu.triggered();
 	    }
 	    else {
 		GameManager.getInstance().guiContainer.addToGui(reviveMenu);
 	    }
 	    
	    controller.setDead(true);
	  
	}
	
    }
    
    /** Respawn */
    public void reset() {
	this.setState(PlayerState.IDLE);
	this.healthBar.reset();
	if (Upgrades.getInstance().isEquipped(ExtraLifeUpgrade.class)) {
	    healthBar.setHealth(4);
	}
	this.controller.reset();
	this.weapon.reset();
	this.score.reset();
	this.setX(20);
	this.setY(550);
	
	this.setRevived(false);
    }
    
    public int getDirection()			{ return this.direction; }
    public boolean isRevived()			{ return this.revived; }
    public PlayerAnimator getAnimator() 	{ return this.animator; }
    public PlayerController getController() 	{ return this.controller; }
    public PlayerHead getHead()			{ return this.head; }
    public PlayerState getState()		{ return this.state; }
    public PlayerScore getScore()		{ return this.score; }
    public HealthBar getHealthBar()		{ return this.healthBar; }
    public Weapon getWeapon()			{ return this.weapon; }
    public Rectangle getCollisionBounds()	{ return this.collisionBounds; }
    public ReviveMenu getReviveMenu()		{ return this.reviveMenu; }
    
    public void setWeapon(Weapon newWeapon)	{ this.weapon = newWeapon; }
    public void setDirection(int newDirection)	{ this.direction = newDirection; }
    public void setState(PlayerState newState)	{ this.state = newState; }
    public void setRevived(boolean isRevived)	{ this.revived = isRevived; }

}

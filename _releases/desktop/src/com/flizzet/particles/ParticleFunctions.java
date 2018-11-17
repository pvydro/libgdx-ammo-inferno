package com.flizzet.particles;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flizzet.main.GameManager;
import com.flizzet.utils.AnimationUtils;

/**
 * Functions that add particles to the screen.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ParticleFunctions {

    private ArrayList<Animation<TextureRegion>> muzzleFlashes = new ArrayList<Animation<TextureRegion>>();
    private Random muzzleChooser = new Random();
    
    /** Default instantiable constructor */
    public ParticleFunctions() {}
    
    /** Adds a muzzle flash to the screen */
    public void addMuzzleFlash(float x, float y, float rotation) {
	MuzzleFlashParticle newMuzzleFlash = new MuzzleFlashParticle();
	newMuzzleFlash.setX(x);
	newMuzzleFlash.setY(y);
	newMuzzleFlash.setRotation(rotation);
	newMuzzleFlash.setAnimation(chooseMuzzleFlashAnimation());
	GameManager.getInstance().particleContainer.add(newMuzzleFlash);
    }
    /** Adds a screen flash overlay to the screen */
    public void addScreenFlash(float intensity) {
	ScreenFlashParticle newScreenFlash = new ScreenFlashParticle();
	newScreenFlash.setIntensity(intensity);
	GameManager.getInstance().particleContainer.add(newScreenFlash);
    }
    /** Adds a blood particle to the screen */
    public void addBloodParticle(float x, float y, int amount) {
	for (int i = 0; i < amount; i ++) {
	    BloodParticle newBloodParticle = new BloodParticle(x, y);
	    GameManager.getInstance().particleContainer.add(newBloodParticle);
	}
    }
    /** Adds a platform lamp particle to the screen */
    public void addPlatformLampParticle(float x, float y) {
	PlatformLampParticle newPlatformLampParticle = new PlatformLampParticle();
	newPlatformLampParticle.setX(x);
	newPlatformLampParticle.setY(y);
	GameManager.getInstance().particleContainer.add(newPlatformLampParticle);
    }
    /** Adds a ground particle to the screen */
    public void addGroundParticle(float x, float y, int amount) {
	for (int i = 0; i < amount; i ++) {
	    GroundParticle newGroundParticle = new GroundParticle();
	    newGroundParticle.setX(x);
	    newGroundParticle.setY(y);
	    GameManager.getInstance().particleContainer.add(newGroundParticle);
	}
    }
    /** Adds a text particle to the screen */
    public void addTextParticle(float x, float y, String message) {
	TextParticle newTextParticle = new TextParticle(message);
	newTextParticle.setX(x);
	newTextParticle.setY(y);
	GameManager.getInstance().particleContainer.add(newTextParticle);
    }
    /** Adds a fire particle to the screen */
    public void addFireParticle(float x, float y) {
	FireParticle newFireParticle = new FireParticle();
	newFireParticle.setX(x);
	newFireParticle.setY(y); 
	GameManager.getInstance().particleContainer.add(newFireParticle);
    }
    /** Adds a next stage particle to the screen */
    public void addNextStageParticle() {
	NextStageParticle newNextStageParticle = new NextStageParticle();
	GameManager.getInstance().particleContainer.add(newNextStageParticle);
    }
    /** Adds an enemy hit particle to the screen */
    public void addEnemyHitParticle(float x, float y) {
	EnemyHitParticle newHitParticle = new EnemyHitParticle();
	newHitParticle.setX(x - (newHitParticle.getWidth() / 2));
	newHitParticle.setY(y - (newHitParticle.getHeight() / 2));
	GameManager.getInstance().particleContainer.add(newHitParticle);
    }
    /** Adds a enemy death particle to the screen */
    public void addEnemyDeathParticle(float x, float y) {
	EnemyDeathParticle newDeathParticle = new EnemyDeathParticle();
	newDeathParticle.setX(x - (newDeathParticle.getWidth() / 2));
	newDeathParticle.setY(y - (newDeathParticle.getHeight() / 2));
	GameManager.getInstance().particleContainer.add(newDeathParticle);
    }
    /** Adds a enemy death slash particle to the screen */
    public void addEnemyDeathSlashParticle(float x, float y) {
	EnemyDeathSlashParticle newSlashParticle = new EnemyDeathSlashParticle();
	newSlashParticle.setX(x - (newSlashParticle.getWidth() / 2));
	newSlashParticle.setY(y - (newSlashParticle.getHeight() / 2));
	GameManager.getInstance().particleContainer.add(newSlashParticle);
    }
    /** Adds a player land particle to the screen */
    public void addPlayerLandParticle(float x, float y) {
	PlayerLandParticle newLandParticle = new PlayerLandParticle();
	newLandParticle.setX(x - (newLandParticle.getWidth() / 2));
	newLandParticle.setY(y);
	GameManager.getInstance().particleContainer.add(newLandParticle);
    }
    
    
    /** Chooses a random muzzle flash animation to be used */
    private Animation<TextureRegion> chooseMuzzleFlashAnimation() {
	int i = muzzleChooser.nextInt(4);
	return muzzleFlashes.get(i);
    }
    /** Loads all muzzle flashes for choosing a random one upon creation */
    public void loadMuzzleFlashes() {
	Texture muzzleSheet0 = GameManager.getInstance().assetManager.get("particles/gameParticles/muzzleFlash0.png", Texture.class);
	Texture muzzleSheet1 = GameManager.getInstance().assetManager.get("particles/gameParticles/muzzleFlash1.png", Texture.class);
	Texture muzzleSheet2 = GameManager.getInstance().assetManager.get("particles/gameParticles/muzzleFlash2.png", Texture.class);
	Texture muzzleSheet3 = GameManager.getInstance().assetManager.get("particles/gameParticles/muzzleFlash3.png", Texture.class);
	
	muzzleFlashes.add(AnimationUtils.newAnimation(muzzleSheet0, 4, 1, 30));
	muzzleFlashes.add(AnimationUtils.newAnimation(muzzleSheet1, 4, 1, 30));
	muzzleFlashes.add(AnimationUtils.newAnimation(muzzleSheet2, 4, 1, 30));
	muzzleFlashes.add(AnimationUtils.newAnimation(muzzleSheet3, 4, 1, 30));
    }

}

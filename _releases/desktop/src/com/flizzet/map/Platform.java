package com.flizzet.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.enemies.MainEnemySpawner;
import com.flizzet.entities.Entity;
import com.flizzet.guntrapenemy.GunTrapEnemy;
import com.flizzet.guntrapenemy.GunTrapEnemySpawner;
import com.flizzet.sawenemy.SawEnemy;
import com.flizzet.sawenemy.SawEnemySpawner;
import com.flizzet.score.TreasureChest;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.spikeenemy.SpikeEnemy;
import com.flizzet.spikeenemy.SpikeEnemySpawner;
import com.flizzet.upgrades.LuckyDogUpgrade;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.zombieenemy.ZombieEnemy;
import com.flizzet.zombieenemy.ZombieEnemySpawner;

/**
 * Platform that player jumps on.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Platform extends Entity {
    
    private Texture image;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private ZombieEnemySpawner zombieSpawner = new ZombieEnemySpawner();
    private GunTrapEnemySpawner gunTrapSpawner = new GunTrapEnemySpawner();
    private SawEnemySpawner sawSpawner = new SawEnemySpawner();
    private SpikeEnemySpawner spikeSpawner = new SpikeEnemySpawner();
    private PlatformLampSpawner lampSpawner = new PlatformLampSpawner();
    private PlatformChestSpawner chestSpawner = new PlatformChestSpawner();
    
    private ZombieEnemy zombie;
    private GunTrapEnemy gunTrap;
    private SawEnemy saw;
    private SpikeEnemy spike;
    private PlatformLamp lamp;
    private TreasureChest chest;
    
    private int side = 0;	// Side the platform resides on. 0 = left, 1 = right.
    private boolean hit = false;

    /** Default instantiable constructor */
    public Platform() {}

    @Override
    public void update(float delta) {
	
	if (zombie != null) 	{ zombie.update(delta); }
	if (gunTrap != null)	{ gunTrap.update(delta); }
	if (saw != null)	{ saw.update(delta); }
	if (spike != null)	{ spike.update(delta); }
	if (lamp != null) 	{ lamp.update(delta); }
	if (chest != null)	{ chest.update(delta); }
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	if (saw != null)	{ saw.render(batch); }
	if (zombie != null)	{ zombie.render(batch); }
	if (gunTrap != null)	{ gunTrap.render(batch); }
	if (spike != null)	{ spike.render(batch); }
	if (lamp != null) 	{ lamp.render(batch); }
	if (chest != null)	{ chest.render(batch); }
	
	batch.draw(image, bounds.x, bounds.y);
	
	if (CurrentSettings.getInstance().showCollisions) {
	    batch.end();
	    shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.setColor(.5f, .5f, .5f, 1f);
	    shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	    shapeRenderer.end();
	    batch.begin();
	}
	
	
	
    }
    
    /** Adds an enemy to the platform if the random chance is true */
    public void addRandomEnemy() {
	if (MainEnemySpawner.getInstance().isZombiesEnabled() && zombieSpawner.isSpawning()) {
	    zombie = new ZombieEnemy(this);
	} else if (MainEnemySpawner.getInstance().isTrapsEnabled() && addRandomGunTrapEnemy()) {
	    gunTrap = new GunTrapEnemy(this);
	} else if (MainEnemySpawner.getInstance().isSawsEnabled() && addRandomSawEnemy()){
	    saw = new SawEnemy(this);
	} else if (MainEnemySpawner.getInstance().isSpikesEnabled() && addRandomSpikeEnemy()) {
	    spike = new SpikeEnemy(this);
	}
    }
    
    /** Adds a gun trap enemy to the platform if the random chance is true */
    public boolean addRandomGunTrapEnemy() {
	if (side != 2 && gunTrapSpawner.isSpawning()) {
	    return true;
	}
	return false;
    }
    /** Adds a rolling saw enemy to the platform if the random chance is true */
    public boolean addRandomSawEnemy() {
	if (sawSpawner.isSpawning()) {
	    return true;
	}
	return false;
    }
    /** Adds a spike enemy to the platform if the random chance is true */
    public boolean addRandomSpikeEnemy() {
	if (spikeSpawner.isSpawning()) {
	    return true;
	}
	return false;
    }
    
    /** Adds a lamp to the platform if the random chance is true */
    public void addRandomLamp() {
	if (lampSpawner.isSpawning()) {
	    lamp = new PlatformLamp(this);
	}
    }
    /** Adds a chest to the platform if the random chance is true */
    public void addRandomChest() {
	if (chestSpawner.isSpawning()) {
	    chest = new TreasureChest(this);
	} else {
	    if (Upgrades.getInstance().isEquipped(LuckyDogUpgrade.class)) {
		if (chestSpawner.isSpawning()) {
		    chest = new TreasureChest(this);
		    chest.setLucky(true);
		}
	    }
	}
    }
    
    public void setImage(Texture newImage) {
	this.image = newImage;
	adjustBoundsToImage(image);
    }
    
    @Override
    public void adjustBoundsToImage(Texture image) {
	this.setWidth(image.getWidth());
	this.setHeight(image.getHeight() - 2);
    }
    
    public ZombieEnemy getZombieEnemy() 	{ return this.zombie; }
    public GunTrapEnemy getGunTrapEnemy() 	{ return this.gunTrap; }
    public SawEnemy getSawEnemy()		{ return this.saw; }
    public SpikeEnemy getSpikeEnemy()		{ return this.spike; }
    public int getSide()			{ return this.side; }
    public boolean isHit()			{ return this.hit; }
    public void setSide(int newSide)		{ this.side = newSide; }
    public void setHit(boolean isHit)		{ this.hit = isHit; }
    
   

}

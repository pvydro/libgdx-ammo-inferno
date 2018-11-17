package com.flizzet.tutorial;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemies.Enemy;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.tutorialmap.TutorialMap;

/**
 * Dummy enemy for the tutorial.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DummyEnemy extends Enemy {

    private static final DummyEnemy INSTANCE = new DummyEnemy();
    
    private Texture image;
    
    /** Returns an instance of the DummyEnemy class */
    public static DummyEnemy getInstance() {
	return INSTANCE;
    }
    /** Default instantiable constructor */
    public DummyEnemy() {
	image = GameManager.getInstance().assetManager.get("tutorial/dummy.png");
	adjustBoundsToImage(image);
    }
    
    @Override
    public void update(float delta) {
	/* Set position */
	Platform platform = TutorialMap.getInstance().getPlatform();
	this.setX(platform.getCenterX() - (this.getWidth() / 2));
	this.setY(platform.getY() + platform.getHeight());
	
	/* Set collision position */
	this.setCollisionWidth(bounds.width);
	this.setCollisionHeight(bounds.height);
	this.setCollisionX(bounds.x);
	this.setCollisionY(bounds.y);
    }
    
    @Override
    public void render(SpriteBatch batch) {
	batch.draw(image, bounds.x, bounds.y);
    }

}

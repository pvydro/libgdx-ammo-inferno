package com.flizzet.tutorialmap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.main.GameManager;

/**
 * Background for the map of the tutorial.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class Background extends Entity {

    private Texture image;
    
    /** Default instantiable constructor */
    public Background() {
	this.image = GameManager.getInstance().assetManager.get("tutorial/background.png");
	this.adjustBoundsToImage(image);
    }

    @Override
    public void update(float delta) {
	/* Position */
	this.setX(MainCamera.getInstance().getCenterX() - (bounds.width / 2));
	this.setY(MainCamera.getInstance().getCenterY() - (bounds.height / 2));
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(image, bounds.x, bounds.y);
    }

}

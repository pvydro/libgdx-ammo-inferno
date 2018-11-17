package com.flizzet.score;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Coin score object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Coin extends ScoreObject {

    public Coin() {
	image = GameManager.getInstance().assetManager.get("items/coin.png", Texture.class);
	adjustBoundsToImage();
    }
    
}

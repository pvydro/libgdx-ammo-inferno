package com.flizzet.score;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Bill currency object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Bill extends ScoreObject {

    /** Default instantiable constructor */
    public Bill() {
	image = GameManager.getInstance().assetManager.get("items/bill.png", Texture.class);
	adjustBoundsToImage();
    }

}

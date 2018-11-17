package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete karate king head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class KarateKingHead extends Head {

    /** Default instantiable constructor */
    public KarateKingHead() {
	super(GameManager.getInstance().assetManager.get("player/head/karateKing.png", Texture.class),
		"karate king");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/karateKing.png");
    }

}

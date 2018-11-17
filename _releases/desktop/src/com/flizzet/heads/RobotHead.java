package com.flizzet.heads;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.headsystem.Head;
import com.flizzet.main.GameManager;

/**
 * Concrete robot head.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RobotHead extends Head {

    /** Default instantiable constructor */
    public RobotHead() {
	super(GameManager.getInstance().assetManager.get("player/head/robot.png", Texture.class),
		"robot");
	this.iconImage = GameManager.getInstance().assetManager.get("gui/headShop/icons/robot.png");
    }

}

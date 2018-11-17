package com.flizzet.settings;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.main.GameManager;

/**
 * Options to be used to control the player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public enum ControlType {

    TILT(GameManager.getInstance().assetManager.get("gui/settingsMenu/controls/tilt.png", Texture.class)),
    HORIZONTALARROWS(GameManager.getInstance().assetManager.get("gui/settingsMenu/controls/arrows.png", Texture.class)),
    CONSOLE(GameManager.getInstance().assetManager.get("gui/settingsMenu/controls/console.png", Texture.class)),
    JOYSTICK(GameManager.getInstance().assetManager.get("gui/settingsMenu/controls/joystick.png", Texture.class));
    
    private final Texture menuImage;
    
    ControlType(Texture menuImage) {
	this.menuImage = menuImage;
    }
    
    public Texture getMenuImage()	{ return this.menuImage; }

}

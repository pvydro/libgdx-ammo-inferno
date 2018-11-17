package com.flizzet.lighting;

import com.badlogic.gdx.graphics.Color;

/**
 * Ambience names.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
enum AmbienceColor {
    
    PURPLE(0.5f, 0.2f, 0.4f, 0.1f),
    ORANGE(0.7f, 0.4f, 0.2f, 0.1f),
    GREEN(0.7f, 0.9f, 0.5f, 0.1f),
    PINK(0.9f, 0.4f, 0.6f, 0.1f),
    BLUE(0.5f, 0.8f, 0.9f, 0.1f),
    BROWN(0.9f, 0.7f, 0.3f, 0.1f);
    
    private final float redValue;
    private final float greenValue;
    private final float blueValue;
    private final float alphaValue;
    
    AmbienceColor(float redValue, float greenValue, float blueValue, float alphaValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
        this.alphaValue = alphaValue;
    }
    
    /** Returns the color value of the enum */
    public Color getColor() {
	return new Color(redValue, greenValue, blueValue, alphaValue);
    }

    public float getRedValue() 		{ return redValue; }
    public float getGreenValue() 	{ return greenValue; }
    public float getBlueValue() 	{ return blueValue; }
    public float getAlphaValue() 	{ return alphaValue; }
    
}

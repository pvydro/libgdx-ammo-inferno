package com.flizzet.tutorialmap;

/**
 * Tutorial screen category.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public enum ScreenCategory {

    WELCOME("welcome to ammo inferno.\n\nclick the check button to proceed."),
    MOVEMENT("press the movement buttons to move.\ncontrols can be changed in the settings."),
    SELECTION("press the red target button to enable enemy selection mode.\n"
    	+ "test it by enabling enemy selection mode and clicking the dummy enemy"),
    HAVEFUN("go ahead and get started.");
    
    private String message;
    
    ScreenCategory(String message) {
	this.message = message;
    }
    
    public String getMessage()	{ return this.message; }
    
}

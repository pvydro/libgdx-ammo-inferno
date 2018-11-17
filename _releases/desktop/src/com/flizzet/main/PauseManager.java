package com.flizzet.main;

import com.flizzet.pausemenu.PauseMenu;
import com.flizzet.revivemenu.RevivedMenu;
import com.flizzet.selecttargetmenu.SelectTargetScreen;

/**
 * Handles changing the state of the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PauseManager {

    private static final PauseManager INSTANCE = new PauseManager();
    private GameState state = GameState.PLAY;
    
    /** Returns an instance of the PauseManager class */
    public static PauseManager getInstance() {
	return INSTANCE;
    }
    /** Single use constructor */
    private PauseManager() {}
    
    public GameState getState()			{ return this.state; }
    public void setState(GameState newState) { 
	/* Only change to pause if the user is in the play or tutorial state */
	if (GameManager.getInstance().stateManager.getCurrentState() == GameManager.getInstance().stateId_play
	|| GameManager.getInstance().stateManager.getCurrentState() == GameManager.getInstance().stateId_tutorial) {
	    this.state = newState;
	    if (newState == GameState.SELECT) {
		SelectTargetScreen.getInstance().enable();
	    }

	    if (newState == GameState.PAUSE) {
		PauseMenu.getInstance().enable();
	    }
	    
	    if (newState == GameState.REVIVE) {
		if (GameManager.getInstance().stateManager.getCurrentState() 
			== GameManager.getInstance().stateId_play) {
		    RevivedMenu.getInstance().enable();
		}
	    }
	}
    }

}

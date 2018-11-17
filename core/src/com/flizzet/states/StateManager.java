package com.flizzet.states;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.TransitionComponent;
import com.flizzet.main.GameManager;

/**
 * Holds and switches between all game states using their IDs.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StateManager {

	private ArrayList<GameState> gameStates; // All game states
	private int currentState; // ID of current state

	/** Creates game state manager */
	public StateManager() {
		gameStates = new ArrayList<GameState>();
	}

	/** Updates current state */
	public void update(float delta) {
		GameState state = getStateById(currentState);

		if (state != null) { // If the state isn't null, update it
			state.update(delta);
		}
	}

	/** Renders the current game state. Takes spritebatch for drawing */
	public void render(SpriteBatch batch) {

		GameState state = getStateById(currentState);

		if (state != null) { // If the state isn't null, render it
			state.render(batch);
		}

	}

	/** Enters game state by ID. Prints an error if state doesn't exist */
	public void enterState(int stateId) {

		getStateById(currentState).exited();

		GameState state = getStateById(stateId);
		final GameState fState = state;
		final int fStateId = stateId;

		if (stateId == GameManager.getInstance().stateId_loading) {
			try {
				state.entered();
				currentState = stateId;
			} catch (NullPointerException e) {
				System.err.println("State doesn't exist!");
				e.printStackTrace();
			}
		} else {
			TransitionComponent transition = new TransitionComponent(0, 0) {
				@Override
				public void triggered() {
					try {
						fState.entered();
						currentState = fStateId;
					} catch (NullPointerException e) {
						System.err.println("State doesn't exist!");
						e.printStackTrace();
					}
				}
			};

			GameManager.getInstance().transitionContainer
					.addToTransitions(transition);
		}
	}

	/** Returns the game state with the specified id */
	public GameState getStateById(int stateId) {

		for (int i = 0; i < gameStates.size(); i++) { // Go through states and
														// match stateId
			GameState state = gameStates.get(i);
			if (state.getId() == stateId) {
				return state;
			}
		}

		throw new NullPointerException("State doesn't exist!"); // Throw an
																// exception if
																// the state
																// doesn't exist
	}

	/** Adds a game state to be stored in the GameStateManager */
	public void addGameState(GameState gameState) {
		gameState.create(); // Starts gameState
		gameStates.add(gameState); // Adds state to ArrayList of states
	}

	/** Calls dispose method of all states, on game close. */
	public void dispose() {
		for (GameState state : gameStates) {
			state.dispose();
		}
	}

	public int getCurrentState() {
		return this.currentState;
	}

}

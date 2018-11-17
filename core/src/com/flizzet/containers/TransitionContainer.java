 package com.flizzet.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.TransitionComponent;

/**
 * Holds and displays transitions.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TransitionContainer extends Container {

	private ArrayList<TransitionComponent> allTransitions = new ArrayList<TransitionComponent>();
	private Queue<TransitionComponent> toBeAdded = new LinkedList<TransitionComponent>();
	private Queue<TransitionComponent> toBeRemoved = new LinkedList<TransitionComponent>();

	/** Default instantiable constructor */
	public TransitionContainer() {}

	@Override
	public void update(float delta) {
		allTransitions.addAll(toBeAdded);
		allTransitions.removeAll(toBeRemoved);
		toBeAdded.removeAll(toBeAdded);
		toBeRemoved.removeAll(toBeRemoved);

		for (TransitionComponent t : allTransitions) {
			t.update(delta);
		}
	}

	@Override
	public void render(SpriteBatch batch) {

		for (TransitionComponent t : allTransitions) {
			t.render(batch);
		}

	}

	/** Adds component to list of transition components */
	public void addToTransitions(TransitionComponent newTransition) {
		toBeAdded.add(newTransition);
	}

	/** Removes component from list of transition components */
	public void removeFromTransitions(TransitionComponent transition) {
		toBeRemoved.add(transition);
	}

	@Override
	public void dispose() {
	}

}

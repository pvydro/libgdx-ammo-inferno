/**
 * 
 */
package com.flizzet.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.GuiComponent;

/**
 * Holds gui components or something
 * 
 * @author Lucas Cirillo, Pedro Dutra (2017)
 * @version 1.0
 */
public class GuiContainer extends Container {

	private ArrayList<GuiComponent> allGui = new ArrayList<GuiComponent>();
	private Queue<GuiComponent> toBeAdded = new LinkedList<GuiComponent>();
	private Queue<GuiComponent> toBeRemoved = new LinkedList<GuiComponent>();

	/** Default constructor for instantiability in GameWorld */
	public GuiContainer() {
	}

	/** Updates all gui components */
	public void update(float delta) {

		allGui.addAll(toBeAdded);
		allGui.removeAll(toBeRemoved);
		toBeAdded.removeAll(toBeAdded);
		toBeRemoved.removeAll(toBeRemoved);

		for (GuiComponent g : allGui) {
			g.update(delta);
		}
	}

	/** Renders all gui components */
	public void render(SpriteBatch batch) {
		for (GuiComponent g : allGui) {
			g.render(batch);
		}
	}

	/** Adds component to list of gui components */
	public void addToGui(GuiComponent guiComponent) {
		toBeAdded.add(guiComponent);
	}

	/** Removes component from list of gui components */
	public void removeFromGui(GuiComponent guiComponent) {
		toBeRemoved.add(guiComponent);
	}

	/** Removes component from list of gui components using index */
	public void removeFromGui(int index) {
		toBeRemoved.add(allGui.get(index));
	}

	/** Returns the index position that the identified component is in */
	public int findIndex(GuiComponent guiComponent) {
		if (allGui.contains(guiComponent)) {
			return allGui.indexOf(guiComponent);
		}
		throw new NullPointerException(
				"This GuiComponent isn't in the GuiContainer!");
	}

	/** Returns an arraylist of all entities */
	public ArrayList<GuiComponent> getGuiComponents() {
		return allGui;
	}

	/** Clears all gui */
	public void clear() {
		toBeRemoved.addAll(allGui);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

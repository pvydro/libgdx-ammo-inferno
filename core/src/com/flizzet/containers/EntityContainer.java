package com.flizzet.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entities.Entity;

/**
 * Stores and draws physics objects
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EntityContainer extends Container {

	private ArrayList<Entity> allEntities = new ArrayList<Entity>();
	private Queue<Entity> toBeAdded = new LinkedList<Entity>();
	private Queue<Entity> toBeRemoved = new LinkedList<Entity>();

	public EntityContainer() {
	}

	@Override
	public void update(float delta) {

		allEntities.addAll(toBeAdded);
		allEntities.removeAll(toBeRemoved);
		toBeAdded.removeAll(toBeAdded);
		toBeRemoved.removeAll(toBeRemoved);

		for (Entity e : allEntities) {
			e.update(delta);
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		for (Entity e : allEntities) {
			e.render(batch);
		}

	}

	/** Adds to the entities */
	public void add(Entity newEntity) {
		toBeAdded.add(newEntity);
	}

	/** Adds list to the entities */
	public void add(ArrayList<Entity> newEntities) {
		toBeAdded.addAll(newEntities);
	}

	/** Removes from the entities */
	public void remove(Entity removedEntity) {
		toBeRemoved.add(removedEntity);
	}

	/** Clears all entities */
	public void clear() {
		toBeRemoved.addAll(allEntities);
	}

	/** Returns the arraylist of all entities */
	public ArrayList<Entity> getEntities() {
		return allEntities;
	}

	@Override
	public void dispose() {
	}

}

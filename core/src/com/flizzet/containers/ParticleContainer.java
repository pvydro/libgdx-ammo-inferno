package com.flizzet.containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.particles.Particle;

public class ParticleContainer extends Container {

	private ArrayList<Particle> allParticles = new ArrayList<Particle>();
	private Queue<Particle> toBeAdded = new LinkedList<Particle>();
	private Queue<Particle> toBeRemoved = new LinkedList<Particle>();
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	@Override
	public void update(float delta) {

		allParticles.addAll(toBeAdded);
		allParticles.removeAll(toBeRemoved);
		toBeAdded.removeAll(toBeAdded);
		toBeRemoved.removeAll(toBeRemoved);

		for (Particle p : allParticles) {
			p.update(delta);
		}

	}

	@Override
	public void render(SpriteBatch batch) {

		for (Particle p : allParticles) {
			p.render(batch, shapeRenderer);
		}

	}

	/** Adds to the list of particles */
	public void add(Particle newParticle) {
		toBeAdded.add(newParticle);
	}

	/** Adds a list to the list of particles */
	public void add(ArrayList<Particle> newParticles) {
		toBeAdded.addAll(newParticles);
	}

	/** Removes a particle from the list of particles */
	public void remove(Particle removedParticle) {
		toBeRemoved.add(removedParticle);
	}

	/** Grabs all particles */
	public ArrayList<Particle> getParticles() {
		return allParticles;
	}

	/** Clears all particles */
	public void clear() {
		toBeRemoved.addAll(allParticles);
	}

	@Override
	public void dispose() {
	}

}

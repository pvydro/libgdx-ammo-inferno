package com.flizzet.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.Camera;
import com.flizzet.containers.EntityContainer;
import com.flizzet.containers.GuiContainer;
import com.flizzet.containers.ParticleContainer;
import com.flizzet.main.GameManager;
import com.flizzet.utils.Assets;

/**
 * Basic game state class. Meant to be extended by concrete classes and is stored in GameStateManager.
 * 
 * @author Lucas Cirillo (2017)
 * @version 1.0
 */
public abstract class GameState {
    
    protected int id;			// Used to keep track of concrete game state classes
    protected GameManager gameManager;	// Allows access to managers and holders
    
    protected AssetManager assetManager;
    protected StateManager stateManager;
    protected GuiContainer guiContainer;
    protected Assets assets;
    protected EntityContainer entityContainer;
    protected ParticleContainer particleContainer;
    protected Camera camera;
    
    /** Sets the ID for this game state, holds game manager for switching */
    public GameState(int id, GameManager gameManager) {
	this.id = id;
	this.gameManager = gameManager;
	this.assetManager = gameManager.assetManager;
	this.stateManager = gameManager.stateManager;
	this.guiContainer = gameManager.guiContainer;
	this.entityContainer = gameManager.entityContainer;
	this.particleContainer = gameManager.particleContainer;
	this.assets = gameManager.assets;
	this.camera = gameManager.getCamera();
    }
    
    /** Called at the beginning of the game to load assets and initialize objects */
    public abstract void create();
    
    /** Called when this game state is entered */
    public abstract void entered();

    /** Updates objects in game state, gets called before render every frame */
    public abstract void update(float delta);
    
    /** Renders all objects in game state. Called after update once per frame. Takes batch for drawing */
    public abstract void render(SpriteBatch batch);
    
    /** Called when a different game state is entered while in this state */
    public abstract void exited();
    
    /** Called at the end of the game, dispose all garbage assets and stop all threads */
    public abstract void dispose();
    
    /** Returns state id */
    public int getId() {
	return id;
    }
    
}

package com.flizzet.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flizzet.camera.Camera;
import com.flizzet.camera.MainCamera;
import com.flizzet.containers.EntityContainer;
import com.flizzet.containers.GuiContainer;
import com.flizzet.containers.ParticleContainer;
import com.flizzet.containers.TransitionContainer;
import com.flizzet.lighting.Ambience;
import com.flizzet.particles.ParticleFunctions;
import com.flizzet.states.StateManager;
import com.flizzet.utils.Assets;

/**
 * Stores GameStateManager, MapManager, etc as well as hardcoded game data.

 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also GameStateManager
 */
public class GameManager {
    
    private static final GameManager INSTANCE = new GameManager();
    
    public StateManager stateManager;
    public AssetManager assetManager;
    public Assets assets;
    public GuiContainer guiContainer;
    public EntityContainer entityContainer;
    public ParticleContainer particleContainer;
    public TransitionContainer transitionContainer;
    public ParticleFunctions particleFunctions;
    public Ambience ambience;
    private Camera camera;
    private Stage stage;
    
    public final int stateId_loading = 			0;		// GameState ints
    public final int stateId_splash = 			1;
    public final int stateId_start = 			2;
    public final int stateId_play = 			3;
    public final int stateId_settings = 		4;
    public final int stateId_headShop =			5;
    public final int stateId_weaponShop = 		6;
    public final int stateId_upgradeShop = 		7;
    public final int stateId_controlsSettings = 8;
    public final int stateId_effectsSettings =	9;
    public final int stateId_generalSettings = 	10;
    public final int stateId_shop = 			11;
    public final int stateId_credits = 			12;
    public final int stateId_tutorial =			13;
    
    /** Returns an instance of GameManager */
    public static GameManager getInstance() {
    	return INSTANCE;
    }
    /** Default constructor for single use */
    public GameManager() {
		assetManager = new AssetManager();
		assets = new Assets(assetManager);
		
		guiContainer = new GuiContainer();
		entityContainer = new EntityContainer();
		particleContainer = new ParticleContainer();
		transitionContainer = new TransitionContainer();
		
		particleFunctions = new ParticleFunctions();
		ambience = new Ambience();
		stateManager = new StateManager();
		
		camera = MainCamera.getInstance();		// Set initial camera
		camera.createCamera();					// Create camera
		camera.getCamera().position.x = 120;	// Set position
		camera.getCamera().position.y = 200;
    }
    
    /** Camera */
    public Camera getCamera() 					{ return this.camera; }
    public void setCamera(Camera newCamera) 	{ this.camera = newCamera; }
    public Stage getStage()						{ return this.stage; }
    public void setStage(Stage newStage)		{ this.stage = newStage; }
   
    
}

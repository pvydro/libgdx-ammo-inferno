package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.camera.MainCamera;
import com.flizzet.dink.Dink;
import com.flizzet.enemies.MainEnemySpawner;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.ingamegui.TargetHandler;
import com.flizzet.lighting.AmbienceManager;
import com.flizzet.main.GameManager;
import com.flizzet.map.MapBuilder;
import com.flizzet.map.MapManager;
import com.flizzet.particles.BugParticleSpawner;
import com.flizzet.player.Player;
import com.flizzet.sound.Sounds;
import com.flizzet.stages.StageManager;
import com.flizzet.upgrades.DinkUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Play game state.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.01
 */
public class PlayState extends GameState {
    
    private MapBuilder mapBuilder;
    private Player player;
    private InGameGui inGameGui;
    private MainEnemySpawner enemySpawner;
    private BugParticleSpawner bugSpawner;
    private StageManager stageManager;
    private MapManager mapManager;
    private AmbienceManager ambienceManager;
    private TargetHandler targetHandler;
    
    public PlayState(int id, GameManager gameManager) {
	super(id, gameManager);
    }

    @Override
    public void create() {}

    @Override
    public void entered() {
	
	/* Play lava sounds */
	Sounds.play(Sounds.getInstance().lavaSound, 0.5f, true);
	
	guiContainer.clear();
	
	/* Set position for camera shakes */
	MainCamera.getInstance().setTargetX(MainCamera.getInstance().getCamera().position.x);
	MainCamera.getInstance().setTargetY(MainCamera.getInstance().getCamera().position.y);
	
	GameManager.getInstance().particleFunctions.loadMuzzleFlashes();	// XXX Loads all muzzle flashes? Wtf why here? Whatever who cares
	
	stageManager = StageManager.getInstance();
	stageManager.reset();
	
	mapManager = MapManager.getInstance();
	mapManager.reset();
	
	ambienceManager = AmbienceManager.getInstance();
	ambienceManager.reset();
	
	mapBuilder = new MapBuilder();
	mapBuilder.buildBackground();
	mapBuilder.buildPlatforms();
	
	player = Player.getInstance();
	player.reset();
	entityContainer.add(player);
	
	if (Upgrades.getInstance().isEquipped(DinkUpgrade.class)) {
	    Dink dink = new Dink();
	    entityContainer.add(dink);
	}
	
	enemySpawner = MainEnemySpawner.getInstance();
	enemySpawner.reset();
	entityContainer.add(enemySpawner);
	
	inGameGui = InGameGui.getInstance();
	inGameGui.buildGui();
	
	targetHandler = TargetHandler.getInstance();
	entityContainer.add(targetHandler);
	
	bugSpawner = BugParticleSpawner.getInstance();
	bugSpawner.reset();
	entityContainer.add(bugSpawner);
	
	mapBuilder.buildLava();
	mapBuilder.buildForeground();		// Build foreground after player so the player draws behind it
	
    }

    @Override
    public void update(float delta) {
	stageManager.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    /** In this case, called in a respawn */
    public void exited() {
	AdManager.getInstance().hideBannerAd();
	inGameGui.clear();
	entityContainer.clear();
	particleContainer.clear();
	guiContainer.clear();
    }

    @Override
    public void dispose() {
    }

}

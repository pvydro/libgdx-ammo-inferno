package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.ingamegui.TargetHandler;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.tutorial.DummyEnemy;
import com.flizzet.tutorialmap.TutorialMap;

/**
 * Tutorial state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TutorialState extends GameState {

    private TutorialMap map;
    private DummyEnemy enemy;
    private InGameGui inGameGui;
    private TargetHandler targetHandler;
    private Player player;
    
    /** Default instantiable constructor */
    public TutorialState(int id, GameManager gameManager) {
	super(id, gameManager);
    }

    @Override
    public void create() {
    }

    @Override
    public void entered() {
	/* Set position for camera shakes */
	MainCamera.getInstance().setTargetX(MainCamera.getInstance().getCamera().position.x);
	MainCamera.getInstance().setTargetY(MainCamera.getInstance().getCamera().position.y);
	GameManager.getInstance().particleFunctions.loadMuzzleFlashes();
	
	map = TutorialMap.getInstance();
	entityContainer.add(map);
	
	enemy = DummyEnemy.getInstance();
	entityContainer.add(enemy);
	
	inGameGui = InGameGui.getInstance();
	inGameGui.buildButtons();
	
	targetHandler = TargetHandler.getInstance();
	entityContainer.add(targetHandler);
	
	player = Player.getInstance();
	player.reset();
	entityContainer.add(player);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public void exited() {
	inGameGui.clear();
	entityContainer.clear();
	particleContainer.clear();
	guiContainer.clear();
    }

    @Override
    public void dispose() {
    }

}

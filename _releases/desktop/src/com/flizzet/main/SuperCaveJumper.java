package com.flizzet.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.flizzet.ads.AdHandler;
import com.flizzet.ads.AdManager;
import com.flizzet.camera.MainCamera;
import com.flizzet.entities.Entity;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.ingamegui.ScoreCounter;
import com.flizzet.ingamegui.TargetButton;
import com.flizzet.ingamegui.TargetHandler;
import com.flizzet.ingamegui.TargetIndicator;
import com.flizzet.music.MusicHandler;
import com.flizzet.particles.NextStageParticle;
import com.flizzet.particles.Particle;
import com.flizzet.particles.ScreenFlashParticle;
import com.flizzet.pausemenu.PauseMenu;
import com.flizzet.pausemenu.ResumeButton;
import com.flizzet.player.Player;
import com.flizzet.saving.Saves;
import com.flizzet.selecttargetmenu.SelectTargetScreen;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.states.ControlsSettingsState;
import com.flizzet.states.CreditsState;
import com.flizzet.states.EffectsSettingsState;
import com.flizzet.states.GeneralSettingsState;
import com.flizzet.states.HeadShopState;
import com.flizzet.states.LoadingState;
import com.flizzet.states.PlayState;
import com.flizzet.states.SettingsState;
import com.flizzet.states.ShopState;
import com.flizzet.states.SplashState;
import com.flizzet.states.StartMenuState;
import com.flizzet.states.TutorialState;
import com.flizzet.states.UpgradeShopState;
import com.flizzet.states.WeaponShopState;

//
//		  _oo0oo_
//	       	 o8888888o
//	       	 88" . "88
//		 (| -_- |)
//		 0\  =  /0
//	       ___/`---'\___
//	     .' \\|     |// '.
//	    / \\|||  :  |||// \
//	   / _||||| -:- |||||- \
//	  |   | \\\  -  /// |   |
// 	  | \_|  ''\---/''  |_/ |
// 	  \  .-\__  '-'  ___/-. /
//      ___'. .'  /--.--\  `. .'___
//   ."" '<  `.___\_<|>_/___.' >' "".
//  | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//  \  \ `_.   \_ __\ /__ _/   .-` /  /
// ==`-.____`.___ \_____/___.-`___.-'==
//		  `=---='
//
//
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
// Buddha bless the code
//

/**
 * Main class, creates and renders game.
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SuperCaveJumper extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private PauseManager pauseManager = PauseManager.getInstance();
    private AdManager adManager = AdManager.getInstance();
    private CurrentSettings settings = CurrentSettings.getInstance();
    
    public SuperCaveJumper(AdHandler handler) {
	adManager.setHandler(handler);
    }
    
    @Override
    public void create() {
	
	GameManager gameManager = GameManager.getInstance();
	batch = new SpriteBatch();
	batch.setProjectionMatrix(MainCamera.getInstance().getCamera().combined);				// Set projection to camera									// Create GameManager
	gameManager.stateManager.addGameState(new LoadingState(gameManager.stateId_loading, gameManager));	// Add the game states
	gameManager.stateManager.addGameState(new SplashState(gameManager.stateId_splash, gameManager));
	gameManager.stateManager.addGameState(new StartMenuState(gameManager.stateId_start, gameManager));
	gameManager.stateManager.addGameState(new PlayState(gameManager.stateId_play, gameManager));
	gameManager.stateManager.addGameState(new HeadShopState(gameManager.stateId_headShop, gameManager));
	gameManager.stateManager.addGameState(new WeaponShopState(gameManager.stateId_weaponShop, gameManager));
	gameManager.stateManager.addGameState(new UpgradeShopState(gameManager.stateId_upgradeShop, gameManager));
	gameManager.stateManager.addGameState(new SettingsState(gameManager.stateId_settings, gameManager));
	gameManager.stateManager.addGameState(new ControlsSettingsState(gameManager.stateId_controlsSettings, gameManager));
	gameManager.stateManager.addGameState(new EffectsSettingsState(gameManager.stateId_effectsSettings, gameManager));
	gameManager.stateManager.addGameState(new GeneralSettingsState(gameManager.stateId_generalSettings, gameManager));
	gameManager.stateManager.addGameState(new ShopState(gameManager.stateId_shop, gameManager));
	gameManager.stateManager.addGameState(new CreditsState(gameManager.stateId_credits, gameManager));
	gameManager.stateManager.addGameState(new TutorialState(gameManager.stateId_tutorial, gameManager));
	
	gameManager.stateManager.enterState(gameManager.stateId_loading);					// Enter initial state
	
	/* Instantly hide the banner ad */
	adManager.hideBannerAd();
	
	/* Touchpad */
	ScreenViewport screen = new ScreenViewport();
	screen.setCamera(GameManager.getInstance().getCamera().getCamera());
	GameManager.getInstance().setStage(new Stage(screen, batch));
	Gdx.input.setCatchBackKey(true);
	Gdx.input.setInputProcessor(GameManager.getInstance().getStage());
	
    }
    
    @Override
    public void resize(int width, int height) {
	GameManager.getInstance().getCamera().resize(width, height);
    }
    
    @Override
    public void render() {
	
	/* Update */
	switch (pauseManager.getState()) {
	case PLAY:
	    GameManager.getInstance().stateManager.update(Gdx.graphics.getDeltaTime()); 

	    GameManager.getInstance().entityContainer.update(Gdx.graphics.getDeltaTime());
	    GameManager.getInstance().particleContainer.update(Gdx.graphics.getDeltaTime());
	    GameManager.getInstance().guiContainer.update(Gdx.graphics.getDeltaTime());
	    GameManager.getInstance().transitionContainer.update(Gdx.graphics.getDeltaTime());

	    GameManager.getInstance().getCamera().update(Gdx.graphics.getDeltaTime());
	    batch.setProjectionMatrix(GameManager.getInstance().getCamera().getCombinedMatrix());

	    /* Ease reset camera transform */
	    if (GameManager.getInstance().stateManager.getCurrentState() == GameManager.getInstance().stateId_play) {
		MainCamera.getInstance().getCamera().position.y += 10
			* ((MainCamera.getInstance().getTargetY() - MainCamera.getInstance().getCamera().position.y) / 2)
			* Gdx.graphics.getDeltaTime();
		MainCamera.getInstance().getCamera().position.x += 10
			* ((MainCamera.getInstance().getTargetX() - MainCamera.getInstance().getCamera().position.x) / 2)
			* Gdx.graphics.getDeltaTime();
	    }
	    break;
	case SELECT:
	    
	    /* Update specified objects in the GuiContainer */
	    for (GuiComponent g : GameManager.getInstance().guiContainer.getGuiComponents()) {
		if (g instanceof TargetButton) {
		    g.update(Gdx.graphics.getDeltaTime());
		}
		if (g instanceof TargetIndicator) {
		    g.update(Gdx.graphics.getDeltaTime());
		}
		if (g instanceof ScoreCounter) {
		    g.update(Gdx.graphics.getDeltaTime());
		}
	    }
	    
	    /* Update specificed objects in the ParticleContainer */
	    for (Particle p : GameManager.getInstance().particleContainer.getParticles()) {
		if (p instanceof ScreenFlashParticle) {
		    p.update(Gdx.graphics.getDeltaTime());
		}
		if (p instanceof NextStageParticle) {
		    p.update(Gdx.graphics.getDeltaTime());
		}
	    }
	    
	    /* Update specified objects in the EntityContainer */
	    for (Entity e : GameManager.getInstance().entityContainer.getEntities()) {
		if (e instanceof TargetHandler) {
		    e.update(Gdx.graphics.getDeltaTime());
		}
	    }
	    
	    Player.getInstance().getWeapon().rotate(Gdx.graphics.getDeltaTime());
	    
	    MainCamera.getInstance().getCamera().position.y += 10
		* ((MainCamera.getInstance().getTargetY() - MainCamera.getInstance().getCamera().position.y) / 2)
		* Gdx.graphics.getDeltaTime();
	    MainCamera.getInstance().getCamera().position.x += 10
		* ((MainCamera.getInstance().getTargetX() - MainCamera.getInstance().getCamera().position.x) / 2)
		* Gdx.graphics.getDeltaTime();
	    break;
	case REVIVE:
	case PAUSE:
	    for (GuiComponent g : GameManager.getInstance().guiContainer.getGuiComponents()) {
		if (g instanceof ResumeButton) {
		    g.update(Gdx.graphics.getDeltaTime());
		}
	    }
	    
	    GameManager.getInstance().guiContainer.update(Gdx.graphics.getDeltaTime());
	    
	    MainCamera.getInstance().getCamera().position.y += 10
		* ((MainCamera.getInstance().getTargetY() - MainCamera.getInstance().getCamera().position.y) / 2)
		* Gdx.graphics.getDeltaTime();
	    MainCamera.getInstance().getCamera().position.x += 10
		* ((MainCamera.getInstance().getTargetX() - MainCamera.getInstance().getCamera().position.x) / 2)
		* Gdx.graphics.getDeltaTime();
	    break;
	}
	
	
	
	/* Render */
	Gdx.gl.glClearColor(0, 0, 0, 1); 			// Clear screen and graphics context, set bg to black
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.begin();						// Begin sprite batch
	
	MainCamera.getInstance().getCamera().translate(MainCamera.getInstance().getXShakeAmt(), MainCamera.getInstance().getYShakeAmt());
	
	GameManager.getInstance().stateManager.render(batch);	// Draw current game state
	
	GameManager.getInstance().entityContainer.render(batch);
	GameManager.getInstance().particleContainer.render(batch);
	if (!GameManager.getInstance().ambience.isAboveGui()) GameManager.getInstance().ambience.render(batch);
	GameManager.getInstance().guiContainer.render(batch);
	GameManager.getInstance().transitionContainer.render(batch);
	if (GameManager.getInstance().ambience.isAboveGui()) GameManager.getInstance().ambience.render(batch);

	/* Update select screen here due to ConcurrentModificationException */
	if (GameManager.getInstance().assetManager.update()) {
	    SelectTargetScreen.getInstance().update(Gdx.graphics.getDeltaTime());
	    SelectTargetScreen.getInstance().render(batch);
	}
	
	batch.end();						// End sprite batch

	/* Draw touchpad */
	GameManager.getInstance().getStage().act(Gdx.graphics.getDeltaTime());
	float currentX = 0;
	float currentY = 0;
	for (Actor a : GameManager.getInstance().getStage().getActors()) {
	    currentX = a.getX();
	    currentY = a.getY();
	    float shakeXOffset = (MainCamera.getInstance().getCamera().position.x - MainCamera.getInstance().getTargetX());
	    float shakeYOffset = (MainCamera.getInstance().getCamera().position.y - MainCamera.getInstance().getTargetY());
	    a.setX(currentX + shakeXOffset);
	    a.setY(currentY + shakeYOffset);
	}
	GameManager.getInstance().getStage().draw();
	for (Actor a : GameManager.getInstance().getStage().getActors()) {
	    a.setX(currentX);
	    a.setY(currentY);
	}
	
	
	/* Pausing */
	if (Gdx.input.isKeyJustPressed(Keys.P)) {
	    switch (pauseManager.getState()) {
	    case PAUSE:
		pauseManager.setState(GameState.PLAY);
		PauseMenu.getInstance().disable();
		break;
	    case PLAY:
		pauseManager.setState(GameState.PAUSE);
		break;
	    default:
		break;
	    }
	}
	
	/* Ads */
	adManager.update(Gdx.graphics.getDeltaTime());

	/* Play music all de fukkin time */
	if (GameManager.getInstance().stateManager.getCurrentState() != GameManager.getInstance().stateId_play
	&& GameManager.getInstance().stateManager.getCurrentState() != GameManager.getInstance().stateId_tutorial
	&& GameManager.getInstance().stateManager.getCurrentState() != GameManager.getInstance().stateId_loading) {
	    if (!MusicHandler.getInstance().getMenuMusic().isPlaying()) {
		MusicHandler.getInstance().play(MusicHandler.getInstance().getMenuMusic(), true);
	    }
	} else if (MusicHandler.getInstance().getMenuMusic() != null) {
	    if (MusicHandler.getInstance().getMenuMusic().isPlaying()) {
		MusicHandler.getInstance().stop(MusicHandler.getInstance().getMenuMusic());
	    }
	}
	
    }

    @Override
    public void dispose() {
	if (GameManager.getInstance().assetManager.update()) {
	    Saves.getInstance().save();				// Save everything if loaded
	}
	
	GameManager.getInstance().stateManager.dispose();	// Dispose asset manager
	GameManager.getInstance().assetManager.dispose();	// Dispose state manager
	
	Sounds.getInstance().dispose();
	batch.dispose();					// Dispose batch
	
    }
    
    public AdManager getAdManager()	{ return this.adManager; }
    public CurrentSettings getSettings(){ return this.settings; }
}

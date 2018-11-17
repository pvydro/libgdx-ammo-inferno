package com.flizzet.pausemenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.PauseMenuBuilder;
import com.flizzet.player.Player;
import com.flizzet.utils.FontUtils;

/**
 * Pause Menu
 * 
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PauseMenu extends GuiComponent {
    
    private static final PauseMenu INSTANCE = new PauseMenu();
    
    private PauseMenuBuilder menuBuilder = new PauseMenuBuilder();
    
    /* Fonts and layouts for text elements */
    private BitmapFont headerFont = FontUtils.UPHEAVAL_175;
    private String header = "paused at";
    private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
    //
    private BitmapFont scoreFont = FontUtils.UPHEAVAL_DEATHMENU_LARGE;
    private String score = "0";
    private GlyphLayout scoreLayout = new GlyphLayout(scoreFont, score);
    //
    private BitmapFont platformsFont = FontUtils.UPHEAVAL_175;
    private String platforms = "platforms";
    private GlyphLayout platformsLayout = new GlyphLayout(platformsFont, platforms);
    
    /* Positioning for text elements */
    private float headerX, headerY;
    private float scoreX, scoreY;
    private float platformsX, platformsY;
    //
    private final int MARGIN = 60;
    
    /** Returns an instance of the PauseMenu class */
    public static PauseMenu getInstance() {
	return INSTANCE;
    }
    /** Suppressed noninstantiable constructor */
    private PauseMenu() {
	super(0, 0);
	headerFont.setUseIntegerPositions(false);
	scoreFont.setUseIntegerPositions(false);
	
	/* Set platforms position 
	 * SET in constructor for the menuBuilder to use the margin */
	platformsX = MainCamera.getInstance().getCenterX() - (platformsLayout.width / 2);
	platformsY = MainCamera.getInstance().getHeight() - MARGIN 
		- headerLayout.height - 5 - scoreLayout.height - 5;
    }

    @Override
    public void update(float delta) {
	/* Set header position */
	headerX = MainCamera.getInstance().getCenterX() - (headerLayout.width / 2);
	headerY = MainCamera.getInstance().getHeight() - MARGIN;
	
	/* Set score position */
	scoreX = MainCamera.getInstance().getCenterX() - (scoreLayout.width / 2);
	scoreY = headerY - headerLayout.height - 5;
	
	/* Set platforms position */
	platformsX = MainCamera.getInstance().getCenterX() - (platformsLayout.width / 2);
	platformsY = scoreY - scoreLayout.height - 5;
	
	/* Back button */
	if (Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    menuBuilder.getResumeButton().triggered();
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	headerFont.draw(batch, header, headerX, headerY);
	scoreFont.draw(batch, score, scoreX, scoreY);
	platformsFont.draw(batch, platforms, platformsX, platformsY);
    }

    @Override
    public void triggered() {}
    
    /** Enables the pause menu */
    public void enable() {
	menuBuilder.buildMenu(platformsY - 100);
	GameManager.getInstance().guiContainer.addToGui(this);
	
	score = String.valueOf(Player.getInstance().getScore().getPlatforms());
	scoreLayout.setText(scoreFont, score);
	
	platforms = score.equals("1") ? "platform" : "platforms";
	platformsLayout.setText(platformsFont, platforms);
    }
    
    /** Disables the pause menu */
    public void disable() {
	menuBuilder.removeAll();
	GameManager.getInstance().guiContainer.removeFromGui(this);
    }
    
}

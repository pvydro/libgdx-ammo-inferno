package com.flizzet.deathmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.EconomyComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.menubuilders.DeathMenuBuilder;
import com.flizzet.player.Player;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.FontUtils;

/**
 * Menu that appears on death.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DeathMenu extends GuiComponent {
    
    private static final DeathMenu INSTANCE = new DeathMenu();
    
    private DeathMenuBuilder builder = new DeathMenuBuilder(this);
    private ShopSelectionMenu shopSelection = new ShopSelectionMenu(this);
    private EconomyComponent economy = new EconomyComponent();
    
    private BitmapFont fontSmall = FontUtils.UPHEAVAL_DEATHMENU_SMALL;
    private BitmapFont fontMedium = FontUtils.UPHEAVAL_DEATHMENU_MEDIUM;
    private BitmapFont fontLarge = FontUtils.UPHEAVAL_DEATHMENU_LARGE;
    private GlyphLayout smallLayout = new GlyphLayout(fontSmall, "");
    private GlyphLayout mediumLayout  = new GlyphLayout(fontMedium, "platforms");
    private GlyphLayout largeLayout = new GlyphLayout(fontLarge, String.valueOf(Player.getInstance().getScore().getPlatforms()));
    
    private float platformsScoreX = MainCamera.getInstance().getCenterX() - (largeLayout.width / 2);
    private float platformsTextX = MainCamera.getInstance().getCenterX() - (mediumLayout.width / 2);
    private float platformsHighTextX = MainCamera.getInstance().getCenterX() - (smallLayout.width / 2);
    private float xChange = 0;
    private boolean built = false;
    private boolean shopSelected = false;
    
    /** Returns an instance of the Death Menu class */
    public static DeathMenu getInstance() {
	return INSTANCE;
    }
    
    /** Default instantiable constructor */
    public DeathMenu() {
	super(0, 0);
    }

    @Override
    public void update(float delta) {
	if (built) {
	    
	    if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
		shopSelected = true;
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
	    } else if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
		shopSelected = false;
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
	    }
	    
	    /* Ease towards shop */
	    xChange += (0 - xChange) / 3;
	    if (shopSelected) {
		xChange += (-400 - bounds.x) / 10;
	    } else {
		xChange += (0 - bounds.x) / 10;
	    }
	    
	    /* Adding xChange to all components of the death menu */
	    for (ButtonComponent b : builder.getButtons()) {
		b.setX(b.getX() + xChange);
	    }
	    
	    /* Economy component */
	    economy.setX(economy.getX() + xChange);
	    
	    platformsScoreX += xChange;
	    platformsTextX += xChange;
	    platformsHighTextX += xChange;
	    bounds.x += xChange;
	   
	    shopSelection.update(delta);
	    economy.update(delta);
	    
	    /* Back button */
	    if (Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		if (shopSelected) shopSelected = false;
		Sounds.play(Sounds.getInstance().newSwoosh(), 1.0f);
	    }
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	if (built) {
	    fontSmall.setUseIntegerPositions(false);
	    fontSmall.setColor(Color.WHITE);
	    fontMedium.setUseIntegerPositions(false);
	    fontMedium.setColor(Color.WHITE);
	    fontLarge.setUseIntegerPositions(false);
	    fontLarge.setColor(Color.WHITE);
	    
	    /* Score text */
	    largeLayout.setText(fontLarge, String.valueOf(Player.getInstance().getScore().getPlatforms()));
	    fontLarge.draw(batch, String.valueOf(Player.getInstance().getScore().getPlatforms()), 
		    platformsScoreX, MainCamera.getInstance().getHeight() - 8 - largeLayout.height);
	    fontMedium.draw(batch, "platforms", platformsTextX, 
		    MainCamera.getInstance().getHeight() - 26 - largeLayout.height - mediumLayout.height);
	    fontSmall.draw(batch, "high: " + String.valueOf(Player.getInstance().getScore().getHighScore()),
		    platformsHighTextX, MainCamera.getInstance().getHeight() - builder.getMargin() - 33
		    - largeLayout.height - mediumLayout.height - smallLayout.height);
	    
	    shopSelection.render(batch);
	    economy.render(batch);
	}
	
    }
    
    /** Disables the death menu and removes all elements from the screen */
    public void disable() {
	shopSelection.disable();
	/* Remove all buttons */
	for (ButtonComponent b : builder.getButtons()) {
	    GameManager.getInstance().guiContainer.removeFromGui(b);
	}
	
	/* Disable all other things */
	GameManager.getInstance().guiContainer.removeFromGui(this);
	builder.getButtons().clear();
	GameManager.getInstance().ambience.setAboveGui(false);
	built = false;
	shopSelected = false;
	xChange = 0;
	bounds.x = 0;
	economy.setX(0);
    }
    
    /** Enables the death menu and builds all elemnts */
    public void enable() {
	xChange = 0;
	bounds.x = 0;
	builder.buildMenu();
	shopSelection.enable();
	largeLayout = new GlyphLayout(fontLarge, String.valueOf(Player.getInstance().getScore().getPlatforms()));
	smallLayout = new GlyphLayout(fontSmall, "high: " + String.valueOf(Player.getInstance().getScore().getHighScore()));
	shopSelected = false;
	platformsScoreX = MainCamera.getInstance().getCenterX() - (largeLayout.width / 2);
	platformsTextX = MainCamera.getInstance().getCenterX() - (mediumLayout.width / 2);
	platformsHighTextX = MainCamera.getInstance().getCenterX() - (smallLayout.width / 2);
	
	AdManager.getInstance().showBannerAd();
    }

    @Override
    public void triggered() {}
    
    public DeathMenuBuilder getBuilder()		{ return this.builder; }
    public float getXChange()				{ return this.xChange; }
    public boolean isShopSelected()			{ return this.shopSelected; }
    public boolean isEnabled()				{ return this.built; }
    
    public void setBuilt(boolean isBuilt)		{ this.built = isBuilt; }
    public void setShopSelected(boolean isSelected)	{ this.shopSelected = isSelected; }

}

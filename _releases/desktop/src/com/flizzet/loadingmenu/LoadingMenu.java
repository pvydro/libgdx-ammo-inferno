package com.flizzet.loadingmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.ShineOverlay;
import com.flizzet.lighting.LoadingBarLight;
import com.flizzet.main.GameManager;
import com.flizzet.utils.FontUtils;

/**
 * Concrete loading menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LoadingMenu extends GuiComponent {

    private Texture background;
    private ShineOverlay overlay;
    private float backgroundX, backgroundY;
    
    /* "Loading" header text */
    private BitmapFont headerFont = FontUtils.UPHEAVAL_175;
    private String header = "loading";
    private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
    private float headerX, headerY;
    
    /* Percentage text */
    private BitmapFont amountFont = FontUtils.UPHEAVAL_120;
    private String amount = "0%";
    private GlyphLayout amountLayout = new GlyphLayout(amountFont, amount);
    private float amountX, amountY;
    
    /* Loading bar */
    private Texture barFill;
    private Texture barOutline;
    private LoadingBarLight light;
    private float loadingBarX, loadingBarY;
    
    /* Splash logo */
    private Texture logo;
    private float logoX, logoY;
    
    private final int MARGIN = 100;
    private boolean loaded = false;
    
    /** Default instantiable constructor */
    public LoadingMenu() {
	super(0, 0);
	headerFont.setUseIntegerPositions(false);
    }

    @Override
    public void update(float delta) {
	
	/* Loading textures
	 * Single call */
	if (!loaded) {
	    /* Finish loading all textures */
	    GameManager.getInstance().assetManager.finishLoadingAsset("gui/loadingMenu/background.png");
	    GameManager.getInstance().assetManager.finishLoadingAsset("gui/loadingMenu/barFill.png");
	    GameManager.getInstance().assetManager.finishLoadingAsset("gui/loadingMenu/barOutline.png");
	    GameManager.getInstance().assetManager.finishLoadingAsset("lights/loadingBarLight.png");
	    GameManager.getInstance().assetManager.finishLoadingAsset("gui/constant/menuBgOverlayOpaque.png");
	    GameManager.getInstance().assetManager.finishLoadingAsset("gui/loadingMenu/logo.png");
	    /* Set all textures */
	    background = GameManager.getInstance().assetManager.get("gui/loadingMenu/background.png", Texture.class);
	    barFill = GameManager.getInstance().assetManager.get("gui/loadingMenu/barFill.png", Texture.class);
	    barOutline = GameManager.getInstance().assetManager.get("gui/loadingMenu/barOutline.png", Texture.class);
	    logo = GameManager.getInstance().assetManager.get("gui/loadingMenu/logo.png", Texture.class);
	    overlay = new ShineOverlay();
	    light = new LoadingBarLight(this);
	    /* Single call the if */
	    loaded = true;
	}
	
	/* Position background */
	backgroundX = MainCamera.getInstance().getCenterX() - (background.getWidth() / 2);
	backgroundY = MainCamera.getInstance().getCenterY() - (background.getHeight() / 2);
	
	/* Position header */
	headerX = MainCamera.getInstance().getCenterX() - (headerLayout.width / 2);
	headerY = MainCamera.getInstance().getHeight() - MARGIN;
	
	/* Position logo */
	logoX = MainCamera.getInstance().getCenterX() - (logo.getWidth() / 2);
	logoY = MainCamera.getInstance().getCenterY() - (logo.getHeight() / 2);
	
	/* Position loading bar */
	loadingBarX = MainCamera.getInstance().getCenterX() - (barOutline.getWidth() / 2);
	loadingBarY = MARGIN;
	
	/* Positioning amount text */
	float newWidth = barFill.getWidth() * GameManager.getInstance().assetManager.getProgress();
	amount = String.valueOf((int) (GameManager.getInstance().assetManager.getProgress() * 100)) + "%";
	amountLayout.setText(amountFont, amount);
	amountX = loadingBarX + newWidth - amountLayout.width - 2;
	amountY = loadingBarY + (barOutline.getHeight() / 2) + (amountLayout.height / 2);
	
	/* Positioning loading bar light */
	light.setWidth(newWidth + 200);
	light.setX(loadingBarX + (newWidth / 2) - (light.getWidth() / 2));
	light.setY(loadingBarY + (barOutline.getHeight() / 2) - (light.getHeight() / 2));
    }

    @Override
    public void render(SpriteBatch batch) {
	
	/* Drawing background */
	batch.draw(background, backgroundX, backgroundY);
	batch.draw(logo, logoX, logoY);
	overlay.render(batch);
	
	/* Drawing loading header text */
	headerFont.draw(batch, header, headerX, headerY);
	
	/* Drawing loading bar */
	batch.draw(barOutline, loadingBarX, loadingBarY);
	float newWidth = barFill.getWidth() * GameManager.getInstance().assetManager.getProgress();
	batch.draw(barFill, loadingBarX + 4, loadingBarY + 4, newWidth, barFill.getHeight());

	/* Drawing amount text */
	amountFont.draw(batch, amount, amountX, amountY);
	
	/* Drawing light */
	light.render(batch);
    }

    @Override
    public void triggered() {}
    
    public boolean isLoaded()	{ return this.loaded; };

}

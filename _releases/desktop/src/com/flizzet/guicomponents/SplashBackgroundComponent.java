package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.main.GameManager;

/**
 * Desc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SplashBackgroundComponent extends GuiComponent {
    
    private Texture backgroundImage;

    /** Default instantiable constructor */
    public SplashBackgroundComponent(float x, float y) {
	super(x, y);
	
	backgroundImage = GameManager.getInstance().assetManager.get("gui/splashScreen/sky.png", Texture.class);
	
	float newCenterX = GameManager.getInstance().getCamera().getWidth() / 2;
	float newCenterY = GameManager.getInstance().getCamera().getHeight() / 2;
	float backgroundX = newCenterX - (backgroundImage.getWidth() / 2);
	float backgroundY = newCenterY - (backgroundImage.getHeight() / 2);
	
	setX(backgroundX);
	setY(backgroundY);
    }

    @Override
    public void update(float delta) {
	/** TODO Auto-generated method stub */
	
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(backgroundImage, bounds.x, bounds.y);
    }

    @Override
    public void triggered() {
	/** TODO Auto-generated method stub */
	
    }

}

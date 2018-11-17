package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.flizzet.camera.MainCamera;
import com.flizzet.lighting.PromptLight;
import com.flizzet.main.GameManager;
import com.flizzet.particles.ScreenDarkener;
import com.flizzet.utils.FontUtils;

/**
 * Displays information with an accept option.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class InfoPrompt extends GuiComponent {
    
    private PromptLight light = new PromptLight(this);
    private BitmapFont infoFont = FontUtils.UPHEAVAL_90;
    private String info = "";
    private GlyphLayout infoLayout = new GlyphLayout(infoFont, info);
    private float infoX, infoY;
    
    private ScreenDarkener screenDarkener = new ScreenDarkener(0.5f);
    private Texture backgroundImage;
    private float bgX, bgY;
    
    private InfoAcceptButton acceptButton = new InfoAcceptButton(this);
    
    /** Default instantiable constructor */
    public InfoPrompt(String message) {
	super(0, 0);
	this.info = message;
	this.infoFont.setUseIntegerPositions(false);
	
	backgroundImage = GameManager.getInstance().assetManager.get("gui/settingsMenu/infoPrompt/promptBg.png", Texture.class);
	
	infoLayout.setText(infoFont, message, Color.WHITE, backgroundImage.getWidth() - 20, Align.center,true);
    }

    @Override
    public void update(float delta) {
	
	/* Position background */
	bgX = MainCamera.getInstance().getCenterX() - (backgroundImage.getWidth() / 2);
	bgY = MainCamera.getInstance().getCenterY() - (backgroundImage.getHeight() / 2);
	
	/* Position text */
	infoX = bgX + 10;
	infoY = MainCamera.getInstance().getCenterY() + (infoLayout.height / 2);
	
	/* Position accept button */
	acceptButton.setX(bgX - 2);
	acceptButton.setY(bgY - 2);
	acceptButton.update(delta);
	
	/* Light */
	light.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	screenDarkener.render(batch);
	batch.draw(backgroundImage, bgX, bgY);
	infoFont.draw(batch, infoLayout, infoX, infoY);
	acceptButton.render(batch);
	light.render(batch);
    }

    @Override
    public void triggered() {}

}

class InfoAcceptButton extends ButtonComponent {
    
    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/reviveMenu/buttonShine.png", Texture.class);
    private InfoPrompt prompt;
    
    public InfoAcceptButton(InfoPrompt prompt) {
	super(0, 0);
	
	this.prompt = prompt;
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/settingsMenu/infoPrompt/acceptButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/settingsMenu/infoPrompt/acceptButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/settingsMenu/infoPrompt/acceptButtonPushed.png", Texture.class));
    }
    
    @Override
    public void render(SpriteBatch batch) {
	super.render(batch);
	if (isPushed()) {
	    batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2, bounds.width - 4, bounds.height - 4);
	} else {
	    batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width, bounds.height);
	}
    }
    
    @Override
    public void triggered() {
	GameManager.getInstance().guiContainer.removeFromGui(prompt);
    }
    
}

package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.FontUtils;

/**
 * Checkbox, selectable, for enabling or disabling things.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CheckboxComponent extends GuiComponent {
    
    protected CheckboxState state = CheckboxState.ON;
    private CheckboxInfoButton infoButton = new CheckboxInfoButton("");
    private Texture imageOn;
    private Texture imageOff;
    private String title = "";
    private BitmapFont titleFont = FontUtils.UPHEAVAL_85;
    private GlyphLayout titleLayout = new GlyphLayout(titleFont, title);
    
    private float titleX, titleY;
    private float initialX, initialY;
    private boolean pushed;
    
    /** Default instantiable constructor */
    public CheckboxComponent(String title, String info, boolean param) {
	super(0, 0);
	
	infoButton.setInfo(info);
	this.title = title;
	this.titleLayout.setText(titleFont, title);
	this.titleFont.setUseIntegerPositions(false);
	
	this.imageOn = GameManager.getInstance().assetManager.get("gui/settingsMenu/constant/checkboxOn.png");
	this.imageOff = GameManager.getInstance().assetManager.get("gui/settingsMenu/constant/checkboxOff.png");
	this.adjustBoundsToImage();
	
	/* Set to state */
	if (param) state = CheckboxState.ON;
	else state = CheckboxState.OFF;
    }

    @Override
    public void update(float delta) {
	/* Clicking */
	if (Gdx.input.isTouched()) {
	    /* Single call */
	    if (!pushed) {
		initialX = MainCamera.getInstance().getMousePos().x;
		initialY = MainCamera.getInstance().getMousePos().y;
		pushed = true;
	    }
	} else {
	    if (pushed) {
		if (bounds.contains(MainCamera.getInstance().getMousePos().x,
			MainCamera.getInstance().getMousePos().y)
			&& bounds.contains(initialX, initialY)) {
		    clicked();
		}
	    }
	    pushed = false;
	}
	
	/* Setting title position */
	titleX = 20;
	titleY = this.getY() + (this.getHeight() / 2) + (titleLayout.height / 2);
	
	/* Settings info button position */
	infoButton.setX(titleX + titleLayout.width - 12);
	infoButton.setY(titleY - (titleLayout.height / 2) - (infoButton.getHeight() / 2));
	infoButton.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	/* Darkening when pushed */
	Color tmp = batch.getColor();
	
	if (Gdx.input.isTouched()) {
	    if (bounds.contains(MainCamera.getInstance().getMousePos().x,
			MainCamera.getInstance().getMousePos().y)) {
		batch.setColor(new Color (tmp.r - 0.3f, tmp.g - 0.3f, tmp.b - 0.3f, 1.0f));
	    }
	}
	
	/* Drawing image based on state */
	if (state == CheckboxState.ON) 		batch.draw(imageOn, bounds.x, bounds.y);
	else if (state == CheckboxState.OFF)	batch.draw(imageOff, bounds.x, bounds.y);
	
	/* Resetting color */
	batch.setColor(tmp);
	
	/* Drawing text */
	titleFont.draw(batch, title, titleX, titleY);
	
	/* Drawing info button */
	infoButton.render(batch);
    }

    @Override
    public void triggered() {}
    
    public void clicked() {
	switch (state) {
	case ON:
	    state = CheckboxState.OFF;
	    triggered();
	    break;
	case OFF:
	    state = CheckboxState.ON;
	    triggered();
	    break;
	}
    }
    
    /** Sets dimensions to image width and height */
    private void adjustBoundsToImage() {
	this.setWidth(imageOn.getWidth());
	this.setHeight(imageOn.getHeight());
    }
    
    public CheckboxState getState()			{ return this.state; }
    public void setState(CheckboxState newState)	{ this.state = newState; }

}

class CheckboxInfoButton extends ButtonComponent {
    private String info = "";
    
    CheckboxInfoButton(String info) {
	super(0, 0);
	this.info = info;
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/settingsMenu/constant/checkboxInfoButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/settingsMenu/constant/checkboxInfoButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/settingsMenu/constant/checkboxInfoButtonPushed.png", Texture.class));
    }
    
    @Override
    public void update(float delta) {
	/* Prevent the button from being pushable if there is already an info prompt on the screen */
	for (GuiComponent g : GameManager.getInstance().guiContainer.getGuiComponents()) {
	    if (g instanceof InfoPrompt) {
		return;
	    }
	}
	super.update(delta);
    }
    
    @Override
    public void triggered() {
	/* Play sound */
	Sounds.play(Sounds.getInstance().infoSound, 1.0f);
	
	/* Add prompt */
	InfoPrompt prompt = new InfoPrompt(info);
	GameManager.getInstance().guiContainer.addToGui(prompt);
    }
    
    public void setInfo(String newInfo) {
	this.info = newInfo;
    }
    
}

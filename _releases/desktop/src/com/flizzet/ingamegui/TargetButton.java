package com.flizzet.ingamegui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;
import com.flizzet.selecttargetmenu.SelectTargetScreen;
import com.flizzet.settings.ControlType;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.upgrades.SwiftUpgrade;
import com.flizzet.upgradesystem.Upgrades;

/**
 * Button to trigger enemy selection mode.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TargetButton extends ButtonComponent {
    
    private Texture cooldownBarImage;
    
    private int currentPointer;
    private boolean triggered = false;
    private float alpha = 0;
    private float maxCooldown = 300;
    private float cooldown = maxCooldown;
    
    private static final TargetButton INSTANCE = new TargetButton();

    /** Returns an instance of the TargetButton class */
    public static TargetButton getInstance() {
	return INSTANCE;
    }
    
    /** Default instantiable constructor */
    public TargetButton() {
	super(0, 0);
	
	reset();	// Find an image
	
	this.cooldownBarImage = GameManager.getInstance().assetManager.get("gui/inGame/movement/targetCooldownBar.png");
    }
    
    @Override
    public void update(float delta) {
	
	state = BtnState.DEFAULT;
	
	/* Iterate through all possible touches for multitouch */
	for (int i = 0; i < 20; i++) {
	    if (Gdx.input.isTouched(i)) {
		final float mouseX = MainCamera.getInstance().getMousePos(i).x;
		final float mouseY = MainCamera.getInstance().getMousePos(i).y;

		if (bounds.contains(mouseX, mouseY)) {
		    currentPointer = i;
		} else {
		    currentPointer = 19;
		}
	    }
	}
	
	if (Gdx.input.isTouched(currentPointer)) {
	    state = BtnState.PUSHED;
	    if (!triggered) {
		triggered();
	    }
	    
	    alpha += (1 - alpha) / 2;
	} else {
	    state = BtnState.DEFAULT;
	    triggered = false;
	    
	    alpha += (0.5f - alpha) / 2;
	}
	
	subtextY = bounds.getY() - 1;
	
	/* Button cooldown */
	maxCooldown = Upgrades.getInstance().isEquipped(SwiftUpgrade.class) ? 50 : 300;
	
	if (cooldown < maxCooldown) cooldown++;
	
    }
    
    @Override
    public void render(SpriteBatch batch) {
	Color tmp = batch.getColor();
	
	if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
	    batch.setColor(tmp.r, tmp.g, tmp.b, alpha);
	    super.render(batch);
	    batch.setColor(tmp);
	} else {
	    super.render(batch);
	}
	
	/* Drawing cooldown bar */
	/* Add alpha if necessary */
	if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
	    batch.setColor(tmp.r, tmp.g, tmp.b, alpha);
	}
	float shakeXOffset = (MainCamera.getInstance().getCamera().position.x - MainCamera.getInstance().getTargetX());
	float shakeYOffset = (MainCamera.getInstance().getCamera().position.y - MainCamera.getInstance().getTargetY());
	batch.draw(cooldownBarImage,
		this.getX() + 3 + shakeXOffset,
		this.getY() + shakeYOffset + this.getHeight() - cooldownBarImage.getHeight() - 5,
		(this.getWidth() - 6) * (cooldown / maxCooldown), 4);
	if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
	    batch.setColor(tmp);
	}
	    
    }
    
    @Override
    public void triggered() {
	if (cooldown == maxCooldown) {
	    triggered = true;

	    if (PauseManager.getInstance().getState() == GameState.SELECT) {
		GameManager.getInstance().particleFunctions.addScreenFlash(1.0f);
		SelectTargetScreen.getInstance().disable();
		PauseManager.getInstance().setState(GameState.PLAY);

		cooldown = 0;
	    } else if (PauseManager.getInstance().getState() == GameState.PLAY) {
		GameManager.getInstance().particleFunctions.addScreenFlash(1.0f);
		GameManager.getInstance().particleContainer.update(Gdx.graphics.getDeltaTime());
		PauseManager.getInstance().setState(GameState.SELECT);
	    }
	}
    }
    
    /** Finds which image to use based on the setting */
    public void reset() {
	switch (CurrentSettings.getInstance().controls) {
	case HORIZONTALARROWS:
	    this.setImage(GameManager.getInstance().assetManager.get("gui/inGame/movement/arrows/targetBtn.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/inGame/movement/arrows/targetBtn.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/inGame/movement/arrows/targetBtnPushed.png", Texture.class));
	    break;
	case CONSOLE:
	    this.setImage(GameManager.getInstance().assetManager.get("gui/inGame/movement/console/targetBtn.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/inGame/movement/console/targetBtn.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/inGame/movement/console/targetBtnPushed.png", Texture.class));
	    break;
	case TILT:
	case JOYSTICK:
	    this.setImage(GameManager.getInstance().assetManager.get("gui/inGame/movement/joystick/targetBtn.png", Texture.class));
	    this.setImageHovered(GameManager.getInstance().assetManager.get("gui/inGame/movement/joystick/targetBtn.png", Texture.class));
	    this.setImagePushed(GameManager.getInstance().assetManager.get("gui/inGame/movement/joystick/targetBtnPushed.png", Texture.class));
	    break;
	default:
	    break;
	}
    }

}

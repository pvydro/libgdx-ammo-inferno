package com.flizzet.ingamegui;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.settings.ControlType;
import com.flizzet.settings.CurrentSettings;

/**
 * Builds the in game gui. Controls, health, etc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class InGameGui {
    
    private static final InGameGui INSTANCE = new InGameGui();
    
    private JumpButton jumpButton;
    private MoveRightButton moveRightButton;
    private MoveLeftButton moveLeftButton;
    private TargetButton targetButton;
    private TouchpadJoystick touchpad;
    private JoystickComponent joystick;
    private TargetIndicator targetIndicator;
    private ScoreCounter scoreCounter = new ScoreCounter();
    private ArrayList<GuiComponent> buttons = new ArrayList<GuiComponent>();
    
    private static final float MARGIN = 20;
    
    /** Returns an instance of the InGameGui */
    public static InGameGui getInstance() {
	return INSTANCE;
    }
    /** Single use constructor */
    private InGameGui() {}
    
    public void buildGui() {
	
	/* Movement Buttons */
	buttons.add(jumpButton = new JumpButton());
	buttons.add(moveRightButton = new MoveRightButton());
	buttons.add(moveLeftButton = new MoveLeftButton());
	buttons.add(targetButton = TargetButton.getInstance());
	
	/* Build the elements based on the control type */
	if (CurrentSettings.getInstance().controls == ControlType.HORIZONTALARROWS) {
	    
	    jumpButton.setX(MainCamera.getInstance().getCenterX() - (jumpButton.getWidth() / 2));
	    jumpButton.setY(MARGIN + jumpButton.getHeight() - 3);
	    moveRightButton.setX(jumpButton.getX() + moveRightButton.getWidth());
	    moveRightButton.setY(MARGIN);
	    moveLeftButton.setX(jumpButton.getX());
	    moveLeftButton.setY(MARGIN);
	    targetButton.setX(MainCamera.getInstance().getWidth() - targetButton.getWidth() - MARGIN);
	    targetButton.setY(MARGIN);
	    
	} else if (CurrentSettings.getInstance().controls == ControlType.CONSOLE) {
	    
	    float fullWidth = moveLeftButton.getWidth() + moveRightButton.getWidth() + targetButton.getWidth()
		    + jumpButton.getWidth();
	    float left = MainCamera.getInstance().getCenterX() - (fullWidth / 2);
	    float right = MainCamera.getInstance().getCenterX() + (fullWidth / 2);
	    
	    /* X */
	    moveLeftButton.setX(left);
	    moveRightButton.setX(left + moveLeftButton.getWidth());
	    jumpButton.setX(right - jumpButton.getWidth());
	    float distanceBetweenJumpAndRight = jumpButton.getX() - moveRightButton.getX();	// HERE GO with the LOOOONG VARIABLE NAME YUHHH COPY PASTE GANG
	    targetButton.setX(moveRightButton.getX() + (distanceBetweenJumpAndRight / 2));
	    /* Y */
	    float margin = moveLeftButton.getX();
	    for (GuiComponent b : buttons) {
		b.setY(margin);
	    }
	    GameManager.getInstance().guiContainer.removeFromGui(moveLeftButton);
	    GameManager.getInstance().guiContainer.removeFromGui(moveRightButton);
	    
	    touchpad = new TouchpadJoystick();
	    touchpad.getTouchpad().setBounds(left, margin, 72.0f, 72.0f);
	    GameManager.getInstance().getStage().addActor(touchpad.getTouchpad());
	    
	} else if (CurrentSettings.getInstance().controls == ControlType.JOYSTICK) {
	    joystick = new JoystickComponent();
	    joystick.setX(MainCamera.getInstance().getCenterX() - (joystick.getWidth() / 2));
	    joystick.setY(MARGIN);
	    targetButton.setX(MainCamera.getInstance().getWidth() - MARGIN - targetButton.getWidth());
	    targetButton.setY(MARGIN);
	    GameManager.getInstance().guiContainer.addToGui(targetButton);
	    GameManager.getInstance().guiContainer.addToGui(joystick);
	} else if (CurrentSettings.getInstance().controls == ControlType.TILT) {
	    targetButton.setX(MainCamera.getInstance().getCenterX() - (targetButton.getWidth() / 2));
	    targetButton.setY(MARGIN);
	}

	if (CurrentSettings.getInstance().controls != ControlType.TILT 
	&& CurrentSettings.getInstance().controls != ControlType.JOYSTICK) {
	    for (GuiComponent b : buttons) {
		GameManager.getInstance().guiContainer.addToGui(b);
	    }
	}
	GameManager.getInstance().guiContainer.addToGui(targetButton);
	if (CurrentSettings.getInstance().controls == ControlType.TILT) {
	    GameManager.getInstance().guiContainer.addToGui(targetButton);
	}
	
	targetButton.reset();

	GameManager.getInstance().guiContainer.addToGui(scoreCounter);
	
	/* Target indicator */
	targetIndicator = new TargetIndicator();
	GameManager.getInstance().guiContainer.addToGui(targetIndicator);
	
    }
    
    /** Builds the InGameGui without score or pause buttons */
    public void buildButtons() {
	buildGui();
	GameManager.getInstance().guiContainer.removeFromGui(scoreCounter);
    }
    
    public void clear() {

	if (CurrentSettings.getInstance().controls == ControlType.JOYSTICK) {
	    GameManager.getInstance().guiContainer.removeFromGui(targetButton);
	    GameManager.getInstance().guiContainer.removeFromGui(joystick);
	}
	
	for (GuiComponent b : buttons) {
	    GameManager.getInstance().guiContainer.removeFromGui(b);
	}
	
	GameManager.getInstance().guiContainer.removeFromGui(scoreCounter);
	GameManager.getInstance().guiContainer.removeFromGui(targetIndicator);
	
	for (Actor a : GameManager.getInstance().getStage().getActors()) {
	    a.remove();
	}
	
	buttons.clear();
	
    }
    
    public ScoreCounter getScoreCounter()		{ return this.scoreCounter; }
    public ArrayList<GuiComponent> getButtons()		{ return this.buttons; }
    public TouchpadJoystick getTouchpad()		{ return this.touchpad; }

}

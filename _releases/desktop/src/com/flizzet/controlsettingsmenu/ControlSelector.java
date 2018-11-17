package com.flizzet.controlsettingsmenu;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.FontUtils;

/**
 * Gui element for selecting from a list of possible control schemes.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ControlSelector extends GuiComponent {

    private ArrayList<ControlOption> options = new ArrayList<ControlOption>();
    private ControlSelectionIndicator selectionIndicator = new ControlSelectionIndicator();
    private String currentSelection = "";
    private BitmapFont selectionFont = FontUtils.UPHEAVAL_CURRENTSELECTION_MEDIUM;
    private GlyphLayout selectionLayout = new GlyphLayout(selectionFont, currentSelection);
    
    private float selectionX, selectionY;
    private static final int MARGIN = 70;
    private static final int STACK_MARGIN = 5;
    
    /** Default instantiable constructor */
    public ControlSelector() {
	super(0, 0);
	
	buildOptions();
    }

    @Override
    public void update(float delta) {
	
	/* Update all control options */
	for (ControlOption o : options) {
	    o.update(delta);
	}
	
	/* Set text position */
	selectionX = MainCamera.getInstance().getCenterX() - (selectionLayout.width / 2);
	selectionY = MainCamera.getInstance().getHeight() - MARGIN + selectionLayout.height + 5;
	
	selectionIndicator.update(delta);
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	/* Render all control options */
	for (ControlOption o : options) {
	    o.render(batch);
	}
	
	selectionIndicator.render(batch);
	
	/* Render selection name */
	selectionFont.setUseIntegerPositions(false);
	selectionFont.draw(batch, currentSelection, selectionX, selectionY);
    }
    
    /** Sets up control options */
    private void buildOptions() {
	/* Create all buttons */
	options.add(new JoystickControlOption(this));
	options.add(new ConsoleControlOption(this));
	options.add(new TiltControlOption(this));
	options.add(new ArrowsControlOption(this));
	
	/* Position all buttons from top to bottom */
	int buttonsAdded = 0;
	for (ControlOption o : options) {
	    o.setX(MainCamera.getInstance().getCenterX() - (o.getWidth() / 2));
	    o.setY(MainCamera.getInstance().getHeight() - MARGIN - o.getHeight()
		   - ((o.getHeight() + STACK_MARGIN) * buttonsAdded));
	    buttonsAdded++;
	    
	    /* Add button to guiContainer */
	    GameManager.getInstance().guiContainer.addToGui(o);
	}
	
	/* Set an initial selection */
	switch (CurrentSettings.getInstance().controls) {
	case JOYSTICK:
	    setSelection(options.get(0));
	    break;
	case CONSOLE:
	    setSelection(options.get(1));
	    break;
	case TILT:
	    setSelection(options.get(2));
	    break;
	case HORIZONTALARROWS:
	    setSelection(options.get(3));
	    break;
	}

    }

    @Override
    public void triggered() {}
    
    public void setSelection(ControlOption newSelection) { 
	this.selectionIndicator.setSelection(newSelection);
	currentSelection = newSelection.getName();
	selectionLayout.setText(selectionFont, currentSelection);
	CurrentSettings.getInstance().controls = newSelection.type;
    }

}

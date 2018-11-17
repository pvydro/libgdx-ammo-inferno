package com.flizzet.controlsettingsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.settings.ControlType;

/**
 * Abstract control selection component.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
abstract class ControlOption extends GuiComponent {
    
    protected ControlType type;
    protected Texture image;
    protected ControlSelector selector;
    protected String name = "";
    
    /** Default instantiable constructor */
    public ControlOption(ControlSelector selector, ControlType type, String name) {
	super(0, 0); 
	
	this.type = type;
	this.selector = selector;
	this.image = type.getMenuImage();
	this.name = name;
	this.adjustBoundsToImage();
    }
    
    @Override
    public void update(float delta) {
	Vector3 mousePos = MainCamera.getInstance().getMousePos();

	if (Gdx.input.isTouched()) {
	    if (this.getBounds().contains(mousePos.x, mousePos.y)) {
		selector.setSelection(this);
	    }
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	batch.draw(image, bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    @Override
    public void triggered() {}
    
    /** Adjusts dimensions to image width and height */
    private void adjustBoundsToImage() {
	this.setWidth(image.getWidth() * 2);
	this.setHeight(image.getHeight() * 2);
    }
    
    public String getName()		{ return this.name; }
    public ControlType getType()	{ return this.type; }

}

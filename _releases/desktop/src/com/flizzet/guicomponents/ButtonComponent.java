package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.FontUtils;

/**
 * A basic button with standard, hovered, and pushed images.
 * </br>
 * Implement triggered ActionListener to add events on button push.
 * 
 * @author Lucas Cirillo (Jan 17, 2017)
 * @version 1.0
 * @see also GuiComponent
 */
public class ButtonComponent extends GuiComponent {
    
    protected Texture image;		// The default image.
    protected Texture imageHovered;	// The hovered image.
    protected Texture imagePushed;	// The pushed image.
    public enum BtnState { DEFAULT, HOVERED, PUSHED };
    protected BtnState state = BtnState.DEFAULT;
    
    /* Text */
    protected BitmapFont subtextFont = FontUtils.UPHEAVAL_90;
    protected String subtext = "";
    protected GlyphLayout subtextLayout = new GlyphLayout(subtextFont, subtext);
    protected float subtextX, subtextY;
    
    /* Misc */
    private float initialX, initialY;
    protected boolean justPushed = false;
    protected boolean pushed = false;
    
    public ButtonComponent(float x, float y) {
	super(x, y);
	
	this.subtextFont.setUseIntegerPositions(false);
    }

    @Override
    public void update(float delta) {
	
	state = BtnState.DEFAULT;
	
	if (Gdx.input.isTouched() && !pushed) {
	    pushed = true;
	    initialX = MainCamera.getInstance().getMousePos().x;
	    initialY = MainCamera.getInstance().getMousePos().y;
	}
	
	if (!Gdx.input.isTouched()) {
	    pushed = false;
	}
	
	if(bounds.contains(MainCamera.getInstance().getMousePos().x, MainCamera.getInstance().getMousePos().y)
	&& bounds.contains(initialX, initialY)) {	// If the mouse is within the button
	    state = BtnState.HOVERED;
	    
	    if(pushed) {				// If the mouse is down
		state = BtnState.PUSHED;		// Set to pushed state
		justPushed = true;
	    } else {
		if(justPushed) {
		    triggered();
		    Sounds.play(Sounds.getInstance().clickSound, 1.2f);
		    justPushed = false;
		}
	    }
	}
	else {
	    state = BtnState.DEFAULT;			// Otherwise set the button to its default state
	    justPushed = false;
	}
	
	/* Setting text position */
	subtextLayout.setText(subtextFont, subtext);
	subtextX = this.getCenterX() - (subtextLayout.width / 2);
	if (!subtext.equals("")) {
	    subtextY = this.getY() + subtextLayout.height;
	} else {
	    subtextY = bounds.getY() - 1;
	}

    }

    @Override
    public void render(SpriteBatch batch) {
	
	float shakeXOffset = (MainCamera.getInstance().getCamera().position.x - MainCamera.getInstance().getTargetX());
	float shakeYOffset = (MainCamera.getInstance().getCamera().position.y - MainCamera.getInstance().getTargetY());
	
	switch (state) {
	case DEFAULT:
	    batch.draw(image, bounds.x + shakeXOffset, subtextY + 1 + shakeYOffset, bounds.width, bounds.height);
	    break;
	case HOVERED:
	    batch.draw(imageHovered, bounds.x + shakeXOffset, subtextY + 1 + shakeYOffset, bounds.width, bounds.height);
	    break;
	case PUSHED:
	    batch.draw(imagePushed, bounds.x + shakeXOffset, subtextY + 1 + shakeYOffset, bounds.width, bounds.height);
	    break;
	}
	
	if (!subtext.equals("")) {
	    subtextFont.draw(batch, subtext, subtextX, subtextY);
	}
	
    }
    
    @Override
    public void triggered() {}
    
    @Override
    public float getHeight() {
	/* If there's no subtext, return the normal height */
	if (subtext.equals("")) {
	    return bounds.getHeight();
	}
	/* Otherwise return the normal height with the subtext height */
	return bounds.getHeight() + subtextLayout.height + 2;
    }
    /** For when the height of only the component is needed, not the subtext */
    public float getHeightNoText() {
	return bounds.getHeight();
    }
    
    
    
    public void adjustBoundsToImage() {
	bounds.width = image.getWidth();
	bounds.height = image.getHeight();
    }
    public void setImage(Texture newImage) {
	this.image = newImage;
	adjustBoundsToImage();
    }
    public void setImageHovered(Texture newImageHovered) 	{ this.imageHovered = newImageHovered; }
    public void setImagePushed(Texture newImagePushed) 		{ this.imagePushed = newImagePushed; }
    
    public boolean isPushed() 					{ return this.justPushed; }
    public float getTextHeight()				{ return this.subtextLayout.height; }
    public float getTextWidth()					{ return this.subtextLayout.width; }
    public float getTextX()					{ return this.subtextX; }
    public float getTextY()					{ return this.subtextY; }
    
}

package com.flizzet.headshop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.headsystem.Head;

/**
 * Button for selecting a head to purchase.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class HeadIcon extends ButtonComponent {
    
    private Head head;
    private IconView view;
    private static final int ICON_MULTIPLIER = 2;
    private float alpha = 0;

    /** Default instantiable constructor */
    public HeadIcon(Head head, IconView view) {
	super(0, 0);
	
	this.head = head;
	this.view = view;
	
	this.setWidth(head.getIconImage().getWidth() * ICON_MULTIPLIER);
	this.setHeight(head.getIconImage().getHeight() * ICON_MULTIPLIER);
    }

    @Override
    public void update(float delta) {
	
	super.update(delta);
	
	if (head.isBought()) {
	    alpha += (0 - alpha) / 5;
	} else {
	    alpha = 0.5f;
	}
	
    }
    
    @Override
    public void render(SpriteBatch batch) {
	
	Color tmp = batch.getColor();
	batch.setColor(tmp.r - alpha, tmp.g - alpha, tmp.b - alpha, 1);
	batch.draw(head.getIconImage(), bounds.x, bounds.y, bounds.width, bounds.height);
	batch.setColor(tmp);
	
	
    }

    @Override
    public void triggered() {
	view.openPrompt(this);
    }
    
    public Texture getImage()	{ return head.getIconImage(); }
    public Head getHead()	{ return head; }
    
}

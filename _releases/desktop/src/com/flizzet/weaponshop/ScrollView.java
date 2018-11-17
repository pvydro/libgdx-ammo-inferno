package com.flizzet.weaponshop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.lighting.WeaponViewLight;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;
import com.flizzet.weapons.Weapon;
import com.flizzet.weaponsystem.Weapons;

/**
 * Scroll view for displaying weapons.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ScrollView extends GuiComponent {
    
    private ArrayList<Weapon> weapons = Weapons.getInstance().getWeapons();
    private ArrayList<WeaponView> views = new ArrayList<WeaponView>();
    private WeaponViewLight light;
    private WeaponStand stand = new WeaponStand(this);
    
    private float xChange = 0;
    private float initialMouseX = 0;
    private float mouseChange = 0;
    private boolean pushed = false;
    private final int MARGIN = 100;
    
    private int currentSelection = 0;

    /** Default instantiable constructor */
    public ScrollView() {
	super(0, 0);
	buildScrollWindow();
	
	if (CurrentSettings.getInstance().lighting) {
	    light = new WeaponViewLight(this);
	}
    }
    
    /** Builds the scroll component */
    private void buildScrollWindow() {
	/* Put all images into an WeaponViews */
	for (Weapon w : weapons) {
	    views.add(new WeaponView(w, this));
	}
	
	currentSelection = Weapons.getInstance().getWeapons().indexOf(Weapons.getInstance().getEquippedWeapon());
     }
    
    @Override
    public void update(float delta) {
	
	/* Locking into place */
	if (!Gdx.input.isTouched()) {
	    if (pushed) {
		if (xChange < -0.1f) {
		    if (currentSelection < views.size() - 1) {
			currentSelection++;
			Sounds.play(Sounds.getInstance().swoosh1Sound, 1.0f);
		    }
		} else if (xChange > 0.1f) {
		    if (currentSelection > 0) {
			currentSelection--;
			Sounds.play(Sounds.getInstance().swoosh2Sound, 1.0f);
		    }
		}
	    }
	}
	if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
	    if (currentSelection > 0) {
		currentSelection--;
	    }
	}
	if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D)) {
	    if (currentSelection < views.size() - 1) {
		currentSelection++;
	    }
	}

	/* Scrolling */
	/* Handling a scroll */
	initialMouseX += (MainCamera.getInstance().getMousePos().x - initialMouseX) / 5;
	if (Gdx.input.isTouched()) {
	    if (!pushed) {
		pushed = true;
		initialMouseX = MainCamera.getInstance().getMousePos().x;
	    }

	    mouseChange = initialMouseX - MainCamera.getInstance().getMousePos().x;
	} else {
	    pushed = false;
	}
	mouseChange += (0 - mouseChange) / 7;
	xChange -= mouseChange / 20;
	xChange += (0 - xChange) / 2;

	if (pushed) {
	    bounds.x += xChange;
	} else {
	    bounds.x += ((-views.get(currentSelection).getX() - (views.get(currentSelection).getImageWidth() / 2) + MainCamera.getInstance().getCenterX() - bounds.x) / 5);
	}
	
	/* Update all WeaponViews */
	for (WeaponView w : views) {
	    w.update(delta);
	    if (views.indexOf(w) == currentSelection) {
		w.setSelected(true);
	    } else {
		w.setSelected(false);
	    }
	}
	
	/* Position stand */
	stand.update(delta);
	
	/* Set positions for all WeaponViews */
	int columns = 0;
	for (WeaponView w : views) {
	    w.setX(MainCamera.getInstance().getCenterX() - (w.getImageWidth() / 2) + (MARGIN * columns));
	    w.setY(stand.getY() + stand.getHeight() + 5);
	    columns++;
	}
	
	/* Position light */
	light.update(delta);
	light.setX(MainCamera.getInstance().getCenterX() - (light.getWidth() / 2));
	light.setY(MainCamera.getInstance().getCenterY() - (light.getHeight() / 2));
	
    }

    @Override
    public void render(SpriteBatch batch) {
	/* Draw all WeaponViews */
	for (WeaponView w : views) {
	    w.render(batch);
	}
	
	light.render(batch);
	stand.render(batch);
    }
    
    public int getCurrentSelection()		{ return this.currentSelection; }
    public WeaponView getCurrentView()		{ return this.views.get(currentSelection); }
    public ArrayList<WeaponView> getViews()	{ return this.views; }
    public WeaponStand getStand()		{ return this.stand; }
    
    @Override
    public void triggered() { }

}

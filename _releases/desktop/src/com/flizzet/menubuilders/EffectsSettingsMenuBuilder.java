package com.flizzet.menubuilders;

import java.util.ArrayList;

import com.flizzet.background.BackgroundComponent;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.CheckboxComponent;
import com.flizzet.guicomponents.SeparatorComponent;
import com.flizzet.guicomponents.SeparatorFillerComponent;
import com.flizzet.interfaces.Updatable;
import com.flizzet.main.GameManager;
import com.flizzet.settings.CurrentSettings;

/**
 * Menu builder for the effects settings menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EffectsSettingsMenuBuilder extends MenuBuilder implements Updatable {

    private BackgroundComponent background;
    private SeparatorComponent effectsSeparator;
    private SeparatorComponent particlesSeparator;
    private SeparatorComponent finalSeparator;
    private SeparatorFillerComponent filler;
    
    // Effects
    private CheckboxComponent checkbox_softLight;
    private CheckboxComponent checkbox_screenFlash;
    private CheckboxComponent checkbox_screenShake;
    private CheckboxComponent checkbox_bugs;
    
    // Particles
    private CheckboxComponent particleCollisionCheckbox;
    private CheckboxComponent enemyHitParticlesCheckbox;
    private CheckboxComponent enemyDeathParticlesCheckbox;
    private CheckboxComponent bloodParticlesCheckbox;
    private CheckboxComponent textParticlesCheckbox;
    private CheckboxComponent groundParticlesCheckbox;
    private CheckboxComponent landParticlesCheckbox;
    private CheckboxComponent bulletHitParticlesCheckbox;
    private CheckboxComponent lightParticlesCheckbox;
    private CheckboxComponent lavaParticlesCheckbox;
    
    private ArrayList<CheckboxComponent> effectCheckboxes = new ArrayList<CheckboxComponent>();
    private ArrayList<CheckboxComponent> particleCheckboxes = new ArrayList<CheckboxComponent>();
    private ArrayList<SeparatorComponent> separators = new ArrayList<SeparatorComponent>();
    
    public final int MARGIN = 60;
    
    /** Default instantiable constructor */
    public EffectsSettingsMenuBuilder() {}

    @Override
    public void buildMenu() {
	background = new BackgroundComponent();
	separators.add(effectsSeparator = new SeparatorComponent(2, "effects"));
	separators.add(particlesSeparator = new SeparatorComponent(2, "particles"));
	separators.add(finalSeparator = new SeparatorComponent(2));
	filler = new SeparatorFillerComponent(effectsSeparator, finalSeparator);
	
	
	/* The background must be added before this so the checkboxes draw above it */
	GameManager.getInstance().guiContainer.addToGui(background);
	GameManager.getInstance().guiContainer.addToGui(filler);
	GameManager.getInstance().guiContainer.addToGui(effectsSeparator);
	GameManager.getInstance().guiContainer.addToGui(particlesSeparator);
	GameManager.getInstance().guiContainer.addToGui(finalSeparator);
	
//	  _____ _____ _____ _____ _____ _____ _____ 
//	 |   __|   __|   __|   __|     |_   _|   __|
//	 |   __|   __|   __|   __|   --| | | |__   |
//	 |_____|__|  |__|  |_____|_____| |_| |_____|
	
	/* Checkboxes */
	checkbox_softLight = new CheckboxComponent("soft lights", 
		"use smooth lighting masks with opengl blending.\n\nminimal performance difference.", 
		CurrentSettings.getInstance().softLight) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().softLight = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().softLight = false;
		    break;
		}
	    }
	};
	checkbox_screenFlash = new CheckboxComponent("screen flash", 
		"display a screen flash effect when triggered.",
		CurrentSettings.getInstance().screenFlash) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().screenFlash = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().screenFlash = false;
		    break;
		}
	    }
	};
	checkbox_screenShake = new CheckboxComponent("screen shake", 
		"shake the camera with recoil, hits, etc.",
		CurrentSettings.getInstance().screenShake) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().screenShake = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().screenShake = false;
		    break;
		}
	    }
	};
	checkbox_bugs = new CheckboxComponent("bugs", 
		"display player-following bug particles.", 
		CurrentSettings.getInstance().bugs) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().bugs = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().bugs = false;
		    break;
		}
	    }
	};
	
	/* Add checkbox to ArrayList 
	 * Effects */
	effectCheckboxes.add(checkbox_softLight);
	effectCheckboxes.add(checkbox_screenFlash);
	effectCheckboxes.add(checkbox_screenShake);
	effectCheckboxes.add(checkbox_bugs);

	// Separate
	effectsSeparator.setY(MainCamera.getInstance().getHeight() - MARGIN);
	
	int totalEffectsCheckboxes = 0;
	for (CheckboxComponent c : effectCheckboxes) {
	    totalEffectsCheckboxes++;

	    c.setX(MainCamera.getInstance().getWidth() - 10 - c.getWidth());
	    c.setY(effectsSeparator.getY() - 3 - (c.getHeight() + 5) * totalEffectsCheckboxes);

	    GameManager.getInstance().guiContainer.addToGui(c);
	}
	
	// Separate
	particlesSeparator.setY(effectCheckboxes.get(effectCheckboxes.size() - 1).getY() - 13);
	
	
//	  _____ _____ _____ _____ _____ _____ __    _____ _____ 
//	 |  _  |  _  | __  |_   _|     |     |  |  |   __|   __|
//	 |   __|     |    -| | | |-   -|   --|  |__|   __|__   |
//	 |__|  |__|__|__|__| |_| |_____|_____|_____|_____|_____|
	
	
	particleCollisionCheckbox = new CheckboxComponent("particle collision",
		"collide particles with the ground, walls, and bullets.\n\n"
		+ "heavy performance difference.",
		CurrentSettings.getInstance().collideParticles) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().collideParticles = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().collideParticles = false;
		    break;
		}
	    }
	};
	enemyHitParticlesCheckbox = new CheckboxComponent("hit particles",
		"emit particles when a bullet collides with an enemy.\n\n"
		+ "minimal performance difference.",
		CurrentSettings.getInstance().enemyHits) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().enemyHits = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().enemyHits = false;
		    break;
		}
	    }
	};
	enemyDeathParticlesCheckbox = new CheckboxComponent("death particles",
		"emit particles when an enemy dies.\n\n"
		+ "minimal performance difference.",
		CurrentSettings.getInstance().enemyDeath) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().enemyDeath = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().enemyDeath = false;
		    break;
		}
	    }
	};
	bloodParticlesCheckbox = new CheckboxComponent("blood", 
		"emit blood particles when hitting enemies and when the player is hit.\n\n"
		+ "heavy performance difference.",
		CurrentSettings.getInstance().blood) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().blood = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().blood = false;
		    break;
		}
	    }
	};
	textParticlesCheckbox = new CheckboxComponent("text", 
		"emit text particles to display hits and currency collection.\n\n"
		+ "moderate performance difference.",
		CurrentSettings.getInstance().textParticles) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().textParticles = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().textParticles = false;
		    break;
		}
	    }
	};
	groundParticlesCheckbox = new CheckboxComponent("walking", 
		"emit particles from the ground when walking on it.\n\n"
		+ "heavy performance difference.",
		CurrentSettings.getInstance().groundParticles) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().groundParticles = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().groundParticles = false;
		    break;
		}
	    }
	};
	landParticlesCheckbox = new CheckboxComponent("platform landing",
		"emit particles when landing on a platform.\n\n"
		+ "minimal performance difference.",
		CurrentSettings.getInstance().playerLand) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().playerLand = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().playerLand = false;
		    break;
		}
	    }
	};
	bulletHitParticlesCheckbox = new CheckboxComponent("bullet hit",
		"emit particles when the bullet collides with the ground or the wall.\n\n"
		+ "moderate performance difference.", 
		CurrentSettings.getInstance().bulletHits) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().bulletHits = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().bulletHits = false;
		    break;
		}
	    }
	};
	lightParticlesCheckbox = new CheckboxComponent("light", 
		"emit particles from platform lamps.\n\n"
		+ "minimal performance difference.", 
		CurrentSettings.getInstance().platformLightParticles) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().platformLightParticles = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().platformLightParticles = false;
		    break;
		}
	    }
	};
	lavaParticlesCheckbox = new CheckboxComponent("lava", 
		"emit light particles from lava.\n\n"
		+ "moderate performance difference.",
		CurrentSettings.getInstance().lavaParticles) {
	    @Override
	    public void triggered() {
		switch (state) {
		case ON:
		    CurrentSettings.getInstance().lavaParticles = true;
		    break;
		case OFF:
		    CurrentSettings.getInstance().lavaParticles = false;
		    break;
		}
	    }
	};
	
	/* Add all new checkboxes to ArrayList */
	particleCheckboxes.add(particleCollisionCheckbox);
	particleCheckboxes.add(bloodParticlesCheckbox);
	particleCheckboxes.add(textParticlesCheckbox);
	particleCheckboxes.add(groundParticlesCheckbox);
	particleCheckboxes.add(landParticlesCheckbox);
	particleCheckboxes.add(bulletHitParticlesCheckbox);
	particleCheckboxes.add(enemyHitParticlesCheckbox);
	particleCheckboxes.add(enemyDeathParticlesCheckbox);
	particleCheckboxes.add(lightParticlesCheckbox);
	particleCheckboxes.add(lavaParticlesCheckbox);
	
	int totalCheckboxes = 0;
	for (CheckboxComponent c : particleCheckboxes) {
	    totalCheckboxes++;
	    
	    c.setX(MainCamera.getInstance().getWidth() - 20 - c.getWidth());
	    c.setY(MainCamera.getInstance().getHeight() - MARGIN
		    - (c.getHeight() + 5) * totalCheckboxes);
	    
	    GameManager.getInstance().guiContainer.addToGui(c);
	    
	}

	// Separate
	finalSeparator.setY(particleCheckboxes.get(particleCheckboxes.size() - 1).getY() - 10);
	
    }

    @Override
    public void update(float delta) {
	// Separate
	effectsSeparator.setY(MainCamera.getInstance().getHeight() - MARGIN);
	
	// Effects
	int totalEffectCheckboxes = 0;
	for (CheckboxComponent c : effectCheckboxes) {
	    totalEffectCheckboxes++;
	    
	    c.setX(MainCamera.getInstance().getWidth() - 20 - c.getWidth());
	    c.setY(effectsSeparator.getY() - 3
		    - (c.getHeight() + 5) * totalEffectCheckboxes);
	}
	
	// Separate
	particlesSeparator.setY(effectCheckboxes.get(effectCheckboxes.size() - 1).getY() - 13);
	
	// Particles
	int totalParticleCheckboxes = 0;
	for (CheckboxComponent c : particleCheckboxes) {
	    totalParticleCheckboxes++;
	    
	    c.setX(MainCamera.getInstance().getWidth() - 20 - c.getWidth());
	    c.setY(particlesSeparator.getY() - 3
		    - (c.getHeight() + 5) * totalParticleCheckboxes);
	}
	
	// Separate
	finalSeparator.setY(particleCheckboxes.get(particleCheckboxes.size() - 1).getY() - 13);
    }
    
    public ArrayList<CheckboxComponent> getEffectCheckboxes()	{ return this.effectCheckboxes; }
    public ArrayList<CheckboxComponent> getParticleCheckboxes()	{ return this.particleCheckboxes; }
    public ArrayList<SeparatorComponent> getSeparators()	{ return this.separators; }
    public SeparatorFillerComponent getFiller()			{ return this.filler; }
    
}

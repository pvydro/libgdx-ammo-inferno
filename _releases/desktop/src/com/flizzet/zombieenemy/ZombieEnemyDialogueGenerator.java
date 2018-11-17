package com.flizzet.zombieenemy;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;
import com.flizzet.utils.FontUtils;

/**
 * Gives the zombie dialogue to say.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ZombieEnemyDialogueGenerator implements Updatable, Renderable {

    private ZombieEnemy enemy;
    private Random random = new Random();
    private String message = "";
    private BitmapFont font = FontUtils.UPHEAVAL_ZOMBIEDIALOGUE_SMALL;
    private GlyphLayout layout;
    
    private boolean enabled = false;
    private boolean messageChosen = false;
    private int cooldown = 225;
    
    /** Default instantiable constructor */
    public ZombieEnemyDialogueGenerator(ZombieEnemy enemy) {
	this.enemy = enemy;
	
	int chance = random.nextInt(20);
	
	if (chance == 2) {
	    enabled = true;
	} else {
	    enabled = false;
	}
    }

    @Override
    public void update(float delta) {
	if (enabled) {
	    cooldown --;
	    if (cooldown <= 0) {
		speak();
		enabled = false;
	    }
	}
	
    }
    
    @Override
    public void render(SpriteBatch batch) {
	if (messageChosen) {
	    font.setUseIntegerPositions(false);
	    font.draw(batch, message, enemy.getCenterX() - (layout.width / 2), enemy.getY() + enemy.getHeight() + 30);
	}
    }

    /** Spawns a new dialogue at the zombie position */
    private void speak() {
	messageChosen = true;
	FileHandle file = Gdx.files.internal("enemies/zombie/zombie.dia");	// Get the dialogue file
	
	String text = file.readString();					// Read it to a string
	String[] choices = text.split(" : ");					// Separate it (see file)
	int choice = random.nextInt(choices.length);				// Choose a random option from the file
	message = choices[choice];
	layout = new GlyphLayout(font, message);
    }
    
}

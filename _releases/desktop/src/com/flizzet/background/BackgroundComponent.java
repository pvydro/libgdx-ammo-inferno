package com.flizzet.background;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.ShineOverlay;
import com.flizzet.main.GameManager;
import com.flizzet.particles.BackgroundParticle;
import com.flizzet.particles.Particle;
import com.flizzet.particles.ScreenDarkener;

/**
 * Renders a background for the UpgradeShop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class BackgroundComponent extends GuiComponent {
    
    private Texture gradientOverlay;
    private Texture zombieFiller;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private ShineOverlay shineOverlay = new ShineOverlay();
    private Color color = Color.LIGHT_GRAY;
    private Random random = new Random();
    private ScreenDarkener screenDarkener = new ScreenDarkener(0.5f);
    
    private Queue<Particle> toBeAdded = new LinkedList<Particle>();
    private Queue<Particle> toBeRemoved = new LinkedList<Particle>();
    private ArrayList<Particle> particles = new ArrayList<Particle>();
    private ArrayList<BackgroundHead> bgHeads = new ArrayList<BackgroundHead>();
    
    private int particleCooldown = 10;
    private float screenFlashAmount = 0;
    private int screenFlashCooldown = 10;
    
    /** Default instantiable constructor */
    public BackgroundComponent() {
	super(0, 0);
	
	this.gradientOverlay = GameManager.getInstance().assetManager.get("gui/constant/menuBgGradient.png");
	this.zombieFiller = GameManager.getInstance().assetManager.get("gui/constant/zombieFiller.png");
	this.buildHeads();
    }

    @Override
    public void update(float delta) {
	
	/* Create particles */
	particleCooldown--;
	if (particleCooldown <= 0) {
	    newParticle();
	}
	
	/* Add and remove particles */
	particles.addAll(toBeAdded);
	toBeAdded.removeAll(toBeAdded);
	particles.removeAll(toBeRemoved);
	toBeRemoved.removeAll(toBeRemoved);
	
	/* Update all background particles */
	for (Particle p : particles) {
	    p.update(delta);
	}
	
	/* Update all background heads */
	for (BackgroundHead h : bgHeads) {
	    h.update(delta);
	}
	
	/* Flash the screen, light flicker effect */
	screenFlashCooldown--;
	if (screenFlashCooldown <= 0) {
	    screenFlashCooldown = random.nextInt(20);
	    screenFlashAmount = random.nextBoolean() ? 0.9f : 0f;
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	batch.end();
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(color);
	shapeRenderer.rect(0, 0, MainCamera.getInstance().getWidth(), MainCamera.getInstance().getHeight());
	shapeRenderer.end();
	batch.begin();
	
	/* Render all background particles */
	for (Particle p : particles) {
	    p.render(batch, shapeRenderer);
	}
	
	/* Draw zombie filler to prevent empty space */
	batch.draw(zombieFiller, -100, 0, zombieFiller.getWidth(), 160);
	/* Reverse-iterate through heads to draw from front->back */
	for (int i = bgHeads.size() - 1; i >= 0; i--) {
	    bgHeads.get(i).render(batch);
	}
	
	/* Drawing overlays */
	screenDarkener.render(batch);
	batch.draw(gradientOverlay, -50, -100);
	shineOverlay.render(batch);
	
	/* Drawing letterboxes */
	batch.end();
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(Color.BLACK);
	shapeRenderer.rect(-200, -200, 200, MainCamera.getInstance().getHeight() + 400);
	shapeRenderer.rect(MainCamera.getInstance().getWidth(), -200, 200, MainCamera.getInstance().getHeight() + 400);
	shapeRenderer.end();
	batch.begin();
	
	
    }
    
    /** Creates the background heads */
    private void buildHeads() {
	int totalHeads = 150;
	
	int currentRow = 3;
	float currentX = 150;
	float currentY = 0;
	float alpha = 0;
	int side = 0;	// 0 = Left; 1 = Right
	for (int i = 0; i < totalHeads; i++) {
	    System.out.println(MainCamera.getInstance().getCenterX());
	    /* Create head */
	    BackgroundHead newHead = new BackgroundHead(currentRow + 1, alpha, this);
	    /* Set head position */
	    newHead.setX((side == 0 ? currentX : -currentX) + 100);
	    newHead.setY(currentY + random.nextInt(8));
	    /* Flip side */
	    side = side == 0 ? 1 : 0;
	    /* Increment currentX */
	    currentX -= (newHead.getWidth() / 2);
	    
	    /* Reached edge */
	    if (currentX <= 0) {
		currentX = 200;
		currentY += newHead.getHeight() * (currentRow);
		currentRow--;
		alpha += 0.2f;
		if (currentRow < 0) currentRow = 0;
	    }
	    
	    bgHeads.add(newHead);
	}
    }

    @Override
    public void triggered() {}
    
    /** Adds a new background particle to the background */
    public void addToParticles(Particle newParticle) {
	toBeAdded.add(newParticle);
    }
    
    /** Removes a background particle from the background */
    public void removeFromParticles(Particle particle) {
	toBeRemoved.add(particle);
    }
    
    /** Creates a new particle */
    private void newParticle() {
	particleCooldown = random.nextInt(15);
	
	BackgroundParticle p = new BackgroundParticle(this);
	p.setX(random.nextInt((int) (MainCamera.getInstance().getWidth() - p.getWidth()) + 200) - 100);
	this.addToParticles(p);
    }
    
    public void setColor(Color newColor)	{ this.color = newColor; }
    public float getScreenFlashAmount()		{ return this.screenFlashAmount; }
    
}

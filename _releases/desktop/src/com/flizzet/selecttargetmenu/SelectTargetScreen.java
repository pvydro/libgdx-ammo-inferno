package com.flizzet.selecttargetmenu;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.ingamegui.TargetButton;
import com.flizzet.sound.Sounds;
import com.flizzet.utils.FontUtils;

/**
 * Displays the gui elements of a Select Target screen when triggered.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SelectTargetScreen extends GuiComponent {

    private static final SelectTargetScreen INSTANCE = new SelectTargetScreen();
    
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Random random = new Random();
    private BitmapFont selectFont = FontUtils.UPHEAVAL_SELECT_LARGE;
    private String selectText = "select enemy";
    private GlyphLayout selectLayout = new GlyphLayout(selectFont, "");
    
    private boolean enabled = false;
    private float alpha = 0f;
    private float textAlpha = 0f;
    private float textX, textY;
    private Vector2 firstLinePos = new Vector2(0, 0);
    private Vector2 secondLinePos = new Vector2(0, 0);
    private float lineWidth = 3;
    private int direction = 0;		// 0 = Up; 1 = Down
    private int totalRects = 500;
    
    /** Returns an instance of the SelectTargetScreen */
    public static SelectTargetScreen getInstance() {
	return INSTANCE;
    }
    /** Default instantiable constructor */
    public SelectTargetScreen() {
	super(0, 0);
    }

    @Override
    public void update(float delta) {
	/* Change alpha */
	if (enabled) {
	    alpha += (0.5f + (random.nextFloat() / 10) - alpha) / 10;
	    /* Back button */
	    if (Gdx.input.isKeyJustPressed(Keys.BACK) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		TargetButton.getInstance().triggered();
	    }
	} else {
	    alpha += (0 - alpha) / 15;
	}
	
	/* Change line position */
	if (direction == 0) {
	    firstLinePos.y += ((MainCamera.getInstance().getHeight() - lineWidth) - firstLinePos.y) / 50;
	    if (firstLinePos.y > MainCamera.getInstance().getHeight() - lineWidth - 2) {
		direction = 1;
	    }
	} else {
	    firstLinePos.y += (0 - firstLinePos.y) / 50;
	    if (firstLinePos.y < 2) {
		direction = 0;
	    }
	}
	secondLinePos.y = firstLinePos.y;
	secondLinePos.x = firstLinePos.x + MainCamera.getInstance().getWidth();
	
	/* Change text position */
	textX = MainCamera.getInstance().getCenterX() - (selectLayout.width / 2);
	if (enabled) {
	    textY += (MainCamera.getInstance().getCenterY() - textY) / 5;
	    textAlpha += (0.7f - textAlpha) / 4;
	} else {
	    textY += (-20 - textY) / 14;
	    textAlpha += (0 - textAlpha) / 5;
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	if (alpha > 0.01f) {
	    
	    batch.end();
	    Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	    shapeRenderer.begin(ShapeType.Filled);

	    /* Draw colored overlay */
	    shapeRenderer.setColor(new Color(Color.MAROON.r, Color.MAROON.g, Color.MAROON.b, alpha / 10));
	    shapeRenderer.rect(-100, -100, MainCamera.getInstance().getWidth() + 200, MainCamera.getInstance().getHeight() + 200);
	    
	    /* Draw scan line */
	    shapeRenderer.setColor(new Color(1f, 1f, 1f, alpha));
	    shapeRenderer.rectLine(firstLinePos, secondLinePos, lineWidth);
	   
	    /* Draw shaking rects */
	    for (int i = 0; i < totalRects; i++) {
		float rectX = random.nextInt((int) MainCamera.getInstance().getWidth());
		float rectY = random.nextInt((int) MainCamera.getInstance().getHeight());
		float scale = random.nextFloat();
		
		shapeRenderer.setColor(new Color(1f, 1f, 1f, 0.2f));
		shapeRenderer.rect(rectX, rectY, scale, scale);
	    }
	    
	    shapeRenderer.end();
	    Gdx.gl.glDisable(GL20.GL_BLEND);
	    
	    batch.begin();

	    /* Draw text */
	    selectFont.setUseIntegerPositions(false);
	    selectFont.setColor(new Color(1f, 1f, 1f, textAlpha));
	    selectFont.draw(batch, selectText, textX, textY);
	}

    }

    @Override
    public void triggered() {}
    
    /** Enables all the elements of the select menu screen */
    public void enable() {
	/* Play sound */
	Sounds.play(Sounds.getInstance().selectEnterSound, 1.0f);
	
	/* Enable */
	enabled = true;
	selectLayout = new GlyphLayout(selectFont, selectText);
    }
    
    /** Disables all the elements of the select menu screen */
    public void disable() {
	/* Play sound */
	Sounds.play(Sounds.getInstance().selectExitSound, 1.3f);
	Sounds.play(Sounds.getInstance().selectExitEffectSound, 0.8f);
	
	/* Disable */
	enabled = false;
    }

}

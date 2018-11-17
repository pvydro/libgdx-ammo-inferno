package com.flizzet.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;

/**
 * Darkens background.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ScreenDarkener extends GuiComponent {
    
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float alpha;

    /** Default instantiable constructor */
    public ScreenDarkener(float alpha) {
	super(0, 0);
	this.alpha = alpha;
    }

    @Override
    public void update(float delta) { /* TODO EASE IN? */ }

    @Override
    public void render(SpriteBatch batch) {
	batch.end();
	
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	
	shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(new Color(0f, 0f, 0f, alpha));
	shapeRenderer.rect(-100, -100, MainCamera.getInstance().getWidth() + 200, MainCamera.getInstance().getHeight() + 200);
	shapeRenderer.end();
	
	Gdx.gl.glDisable(GL20.GL_BLEND);
	
	batch.begin();
	
    }

    @Override
    public void triggered() {
	
    }

}

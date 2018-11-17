package com.flizzet.guicomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.flizzet.camera.MainCamera;

/**
 * Fills space between two separators with color.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SeparatorFillerComponent extends GuiComponent {

	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private SeparatorComponent separator1;
	private SeparatorComponent separator2;
	private Color color;

	/** Default instantiable constructor */
	public SeparatorFillerComponent(SeparatorComponent separator1,
			SeparatorComponent separator2) {
		super(0, 0);

		this.separator1 = separator1;
		this.separator2 = separator2;
		this.color = new Color(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b,
				0.4f);
	}

	@Override
	public void update(float delta) {
		bounds.x = 13;
		bounds.y = separator2.getY();
		bounds.width = MainCamera.getInstance().getWidth() - 26;
		bounds.height = separator1.getY() - separator2.getY();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.end();

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setColor(color);
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		batch.begin();
	}

	@Override
	public void triggered() {
	}

}

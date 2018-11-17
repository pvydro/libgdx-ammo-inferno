package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.camera.MainCamera;
import com.flizzet.utils.FontUtils;

/**
 * Draws a line to separate categories.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SeparatorComponent extends GuiComponent {

	private Color color = Color.WHITE;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Vector2 point1 = new Vector2(0, 0);
	private Vector2 point2 = new Vector2(0, 0);
	private String subtext = "";
	private BitmapFont subFont = FontUtils.UPHEAVAL_75;
	private GlyphLayout subLayout = new GlyphLayout(subFont, subtext);

	private float subX, subY;
	private float width = 3;

	/** Default instantiable constructors */
	public SeparatorComponent(float width, String subtext) {
		super(0, 0);
		this.width = width;
		this.subtext = subtext;
		this.subLayout.setText(subFont, subtext);
		this.subFont.setUseIntegerPositions(false);
	}
	public SeparatorComponent(float width) {
		super(0, 0);
		this.width = width;
	}

	@Override
	public void update(float delta) {
		/* Position points */
		point1.x = 10;
		point1.y = this.getY();
		point2.x = MainCamera.getInstance().getWidth() - 10;
		point2.y = bounds.y;

		/* Position text */
		subX = point2.x - subLayout.width - 6;
		subY = bounds.y + subLayout.height + 3;
	}

	@Override
	public void render(SpriteBatch batch) {

		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(color);
		shapeRenderer.rectLine(point1, point2, width);
		shapeRenderer.end();
		batch.begin();

		subFont.draw(batch, subtext, subX, subY);
	}

	@Override
	public void triggered() {
	}

	public void setColor(Color newColor) {
		this.color = newColor;
	}

}

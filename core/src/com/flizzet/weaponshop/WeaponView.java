package com.flizzet.weaponshop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.utils.FontUtils;
import com.flizzet.weapons.Weapon;

/**
 * Displays a weapon icon.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class WeaponView extends GuiComponent {

	private ScrollView view;
	private Texture image;
	private Texture imageGold;
	private String name = "";
	private String price = "";
	private String totalKills = "0/1000";
	private BitmapFont nameFont = FontUtils.UPHEAVAL_WEAPONSELECT_LARGE;
	private BitmapFont priceFont = FontUtils.UPHEAVAL_WEAPONSELECT_MEDIUM;
	private GlyphLayout nameLayout;
	private GlyphLayout priceLayout;
	private GlyphLayout totalKillsLayout;
	private Weapon weapon;
	private GoldButton goldButton;

	private float textAlpha = 0f;
	private float lightAlpha = 0f;
	private float alpha = 0.75f;
	private float imageWidth, imageHeight;
	private float scale = 2;
	private float yChange = 0;
	private float nameX, nameY;
	private float priceX, priceY;
	private float totalKillsX, totalKillsY;
	private int state = 0;
	private boolean selected = false;

	public WeaponView(Weapon weapon, ScrollView view) {
		super(0, 0);

		this.weapon = weapon;
		this.view = view;
		this.image = weapon.getImageNoHand();
		this.imageGold = weapon.getImageGoldNoHand();

		this.imageWidth = image.getWidth() * scale;
		this.imageHeight = image.getHeight() * scale;
		this.goldButton = new GoldButton(this);

		triggered();

	}

	@Override
	public void update(float delta) {

		/* Selected/Deselected */
		if (selected) {
			lightAlpha += (1 - lightAlpha) / 2;
			alpha += (0 - alpha) / 2;
			scale += (4 - scale) / 2;
		} else {
			lightAlpha += (0 - lightAlpha) / 2;
			alpha += (0.75f - alpha) / 2;
			scale += (2 - scale) / 2;
		}

		/* Bouncing up and down */
		if (selected) {
			if (state == 0) {
				yChange += (3 - yChange) / 5;
			} else if (state == 1) {
				yChange += (-3 - yChange) / 5;
			}

			if (yChange > 2.9f) {
				state = 1;
			} else if (yChange < -2.9f) {
				state = 0;
			}
		} else {
			yChange = 0;
		}

		/* Text positioning */
		/* Name */
		nameX = MainCamera.getInstance().getCenterX() - (nameLayout.width / 2);
		if (selected) {
			if (textAlpha < 1)
				textAlpha += (0.05f);
			nameY += ((300 - (nameLayout.height / 2)) - nameY) / 4;
		} else {
			textAlpha = 0;
			nameY += ((-nameLayout.height) - nameY) / 2;
		}
		/* Price */
		priceX = MainCamera.getInstance().getCenterX()
				- (priceLayout.width / 2);
		priceY = nameY - nameLayout.height - 2;
		/* Total kills */
		totalKillsX = MainCamera.getInstance().getCenterX()
				- (totalKillsLayout.width / 2);
		totalKillsY = priceY - priceLayout.height - 2;

		/* Scale image */
		imageWidth = image.getWidth() * scale;
		imageHeight = image.getHeight() * scale;

		/* Gold button positioning based on whether or not it's possible */
		goldButton.setX(MainCamera.getInstance().getCenterX()
				- (goldButton.getWidth() / 2));
		if (weapon.getTotalKills() >= weapon.getKillsToGold()) {
			goldButton.setY(totalKillsY - priceLayout.height
					- goldButton.getHeight() - 12);
			goldButton.update(delta);
		} else {
			goldButton.setY(-500);
		}

		/* Darkening when not purchased */
		if (!weapon.isBought()) {
			alpha = 1;
		}

	}

	@Override
	public void render(SpriteBatch batch) {
		Color tmp = batch.getColor();
		batch.setColor(
				new Color(tmp.r - alpha, tmp.g - alpha, tmp.b - alpha, 1.0f));

		if (weapon.isGold()) {
			batch.draw(imageGold, view.getX() + bounds.x,
					view.getY() + bounds.y + yChange, imageWidth, imageHeight);
		} else {
			batch.draw(image, view.getX() + bounds.x,
					view.getY() + bounds.y + yChange, imageWidth, imageHeight);
		}
		batch.setColor(tmp);

		Color fontColor = new Color(1f, 1f, 1f, textAlpha);
		nameFont.setUseIntegerPositions(false);
		priceFont.setUseIntegerPositions(false);
		nameFont.setColor(fontColor);
		nameFont.draw(batch, name, nameX, nameY + yChange);
		priceFont.setColor(fontColor);
		priceFont.draw(batch, price, priceX, priceY + yChange);
		priceFont.draw(batch, totalKills, totalKillsX, totalKillsY + yChange);

		batch.setColor(fontColor);
		if (weapon.getTotalKills() >= weapon.getKillsToGold()) {
			goldButton.render(batch);
		}
		batch.setColor(tmp);
	}

	@Override
	public void triggered() {
		if (weapon.isBought()) {
			this.name = weapon.getName();
			this.price = "bought";
			this.totalKills = String.valueOf(weapon.getTotalKills()) + "/"
					+ String.valueOf(weapon.getKillsToGold());
		} else {
			this.name = "?";
			this.price = "$" + String.valueOf(weapon.getPrice());
			this.totalKills = "?";
		}

		this.nameLayout = new GlyphLayout(nameFont, name);
		this.priceLayout = new GlyphLayout(priceFont, price);
		this.totalKillsLayout = new GlyphLayout(priceFont, totalKills);
	}

	public float getImageWidth() {
		return this.imageWidth;
	}
	public float getImageHeight() {
		return this.imageHeight;
	}
	public void setSelected(boolean isSelected) {
		this.selected = isSelected;
	}
	public Weapon getWeapon() {
		return this.weapon;
	}

}

package com.flizzet.headsystem;

import com.badlogic.gdx.graphics.Texture;

/**
 * Abstract head class for heads to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Head {

	protected boolean bought = false;
	protected boolean equipped = false;
	protected Texture image;
	protected Texture iconImage;
	protected String name;
	protected int price = 100;

	/** Default instantiable constructor */
	public Head(Texture image, String name) {
		this.image = image;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isBought() {
		return this.bought;
	}
	public boolean isEquipped() {
		return this.equipped;
	}
	public int getPrice() {
		return this.price;
	}
	public Texture getImage() {
		return this.image;
	}
	public Texture getIconImage() {
		return this.iconImage;
	}
	public String getName() {
		return this.name;
	}

	public void setBought(boolean isBought) {
		this.bought = isBought;
	}
	public void setEquipped(boolean isEquipped) {
		this.equipped = isEquipped;
	}

}

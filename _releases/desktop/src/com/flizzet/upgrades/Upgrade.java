package com.flizzet.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.flizzet.utils.StringUtils;

/**
 * Abstract Upgrade class for concrete classes to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public abstract class Upgrade {
    
    protected boolean bought = false;
    protected boolean equipped = false;
    protected Texture icon;
    protected String name;
    protected String description;
    protected int price = 0;

    /** Default instantiable constructor */
    public Upgrade(Texture icon, String name, int price) {
	this.icon = icon;
	this.name = name;
	this.price = price;
    }
    
    @Override
    public String toString() {
	return  "Upgrade" + 
		"\nType: " + StringUtils.capitalizeFirstLetter(((FileTextureData)
			icon.getTextureData()).getFileHandle().path().replaceAll("upgrades/icons/", "").replaceAll(".png", "")) +
		"\nBought: " + bought + 
		"\nEquipped: " + equipped + 
		"\n";
    }
    
    public boolean isBought()			{ return this.bought; }
    public boolean isEquipped()			{ return this.equipped; }
    public int getPrice()			{ return this.price; }
    public Texture getIcon()			{ return this.icon; }
    public String getName()			{ return this.name; }
    public String getDesc()			{ return this.description; }
    
    public void setBought(boolean isBought)	{ this.bought = isBought; }
    public void setEquipped(boolean isEquipped)	{ this.equipped = isEquipped; }

}

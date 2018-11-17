package com.flizzet.upgradeshop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.EconomyComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.upgrades.Upgrade;
import com.flizzet.utils.FontUtils;

/**
 * Prompt for purchasing or declining an upgrade.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class UpgradeBuyPrompt extends GuiComponent {

    private UpgradeScrollView view;
    private Texture icon;
    private BitmapFont headerFont = FontUtils.UPHEAVAL_CURRENTSELECTION_MEDIUM;
    private BitmapFont descFont = FontUtils.UPHEAVAL_UPGRADEMENU_SMALL;
    private String header = "";
    private String description = "";
    private String price = "";
    private GlyphLayout headerLayout = new GlyphLayout(headerFont, header);
    private GlyphLayout descLayout = new GlyphLayout(descFont, description);
    private GlyphLayout priceLayout = new GlyphLayout(descFont, price);
    private PromptBuyButton buyButton;
    private PromptCancelButton cancelButton;
    private EconomyComponent economyView = new EconomyComponent();
    private float iconX, iconY;
    private float headerX, headerY;
    private float descX, descY;
    private float priceX, priceY;
    private static final int ICON_MULTIPLIER = 5;
    private static final int MARGIN = 5;
    
    /** Default instantiable constructor */
    public UpgradeBuyPrompt(UpgradeScrollView view) {
	super(0, 0);
	this.view = view;
	this.icon = GameManager.getInstance().assetManager.get("upgrades/icons/stickySoles.png");
	
	buyButton = new PromptBuyButton();
	cancelButton = new PromptCancelButton(view);
    }

    @Override
    public void update(float delta) {
	
	this.setX(view.getX() + 500);
	this.setY(view.getY());
	
	/* Setting economy position */
	economyView.setX(bounds.x);
	economyView.setY(bounds.y);
	economyView.update(delta);
	
	/* Setting icon position */
	iconX = bounds.x + MainCamera.getInstance().getCenterX() - ((icon.getWidth() * ICON_MULTIPLIER) / 2);
	iconY = bounds.y + MainCamera.getInstance().getCenterY();
	
	/* Setting header position */
	headerX = bounds.x + MainCamera.getInstance().getCenterX() - (headerLayout.width / 2);
	headerY = iconY + headerLayout.height + (icon.getWidth() * ICON_MULTIPLIER) + 5;
	
	/* Setting description position */
	descLayout.setText(descFont, description, Color.WHITE, MainCamera.getInstance().getWidth(), Align.center, true);
	descX = bounds.x + MainCamera.getInstance().getCenterX() - (MainCamera.getInstance().getWidth() / 2);
	descY = iconY - 5;
	
	/* Setting price position */
	priceX = bounds.x + MainCamera.getInstance().getCenterX() - (priceLayout.width / 2);
	priceY = descY - descLayout.height - 5;
	
	/* Setting button position */
	buyButton.setX(bounds.x + MainCamera.getInstance().getCenterX() - buyButton.getWidth() - MARGIN);
	buyButton.setY(priceY - priceLayout.height - buyButton.getHeight() - MARGIN);
	cancelButton.setX(bounds.x + MainCamera.getInstance().getCenterX() + MARGIN);
	cancelButton.setY(priceY - priceLayout.height - cancelButton.getHeight() - MARGIN);
	buyButton.update(delta);
	cancelButton.update(delta);
	
	
    }

    @Override
    public void render(SpriteBatch batch) {
	
	
	
	headerFont.setUseIntegerPositions(false);
	headerFont.setColor(Color.WHITE);
	headerFont.draw(batch, header, headerX, headerY);
	descFont.setUseIntegerPositions(false);
	descFont.draw(batch, descLayout, descX, descY);
	descFont.draw(batch, price, priceX, priceY);
	batch.draw(icon, iconX, iconY, icon.getWidth() * ICON_MULTIPLIER, icon.getHeight() * ICON_MULTIPLIER);
	
	buyButton.render(batch);
	cancelButton.render(batch);
	economyView.render(batch);

	
    }

    @Override
    public void triggered() {}
    
    public void setUpgrade(Upgrade newUpgrade) {
	this.header = newUpgrade.getName();
	this.icon = newUpgrade.getIcon();
	this.description = newUpgrade.getDesc();
	this.price = "$" + String.valueOf(newUpgrade.getPrice());
	
	buyButton.setUpgrade(newUpgrade);
	
	headerLayout = new GlyphLayout(headerFont, header);
	descLayout = new GlyphLayout(descFont, description);
	priceLayout = new GlyphLayout(descFont, price);
    }
    
}

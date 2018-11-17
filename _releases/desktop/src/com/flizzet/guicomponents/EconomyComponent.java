package com.flizzet.guicomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.camera.MainCamera;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.FontUtils;

/**
 * Draws the players score in the InGameGui.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also InGameGui
 */
public class EconomyComponent extends GuiComponent {
    
    private Texture coinIcon;
    private Texture billIcon;
    private GlyphLayout layout = new GlyphLayout();
    
    private float coinX, coinY;
    private float coinWidth, coinHeight;
    private float billX, billY;
    private float billWidth, billHeight;
    private float margin = 10;
    
    private int iconScale = 2;
    
    private AddMoneyButton addMoneyButton = new AddMoneyButton();

    /** Default instantiable constructor */
    public EconomyComponent() {
	super(0, 0);
	this.coinIcon = GameManager.getInstance().assetManager.get("gui/icons/coinIcon.png", Texture.class);
	this.billIcon = GameManager.getInstance().assetManager.get("gui/icons/billIcon.png", Texture.class);
	this.coinWidth = coinIcon.getWidth() * iconScale;
	this.coinHeight = coinIcon.getWidth() * iconScale;
	this.billWidth = billIcon.getWidth() * iconScale;
	this.billHeight = billIcon.getHeight() * iconScale;
    }

    @Override
    public void update(float delta) {
	/* Coin icon positioning */
	coinX = bounds.x + MainCamera.getInstance().getWidth() - margin - coinWidth;
	coinY = bounds.y + MainCamera.getInstance().getHeight() - margin - coinHeight;
	
	/* Platform icon positioning */
	billX = bounds.x + margin;
	billY = bounds.y + MainCamera.getInstance().getHeight() - margin - billHeight;
	
	/* Add money button */
	addMoneyButton.setX(bounds.x + MainCamera.getInstance().getCenterX() - (addMoneyButton.getWidth() / 2));
	addMoneyButton.setY(billY + (billHeight / 2) - (addMoneyButton.getHeight() / 2));
	if (CurrentSettings.getInstance().adsPlayable) {
	    if (GameManager.getInstance().stateManager.getCurrentState() != GameManager.getInstance().stateId_play) {
		if (!CurrentSettings.getInstance().moneyAdded)
		    addMoneyButton.update(delta);
	    }
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	/* Stabilize */
	float textSpacing = 3;
	
	/* Draw icons */
	batch.draw(coinIcon, coinX, coinY, coinWidth, coinHeight);
	batch.draw(billIcon, billX, billY, billWidth, billHeight);
	
	/* Draw numbers */
	/* Bills */
	BitmapFont font = FontUtils.UPHEAVAL_SCORECOUNTER_MEDIUM;
	layout.setText(font, String.valueOf(Player.getInstance().getScore().getTotalBills()));
	font.setUseIntegerPositions(false);
	font.draw(batch, String.valueOf(Player.getInstance().getScore().getTotalBills()),
		billX + billWidth + textSpacing, billY + (billHeight / 2) + (font.getCapHeight() / 2));

	/* Coins */
	layout.setText(font, String.valueOf(Player.getInstance().getScore().getTotalCoins()));
	font.draw(batch, String.valueOf(Player.getInstance().getScore().getTotalCoins()),
		coinX - layout.width - textSpacing, coinY + (coinHeight / 2) + (font.getCapHeight() / 2));
	
	/* Add money button */
	if (CurrentSettings.getInstance().adsPlayable) {
	    if (GameManager.getInstance().stateManager.getCurrentState() != GameManager.getInstance().stateId_play) {
		if (!CurrentSettings.getInstance().moneyAdded)
		    addMoneyButton.render(batch);
	    }
	}
	
    }

    @Override
    public void triggered() {}

}

class AddMoneyButton extends ButtonComponent{
    
    AddMoneyButton() {
	super(0, 0);
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/inGame/addMoneyBtn.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/inGame/addMoneyBtn.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/inGame/addMoneyBtnPushed.png", Texture.class));
    }
    
    @Override
    public void triggered() {
	AdManager.getInstance().showVideoAd();
    }
    
}

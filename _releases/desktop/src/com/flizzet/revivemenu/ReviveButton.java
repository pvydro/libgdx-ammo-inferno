package com.flizzet.revivemenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemies.MainEnemySpawner;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.ingamegui.InGameGui;
import com.flizzet.main.GameManager;
import com.flizzet.map.Platform;
import com.flizzet.map.PlatformGenerator;
import com.flizzet.player.Player;
import com.flizzet.player.PlayerState;

/**
 * Concrete revive accept button.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ReviveButton extends ButtonComponent {
    
    private Texture shineImage = GameManager.getInstance().assetManager.get("gui/reviveMenu/buttonShine.png");
    private Texture billIcon = GameManager.getInstance().assetManager.get("gui/icons/billIcon.png");
    private float billX, billY;
    private ReviveMenu menu;

    /** Default instantiable constructor */
    public ReviveButton(ReviveMenu menu) {
	super(0, 0);
	this.menu = menu;
	
	this.setImage(GameManager.getInstance().assetManager.get("gui/reviveMenu/reviveButton.png", Texture.class));
	this.setImageHovered(GameManager.getInstance().assetManager.get("gui/reviveMenu/reviveButton.png", Texture.class));
	this.setImagePushed(GameManager.getInstance().assetManager.get("gui/reviveMenu/reviveButtonPushed.png", Texture.class));
	
	this.subtext = "$15";
    }
    
    @Override
    public void update(float delta) {
	super.update(delta);
	
	float fullWidth = this.getTextWidth() + billIcon.getWidth() + 2;
	this.subtextX = this.getCenterX() + (fullWidth / 2) - (subtextLayout.width);
	billX = this.getCenterX() - (fullWidth / 2);
	billY = subtextY - (subtextLayout.height / 2) - (billIcon.getHeight() / 2);
    }

    @Override
    public void render(SpriteBatch batch) {
	super.render(batch);
	if (isPushed()) {
	    batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2, bounds.width - 4, bounds.height - 4);
	} else {
	    batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width, bounds.height);
	}
	batch.draw(billIcon, billX, billY);
    }
    
    @Override
    public void triggered() {
	Platform newPlatform = PlatformGenerator.getInstance().getPlatforms()
		.get((int) ((PlatformGenerator.getInstance().getPlatforms().size() - 1) / 2));
	float newY = newPlatform.getY() + newPlatform.getHeight() + 10;
	Player.getInstance().setY(newY);
	Player.getInstance().setX(newPlatform.getCenterX() - (Player.getInstance().getWidth() / 2));
	Player.getInstance().getHealthBar().setHealth(1);
	Player.getInstance().setState(PlayerState.IDLE);
	Player.getInstance().getController().reset();
	Player.getInstance().getWeapon().reset();
	Player.getInstance().getWeapon().setFireRate(10);
	Player.getInstance().setRevived(true);
	MainEnemySpawner.getInstance().reset();
	InGameGui.getInstance().buildGui();
	
	Player.getInstance().getScore().setBills(Player.getInstance().getScore().getTotalBills() - 15);
	
	GameNotification revivedNoti = new GameNotification("revived!");
	GameManager.getInstance().guiContainer.addToGui(revivedNoti);

	GameManager.getInstance().guiContainer.removeFromGui(menu);
    }

}

package com.flizzet.headshop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GameNotification;
import com.flizzet.headsystem.Head;
import com.flizzet.headsystem.Heads;
import com.flizzet.main.GameManager;
import com.flizzet.player.Player;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.sound.Sounds;

/**
 * Concrete buy button for the Heads shop.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 * @see also DeathMenu
 */
class PromptBuyButton extends ButtonComponent {

	private Head head;
	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/prompt/buttonShine.png");
	private int state = 0; // 0 = Buy; 1 = Already bought;

	/** Default instantiable constructor */
	public PromptBuyButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/prompt/buyButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/prompt/buyButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/prompt/buyButtonPushed.png", Texture.class));
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 4, bounds.y + 4, bounds.width - 8,
					bounds.height - 8);
		} else {
			batch.draw(shineImage, bounds.x, bounds.y, bounds.width,
					bounds.height);
		}
	}

	@Override
	public void triggered() {
		switch (state) {
			case 0:
				if (CurrentSettings.getInstance().brokeBoi) { // Buy for free
					Sounds.play(Sounds.getInstance().buySound, 1.0f);
					Heads.getInstance().buy(head.getClass(), false);
					Heads.getInstance().equip(head.getClass());
					setState(1);
					/* Successfully purchased notification */
					GameNotification notification = new GameNotification(
							"bought " + head.getName());
					GameManager.getInstance().guiContainer
							.addToGui(notification);
				} else if (Player.getInstance().getScore().getTotalCoins() > // Buy
																				// with
																				// money
				Heads.getInstance().getHead(head.getClass()).getPrice()) {
					Sounds.play(Sounds.getInstance().buySound, 1.0f);
					Heads.getInstance().buy(head.getClass(), true);
					Heads.getInstance().equip(head.getClass());
					setState(1);
					/* Successfully purchased notification */
					GameNotification notification = new GameNotification(
							"bought " + head.getName());
					GameManager.getInstance().guiContainer
							.addToGui(notification);
				} else { // Not enough money
					GameNotification notification = new GameNotification(
							"insufficient funds");
					GameManager.getInstance().guiContainer
							.addToGui(notification);
				}
				break;
			case 1: // Already bought
				GameNotification notification = new GameNotification(
						"already purchased");
				GameManager.getInstance().guiContainer.addToGui(notification);
				break;
		}
	}

	/** Sets a new upgrade for actions to be triggered on */
	public void setHead(Head newHead) {
		this.head = newHead;
		/*
		 * Set the state of the button depending on whether or not the upgrade
		 * is already bought/equipped
		 */
		if (Heads.getInstance().isBought(newHead.getClass())) {
			setState(1);
		} else {
			setState(0);
		}
	}

	/** Changes the state of the button between Buy, Equip, and Equipped */
	public void setState(int newState) {
		this.state = newState;
		/* Set a new image based on the newly defined state */
		switch (newState) {
			case 0:
				this.setImage(GameManager.getInstance().assetManager
						.get("gui/prompt/buyButton.png", Texture.class));
				this.setImageHovered(GameManager.getInstance().assetManager
						.get("gui/prompt/buyButton.png", Texture.class));
				this.setImagePushed(GameManager.getInstance().assetManager
						.get("gui/prompt/buyButtonPushed.png", Texture.class));
				break;
			case 1:
				this.setImage(GameManager.getInstance().assetManager
						.get("gui/prompt/boughtButton.png", Texture.class));
				this.setImageHovered(GameManager.getInstance().assetManager
						.get("gui/prompt/boughtButton.png", Texture.class));
				this.setImagePushed(GameManager.getInstance().assetManager.get(
						"gui/prompt/boughtButtonPushed.png", Texture.class));
				break;
		}
	}

}

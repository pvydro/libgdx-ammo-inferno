package com.flizzet.revivemenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.ads.AdManager;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.main.GameManager;
import com.flizzet.main.GameState;
import com.flizzet.main.PauseManager;
import com.flizzet.player.Player;

/**
 * Concrete video play button for the revive menu.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayVideoButton extends ButtonComponent {

	private Texture shineImage = GameManager.getInstance().assetManager
			.get("gui/reviveMenu/buttonShine.png");

	/** Default instantiable constructor */
	public PlayVideoButton() {
		super(0, 0);

		this.setImage(GameManager.getInstance().assetManager
				.get("gui/reviveMenu/videoButton.png", Texture.class));
		this.setImageHovered(GameManager.getInstance().assetManager
				.get("gui/reviveMenu/videoButton.png", Texture.class));
		this.setImagePushed(GameManager.getInstance().assetManager
				.get("gui/reviveMenu/videoButtonPushed.png", Texture.class));

		this.subtext = "video";
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (isPushed()) {
			batch.draw(shineImage, bounds.x + 2, subtextY + 1 + 2,
					bounds.width - 4, bounds.height - 4);
		} else {
			batch.draw(shineImage, bounds.x, subtextY + 1, bounds.width,
					bounds.height);
		}
	}

	@Override
	public void triggered() {
		Player.getInstance().getScore()
				.setBills(Player.getInstance().getScore().getTotalBills() + 15);
		AdManager.getInstance().showVideoAd();
		/* Revive, then add $15 to level out the removal */
		Player.getInstance().getReviveMenu().getReviveButton().triggered();
		Player.getInstance().getReviveMenu().stopCountdown();
		/* Enter pause state */
		PauseManager.getInstance().setState(GameState.REVIVE);
	}

}

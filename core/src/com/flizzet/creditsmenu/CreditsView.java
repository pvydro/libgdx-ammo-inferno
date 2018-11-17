package com.flizzet.creditsmenu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.ButtonComponent;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.guicomponents.SeparatorComponent;
import com.flizzet.guicomponents.SeparatorFillerComponent;
import com.flizzet.main.GameManager;
import com.flizzet.utils.FontUtils;

/**
 * Displays the credits.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CreditsView extends GuiComponent {

	private BitmapFont smallFont = FontUtils.UPHEAVAL_75;
	private BitmapFont mediumFont = FontUtils.UPHEAVAL_120;
	private BitmapFont largeFont = FontUtils.UPHEAVAL_175;

	private final int MARGIN = 15;
	private final int STACK_MARGIN = 2;

	/* Programmed by */
	private String programmedBy = "programming and";
	private GlyphLayout programmedByLayout = new GlyphLayout(smallFont,
			programmedBy);
	private float programmedByX, programmedByY;

	/* Art by */
	private String artBy = "art and";
	private GlyphLayout artByLayout = new GlyphLayout(smallFont, artBy);
	private float artByX, artByY;

	/* UI and UX by */
	private String uiuxBy = "ui and ux and";
	private GlyphLayout uiuxByLayout = new GlyphLayout(smallFont, uiuxBy);
	private float uiuxX, uiuxY;

	/* Migraines and */
	private String migrainesBy = "migraines and";
	private GlyphLayout migrainesByLayout = new GlyphLayout(smallFont,
			migrainesBy);
	private float migrainesByX, migrainesByY;

	/* Sleepless nights and */
	private String sleeplessNightsBy = "sleepless nights and";
	private GlyphLayout sleeplessNightsLayout = new GlyphLayout(smallFont,
			sleeplessNightsBy);
	private float sleeplessNightsX, sleeplessNightsY;

	/* Design and */
	private String designBy = "design and";
	private GlyphLayout designByLayout = new GlyphLayout(smallFont, designBy);
	private float designByX, designByY;

	/* Engine and */
	private String engineBy = "engine and";
	private GlyphLayout engineByLayout = new GlyphLayout(smallFont, engineBy);
	private float engineByX, engineByY;

	/* Monetization and */
	private String monetizationBy = "monetization and";
	private GlyphLayout monetizationByLayout = new GlyphLayout(smallFont,
			monetizationBy);
	private float monetizationByX, monetizationByY;

	/* Maintenance and */
	private String maintenanceBy = "maintenance and";
	private GlyphLayout maintenanceByLayout = new GlyphLayout(smallFont,
			maintenanceBy);
	private float maintenanceByX, maintenanceByY;

	/* Store design and */
	private String storeDesignBy = "store design and";
	private GlyphLayout storeDesignByLayout = new GlyphLayout(smallFont,
			storeDesignBy);
	private float storeDesignByX, storeDesignByY;

	/* Distribution by */
	private String distributionBy = "distribution by";
	private GlyphLayout distributionByLayout = new GlyphLayout(smallFont,
			distributionBy);
	private float distributionByX, distributionByY;

	/* Pedro Dutra */
	private String pedro = "pedro dutra";
	private String flizzet = "@flizzet";
	private GlyphLayout pedroLayout = new GlyphLayout(largeFont, pedro);
	private GlyphLayout flizzetLayout = new GlyphLayout(smallFont, flizzet);
	private float pedroX, pedroY;
	private float flizzetX, flizzetY;

	/* In Java with */
	private String inJava = "in java with";
	private GlyphLayout inJavaLayout = new GlyphLayout(smallFont, inJava);
	private float inJavaX, inJavaY;

	/* LibGDX */
	private Texture libgdxLogo = GameManager.getInstance().assetManager
			.get("gui/creditsMenu/libgdxLogo.png");
	private float libgdxX, libgdxY;

	/* Separator */
	private SeparatorComponent firstSeparator = new SeparatorComponent(2);
	private SeparatorComponent contactSeparator = new SeparatorComponent(2,
			"contact");
	private SeparatorFillerComponent filler = new SeparatorFillerComponent(
			firstSeparator, contactSeparator);

	/* Contact buttons */
	private List<ButtonComponent> contactButtons = new ArrayList<ButtonComponent>();

	/** Default instantiable constructor */
	public CreditsView() {
		super(0, 0);
		smallFont.setUseIntegerPositions(false);
		mediumFont.setUseIntegerPositions(false);
		largeFont.setUseIntegerPositions(false);

		/* Set up contact buttons */
		contactButtons.add(new ContactEmailButton());
		contactButtons.add(new ContactInstaButton());
	}

	@Override
	public void update(float delta) {
		/* First separator */
		firstSeparator.setY(bounds.y);
		firstSeparator.update(delta);

		/* Programming and */
		programmedByX = MARGIN;
		programmedByY = firstSeparator.getY() - MARGIN;

		/* Art and */
		artByX = MARGIN;
		artByY = programmedByY - programmedByLayout.height - STACK_MARGIN;

		/* UI and UX and */
		uiuxX = MARGIN;
		uiuxY = artByY - artByLayout.height - STACK_MARGIN;

		/* Migraines and */
		migrainesByX = MARGIN;
		migrainesByY = uiuxY - uiuxByLayout.height - STACK_MARGIN;

		/* Sleepless nights and */
		sleeplessNightsX = MARGIN;
		sleeplessNightsY = migrainesByY - migrainesByLayout.height
				- STACK_MARGIN;

		/* Design and */
		designByX = MARGIN;
		designByY = sleeplessNightsY - sleeplessNightsLayout.height
				- STACK_MARGIN;

		/* Engine and */
		engineByX = MARGIN;
		engineByY = designByY - designByLayout.height - STACK_MARGIN;

		/* Monetization and */
		monetizationByX = MARGIN;
		monetizationByY = engineByY - engineByLayout.height - STACK_MARGIN;

		/* Maintenance and */
		maintenanceByX = MARGIN;
		maintenanceByY = monetizationByY - monetizationByLayout.height
				- STACK_MARGIN;

		/* Store design and */
		storeDesignByX = MARGIN;
		storeDesignByY = maintenanceByY - maintenanceByLayout.height
				- STACK_MARGIN;

		/* Distribution by */
		distributionByX = MARGIN;
		distributionByY = storeDesignByY - storeDesignByLayout.height
				- STACK_MARGIN;

		/* Pedro Dutra */
		pedroX = MARGIN;
		pedroY = distributionByY - distributionByLayout.height - STACK_MARGIN;
		flizzetX = MARGIN;
		flizzetY = pedroY - pedroLayout.height - STACK_MARGIN;

		/* In Java with */
		inJavaX = MainCamera.getInstance().getWidth() - MARGIN
				- inJavaLayout.width;
		inJavaY = flizzetY - flizzetLayout.height - STACK_MARGIN;

		/* LibGDX */
		libgdxX = MainCamera.getInstance().getWidth() - MARGIN
				- libgdxLogo.getWidth();
		libgdxY = inJavaY - inJavaLayout.height - STACK_MARGIN
				- libgdxLogo.getHeight();

		/* Separate */
		contactSeparator
				.setY(libgdxY - libgdxLogo.getHeight() - STACK_MARGIN - 20);
		contactSeparator.update(delta);
		filler.update(delta);

		/* Position contact buttons */
		int totalButtons = 0;
		for (ButtonComponent b : contactButtons) {
			b.setX(MainCamera.getInstance().getWidth() - b.getWidth() - MARGIN
					- ((b.getWidth() + (STACK_MARGIN * 2)) * totalButtons));
			b.setY(contactSeparator.getY() - b.getHeight()
					- (STACK_MARGIN * 2));
			b.update(delta);
			totalButtons++;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		/* Separator filler */
		filler.render(batch);
		/* First separator */
		firstSeparator.render(batch);
		/* Pogramming and */
		smallFont.draw(batch, programmedBy, programmedByX, programmedByY);
		/* Art and */
		smallFont.draw(batch, artBy, artByX, artByY);
		/* UI and UX and */
		smallFont.draw(batch, uiuxBy, uiuxX, uiuxY);
		/* Migraines and */
		smallFont.draw(batch, migrainesBy, migrainesByX, migrainesByY);
		/* Sleepless nights and */
		smallFont.draw(batch, sleeplessNightsBy, sleeplessNightsX,
				sleeplessNightsY);
		/* Design and */
		smallFont.draw(batch, designBy, designByX, designByY);
		/* Engine and */
		smallFont.draw(batch, engineBy, engineByX, engineByY);
		/* Monetization and */
		smallFont.draw(batch, monetizationBy, monetizationByX, monetizationByY);
		/* Maintenance and */
		smallFont.draw(batch, maintenanceBy, maintenanceByX, maintenanceByY);
		/* Store design and */
		smallFont.draw(batch, storeDesignBy, storeDesignByX, storeDesignByY);
		/* Distribution by */
		smallFont.draw(batch, distributionBy, distributionByX, distributionByY);
		/* Pedro Dutra */
		largeFont.draw(batch, pedro, pedroX, pedroY);
		smallFont.draw(batch, flizzet, flizzetX, flizzetY);
		/* In Java with */
		smallFont.draw(batch, inJava, inJavaX, inJavaY);
		/* LibGDX */
		batch.draw(libgdxLogo, libgdxX, libgdxY);
		/* Separator */
		contactSeparator.render(batch);
		/* Render all contact buttons */
		for (ButtonComponent b : contactButtons) {
			b.render(batch);
		}
	}

	@Override
	public void triggered() {
	}

}

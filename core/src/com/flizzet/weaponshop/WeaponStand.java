package com.flizzet.weaponshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flizzet.camera.MainCamera;
import com.flizzet.guicomponents.GuiComponent;
import com.flizzet.main.GameManager;
import com.flizzet.particles.WeaponShopParticle;

/**
 * Weapon stand.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class WeaponStand extends GuiComponent {

	private Texture image;
	private Texture holoImage;
	private Random random = new Random();
	private Queue<WeaponShopParticle> toBeRemoved = new LinkedList<WeaponShopParticle>();
	private ArrayList<WeaponShopParticle> particles = new ArrayList<WeaponShopParticle>();
	private BuyButton buyButton;
	private ScrollView view;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	private int particleCooldown = 10;
	private float yChange = -400;
	private float holoX, holoY;

	/** Default instantiable constructor */
	public WeaponStand(ScrollView view) {
		super(0, 0);

		this.view = view;
		this.buyButton = new BuyButton(view);
		this.image = GameManager.getInstance().assetManager
				.get("gui/weaponShop/stand.png", Texture.class);
		this.holoImage = GameManager.getInstance().assetManager
				.get("gui/weaponShop/holo.png", Texture.class);
		this.adjustBoundsToImage();
	}

	@Override
	public void update(float delta) {

		particles.removeAll(toBeRemoved);
		toBeRemoved.removeAll(toBeRemoved);

		this.setX(
				MainCamera.getInstance().getCenterX() - (this.getWidth() / 2));

		holoX = this.getX();
		holoY = this.getY() + this.getHeight() + yChange;

		yChange += (0 - yChange) / 5;

		/* Adding particles */
		particleCooldown--;
		if (particleCooldown <= 0) {
			particleCooldown = random.nextInt(15);
			addParticle();
		}

		/* Update particles */
		for (WeaponShopParticle p : particles) {
			p.update(delta);
		}

		/* Set the buy buttons' weapon */
		buyButton.setWeapon(
				view.getViews().get(view.getCurrentSelection()).getWeapon());

		/* Position buy button */
		buyButton.setX(MainCamera.getInstance().getCenterX()
				- (buyButton.getWidth() / 2));
		buyButton.setY(20);
		buyButton.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {

		/* Render particles */
		for (WeaponShopParticle p : particles) {
			p.render(batch, shapeRenderer);
		}
		batch.draw(image, bounds.x, bounds.y + yChange);
		batch.draw(holoImage, holoX, holoY);

		buyButton.render(batch);
	}

	/** Add a new particle to the screen */
	private void addParticle() {
		WeaponShopParticle newParticle = new WeaponShopParticle(this);
		newParticle.setX(
				this.getX() + 4 + random.nextInt((int) this.getWidth() - 8)
						- newParticle.getWidth());
		newParticle
				.setY(this.getY() + this.getHeight() - newParticle.getHeight());
		particles.add(newParticle);
	}

	/** Removes a particle from the screen */
	public void removeParticle(WeaponShopParticle oldParticle) {
		toBeRemoved.add(oldParticle);
	}

	@Override
	public void triggered() {
	}

	/** Sets dimensions to image width and height */
	private void adjustBoundsToImage() {
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
	}

}

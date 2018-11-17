package com.flizzet.enemies;

import java.util.Random;

import com.flizzet.main.GameManager;
import com.flizzet.score.Bill;
import com.flizzet.score.Coin;
import com.flizzet.upgrades.ZombieLooterUpgrade;
import com.flizzet.upgradesystem.Upgrades;
import com.flizzet.zombieenemy.ZombieEnemy;

/**
 * Deals points to the player for killing an enemy.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EnemyScoreDealer {

    private Enemy enemy;
    private Random random = new Random();
    
    /** Default instantiable constructor */
    public EnemyScoreDealer(Enemy enemy) {
	this.enemy = enemy;
    }
    
    /** Chooses which point to give the player randomly */
    public void newPoint() {
	int chance = random.nextInt(100);
	
	/* 80% chance of coin; 10% chance of bill;  10% chance of nothing */
	if (chance >= 90) {
	    addBill();
	} else if (chance >= 10) {
	    addCoin();
	    if (Upgrades.getInstance().isEquipped(ZombieLooterUpgrade.class) && enemy instanceof ZombieEnemy) {
		addCoin();
	    }
	}
    }
    
    /** Adds a coin at the enemy position */
    public void addCoin() {
	Coin coin = new Coin();
	
	coin.setX(enemy.getCenterX() + (random.nextBoolean() ? random.nextInt(5) : -random.nextInt(5)) - (coin.getWidth() / 2));
	coin.setY(enemy.getCenterY() - (coin.getHeight() / 2));
	
	GameManager.getInstance().entityContainer.add(coin);
    }
    
    /** Adds a bill at the enemy position */
    public void addBill() {
	Bill bill = new Bill();
	
	bill.setX(enemy.getCenterX() - (bill.getWidth() / 2));
	bill.setY(enemy.getCenterX() - (bill.getHeight() / 2));
	
	GameManager.getInstance().entityContainer.add(bill);
    }
}

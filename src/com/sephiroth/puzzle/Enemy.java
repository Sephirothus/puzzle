package com.sephiroth.puzzle;

import android.os.Handler;

import java.util.Random;

/**
 * Created by sephirothus on 13.12.15.
 */
public class Enemy extends Unit {

    public int health;
    public int strength;
    private final String letter = "E";

    public Enemy(GameManager gameManager, int health, int strength) {
        this.health = health;
        this.strength = strength;
        this.gameManager = gameManager;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public void action() {
        gameManager.map.makeBattleGrid();
        gameManager.createBattleEvent(this);
    }

    public void getDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            gameManager.winMessage();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameManager.retrieveMainMap();
                }
            }, 1000);
        } else {
            makeHit();
        }
    }

    public void makeHit() {
        /*gameManager.player.health -= gameManager.map.openRandomCellinBattle();
        if (gameManager.player.health <= 0) {

        }*/
    }
}

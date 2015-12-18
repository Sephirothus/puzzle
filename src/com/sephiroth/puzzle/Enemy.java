package com.sephiroth.puzzle;

import android.os.Handler;

import java.util.ArrayList;
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
    public boolean action() {
        gameManager.map.makeBattleGrid();
        ArrayList<String> stats = gameManager.player.getStats();
        stats.addAll(getStats());
        //gameManager.setStatuses(stats);
        gameManager.createBattleEvent(this);
        return false;
    }

    private ArrayList<String> getStats() {
        ArrayList<String> stats = new ArrayList<String>();
        stats.add("Enemy Health: " + health);
        stats.add("Enemy Strengh: " + strength);
        return stats;
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

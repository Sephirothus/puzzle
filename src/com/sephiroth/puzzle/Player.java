package com.sephiroth.puzzle;

import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sephirothus on 13.12.15.
 */
public class Player {
    private final GameManager gameManager;
    public int health;
    private int strength;
    private int moves;
    private int level;
    private int exp;
    private int leftMoves;

    public Player(GameManager gameManager) {
        this.health = 10;
        this.strength = 5;
        this.moves = this.leftMoves = 3;
        this.level = 1;
        this.exp = 0;
        this.gameManager = gameManager;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getMoves() {
        return moves;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void makeMove() {
        leftMoves--;
    }

    public ArrayList<String> getStats() {
        ArrayList<String> stats = new ArrayList<String>();
        stats.add("Health: " + getHealth());
        stats.add("Strengh: " + getStrength());
        return stats;
    }

    public boolean checkMoves() {
        if (leftMoves <= 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameManager.map.nextLevel();
                    gameManager.map.createMap();
                    leftMoves = moves;
                }
            }, 1000);
            return false;
        }
        return true;
    }
}

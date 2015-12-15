package com.sephiroth.puzzle;

import java.util.Random;

/**
 * Created by sephirothus on 13.12.15.
 */
public class Unit {
    private static String[] letters = new String[] {
        "E", "G", ""
    };
    public GameManager gameManager;

    public static String getRandomUnit() {
        Random random = new Random();
        return letters[random.nextInt(letters.length)];
    }

    public static Unit getUnitByLetter(GameManager gameManager, String letter, Integer level) {
        Random random = new Random();
        Unit unit = new Unit();
        switch (letter) {
            case "E":
                unit = new Enemy(gameManager, level + random.nextInt(6) + 1, level + random.nextInt(3) + 1);
                break;
            case "G":
                unit = new Gold();
                break;
            case "":
                unit = new Unit();
                break;
        }
        return unit;
    }

    public void action() {
        // Empty action
    }
}

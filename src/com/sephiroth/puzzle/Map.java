package com.sephiroth.puzzle;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by sephirothus on 11.12.15.
 */
public class Map {

    public static final int MAP_WIDTH_PERCENT = 80;
    public static final int MAP_HEIGHT_PERCENT = 80;
    public static final int MAP_ROWS = 8;
    public static final int MAP_CELL_WIDTH = 30;
    private final GameManager gameManager;
    private int level;
    private ArrayList<String> units;
    public ArrayList<Integer> openUnits;
    public ArrayList<Integer> battleNums;
    private final int height;
    private final int width;

    public Map(GameManager gameManager, int width, int height) {
        this.gameManager = gameManager;
        this.width = width * MAP_WIDTH_PERCENT / 100;
        this.height = height * MAP_HEIGHT_PERCENT / 100;
        this.level = 1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getUnits() {
        return units;
    }

    public ArrayList<Integer> getBattleNums() {
        return battleNums;
    }

    public void nextLevel() {
        this.level++;
    }

    public void createMap() {
        createUnits();
        this.gameManager.setGrid(getEmptyClonedUnits(), "#43b71f");
        this.gameManager.createMainEvent();
        this.gameManager.lvlMessage();
    }

    private void createUnits() {
        this.units = new ArrayList<String>();
        this.openUnits = new ArrayList<Integer>();
        for (int i = 0; i < MAP_ROWS * getRowCountCells(); i++) {
            this.units.add(Unit.getRandomUnit());
        }
    }

    public void createBattleNums(int size, int except) {
        battleNums = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            if (i != except) this.battleNums.add(random.nextInt(9) + 1);
        }
    }

    public ArrayList<String> retrieveMainMap() {
        ArrayList<String> map = getEmptyClonedUnits();
        for (int index : openUnits) {
            map.set(index, units.get(index));
        }
        return map;
    }

    public ArrayList<String> getEmptyClonedUnits() {
        return new ArrayList<String>(Collections.nCopies(units.size(), ""));
    }

    public void makeBattleGrid() {
        ArrayList<String> units = getEmptyClonedUnits();
        // get last open unit index
        Integer index = getLastOpenUnitIndex();
        units.set(index, getUnits().get(index));
        gameManager.setGrid(units, "#ff2021");
        createBattleNums(units.size(), index);
    }

    public int getLastOpenUnitIndex() {
        return openUnits.get(openUnits.size() - 1);
    }

    /*public int openRandomCellinBattle() {
        Random random = new Random();
        int cellIndex;
        do {
            cellIndex = random.nextInt(getBattleNums().size());
        } while (checkIfCellIsOpened());
        return getBattleNums().get(cellIndex);
    }

    public boolean checkIfCellIsOpened(int cell, ArrayList cells) {
        return ;
    }*/

    private Integer getRowCountCells() {
        return 11;
    }
}

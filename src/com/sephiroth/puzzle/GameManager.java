package com.sephiroth.puzzle;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sephirothus on 11.12.15.
 */
public class GameManager {

    private static int width;
    private static int height;
    public Map map;
    public Player player;
    private GameView gameView;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public GameManager(GameView gameView, int width, int height) {
        this.player = new Player(this);
        this.gameView = gameView;
        this.map = new Map(this, width, height);
        map.createMap();
        /*Toast toast = Toast.makeText(gameView.getContext(), width, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/
    }

    public void setGrid(ArrayList<String> units, String color) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(gameView.getContext(), android.R.layout.simple_list_item_1, units) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setBackgroundColor(Color.parseColor(color));
                return view;
            }
        };
        this.gameView.getGridView().setAdapter(adapter);
        //this.gameView.redraw();
    }

    public void createMainEvent() {
        GameManager gameManager = this;
        this.gameView.getGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ArrayList<String> units = map.getUnits();
                ((TextView) v).setText(units.get(position));
                v.setBackgroundColor(Color.parseColor("#6a3c22"));
                map.openUnits.add(position);
                Unit unit = Unit.getUnitByLetter(gameManager, units.get(position), map.getLevel());
                unit.action();
                player.makeAndcheckMoves();
                //Toast.makeText(getContext(), units.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createBattleEvent(Enemy enemy) {
        Toast.makeText(gameView.getContext(), "Enemy health: " + enemy.health, Toast.LENGTH_SHORT).show();
        this.gameView.getGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (position == map.getLastOpenUnitIndex()) return;
                ArrayList<Integer> nums = map.getBattleNums();
                ((TextView) v).setText(Integer.toString(nums.get(position)));
                v.setBackgroundColor(Color.parseColor("#6a3c22"));
                enemy.getDamage(nums.get(position));
                //Toast.makeText(gameView.getContext(), nums.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrieveMainMap() {
        setGrid(map.retrieveMainMap(), "#43b71f");
        createMainEvent();
    }

    public void lvlMessage() {
        message("LEVEL: " + map.getLevel());
    }

    public void winMessage() {
        message("You win!");
    }

    public void message(String message) {
        Toast.makeText(gameView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

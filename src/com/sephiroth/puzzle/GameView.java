package com.sephiroth.puzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sephirothus on 11.12.15.
 */
public class GameView extends GridView {

    private GameManager gameManager;
    private GridView gridView;
    private static int width;
    private static int height;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initGrid();
        gameManager = new GameManager(this, width, height);
    }

    public GridView getGridView() {
        return gridView;
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    private void initGrid() {
        this.gridView = (GridView) findViewById(R.id.gridview);
    }

    public void redraw() {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

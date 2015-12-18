package com.sephiroth.puzzle;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;

/**
 * Created by sephirothus on 11.12.15.
 */
public class GameView extends GridView {

    private static int width;
    private static int height;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView getGridView() {
        return (GridView) findViewById(R.id.main_map);
    }

    public ListView getStatusBar() {
        return (ListView) findViewById(R.id.status);
    }

    public void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    public void redraw() {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

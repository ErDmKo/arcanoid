package tk.erdmko.arcanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * Created by erdmko on 21.01.15.
 */
public abstract class GameObject {
    protected int coord_left, coord_top, coord_right, coord_bottom;
    protected static int bgColor = Color.BLUE;
    protected int color;
    protected Canvas canvas;
    protected Point position;
    private static final String TAG = "GameObject";

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    protected GameObject(int width, int height, Point center, int color) {
        this.coord_left = center.x - width/2;
        this.coord_right = this.coord_left + width;
        this.coord_top = center.y - height/2;
        this.coord_bottom = this.coord_top + height;
        position = center;
        this.color = color;
    }
    protected GameObject(int coord_left, int coord_top, int coord_right, int coord_bottom, int color) {
        this.coord_left = coord_left;
        this.coord_top = coord_top;
        this.coord_right = coord_right;
        this.coord_bottom = coord_bottom;
        this.color = color;
        int centerX = coord_left +(coord_right - coord_left) / 2;
        int cenrerY = coord_top + (coord_bottom - coord_top) / 2;
        this.position = new Point(centerX, cenrerY);
    }

    abstract void draw(int color);
    abstract void onTouch(Point p);

    public void show() {
       draw(color);
    }
    public void  hide() {
       draw(bgColor);
    }
    public void move(int dx, int dy) {
        //hide();
        Log.i(TAG, "old coords" + position.x + " "+position.y);
        Log.i(TAG, "old coords" + coord_left + " "+coord_right+" "+coord_top+" "+coord_bottom);
        this.coord_left += dx;
        this.coord_top += dy;
        this.coord_right += dx;
        this.coord_bottom += dy;
        this.position.offset(dx, dy);
        Log.i(TAG, "new coords" + position.x + " "+position.y);
        Log.i(TAG, "new coords" + coord_left + " "+coord_right+" "+coord_top+" "+coord_bottom);
        //show();
    }
}

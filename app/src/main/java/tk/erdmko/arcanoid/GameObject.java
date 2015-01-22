package tk.erdmko.arcanoid;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Vector;

/**
 * Created by erdmko on 21.01.15.
 */
public abstract class GameObject {
    protected int coord_left, coord_top, coord_right, coord_bottom;
    protected static int bgColor = Color.BLUE;
    protected int color;
    protected Canvas canvas;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    protected GameObject(int coord_left, int coord_top, int coord_right, int coord_bottom, int color) {
        this.coord_left = coord_left;
        this.coord_top = coord_top;
        this.coord_right = coord_right;
        this.coord_bottom = coord_bottom;
        this.color = color;
    }

    abstract void draw(int color);

    public void show() {
       draw(color);
    }
    public void  hide() {
       draw(bgColor);
    }
    public void move(int dx, int dy) {
        hide();
        this.coord_left += dx;
        this.coord_top += dx;
        this.coord_right += dy;
        this.coord_bottom += dy;
        show();
    }
}

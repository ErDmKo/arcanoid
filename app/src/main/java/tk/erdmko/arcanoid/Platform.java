package tk.erdmko.arcanoid;

import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by erdmko on 21.01.15.
 */
public class Platform extends GameObject {
    private Paint paint = new Paint();
    private static final String TAG = "GameObject";

    public Platform(int width, int height, Point p, int color) {
        super(width, height, p, color);
        paint.setStyle(Paint.Style.FILL);
    }
    protected Platform(int coord_left, int coord_top, int coord_right, int coord_bottom, int color) {
        super(coord_left, coord_top, coord_right, coord_bottom, color);
        paint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void draw(int color) {
        paint.setColor(color);
        if (this.canvas == null) {
            Log.i(TAG, "No canv");
            return;
        }
        this.canvas.drawRect(coord_left, coord_top, coord_right, coord_bottom, paint);
    }

    @Override
    void onTouch(Point p) {
        Log.i(TAG, "Touch");
        this.moveTo(p.x);
    }

    public void moveTo(int x) {
        int dx;
        dx = x - position.x;
        move(dx, 0);
    }
}

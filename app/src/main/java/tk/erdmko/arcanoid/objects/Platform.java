package tk.erdmko.arcanoid.objects;

import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by erdmko on 21.01.15.
 */
public class Platform extends GameObject {

    private static final String TAG = "GameObject";

    public Platform(int width, int height, Point p, int color) {
        super(width, height, p, color);
    }

    @Override
    protected void draw() {
        if (this.canvas == null) {
            Log.i(TAG, "No canv");
            return;
        }
        this.canvas.drawRect(coord_left, coord_top, coord_right, coord_bottom, paint);
    }

    @Override
    public void onTouch(Point p) {
        Log.i(TAG, "Touch");
        this.moveTo(p.x);
    }

    private void moveTo(int x) {
        int dx;
        dx = x - position.x;
        move(dx, 0);
    }
}

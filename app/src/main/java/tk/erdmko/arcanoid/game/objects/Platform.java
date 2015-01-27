package tk.erdmko.arcanoid.game.objects;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by erdmko on 21.01.15.
 */
public class Platform extends GameObject {

    private static final String TAG = "Platform";

    public Platform(int width, int height, Vector2d p, int color) {
        super(width, height, p, color);
    }

    @Override
    protected void draw(Canvas canvas) {
        if (canvas == null) {
            Log.i(TAG, "No canv");
            return;
        }
        canvas.drawRect(coord_left, coord_top, coord_right, coord_bottom, paint);
    }

    @Override
    public void onTouch(Vector2d v) {
        this.moveTo(v.x);
    }

    private void moveTo(float x) {
        float dx;
        dx = x - position.x;
        move(dx, 0);
    }
}

package tk.erdmko.arcanoid.objects;

import android.util.Log;

/**
 * Created by erdmko on 22.01.15.
 */
public class Block extends GameObject {
    private static final String TAG = "Block";

    public Block(int width, int height, int color) {
        super(width, height, new Vector2d(0, 0), color);
    }


    public Block(int width, int height, Vector2d p, int color) {
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
    public void onTouch(Vector2d v) {

    }

}

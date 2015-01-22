package tk.erdmko.arcanoid;

import android.graphics.Paint;

/**
 * Created by erdmko on 21.01.15.
 */
public class Platform extends GameObject {
    private Paint paint;
    private static final String TAG = "GameObject";
    protected Platform(int coord_left, int coord_top, int coord_right, int coord_bottom, int color) {
        super(coord_left, coord_top, coord_right, coord_bottom, color);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void draw(int color) {
        paint.setColor(color);
        //this.canvas.drawColor(Color.BLUE);
        this.canvas.drawRect(coord_left, coord_top, coord_right, coord_bottom, paint);
    }
    public void moveTo(int x) {
        int center = coord_left +(coord_right - coord_left) / 2;
        move(center-x, 0);
    }
}

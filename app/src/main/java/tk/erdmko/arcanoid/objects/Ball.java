package tk.erdmko.arcanoid.objects;

import android.graphics.Point;

/**
 * Created by erdmko on 22.01.15.
 */
public class Ball extends GameObject {
    public int radius;

    public Ball(int radius, Point center, int color) {
        this(radius*2, radius*2, center, color);
        this.radius = radius;
    }

    public Ball(int width, int height, Point center, int color) {
        super(width, height, center, color);
        this.radius = width/2;
    }

    @Override
    protected void draw() {
        this.canvas.drawCircle(position.x, position.y, radius, paint);
    }

    @Override
    public void onTouch(Point p) {

    }
}

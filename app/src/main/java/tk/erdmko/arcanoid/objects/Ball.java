package tk.erdmko.arcanoid.objects;

import android.util.Log;

/**
 * Created by erdmko on 22.01.15.
 */
public class Ball extends GameObject {
    public int radius;
    private Vector2d speed = new Vector2d(-5, 5);
    private static final String TAG = "Ball";


    public Ball(int radius, Vector2d center, int color) {
        this(radius*2, radius*2, center, color);
        this.radius = radius;
    }

    public Ball(int width, int height, Vector2d center, int color) {
        super(width, height, center, color);
        this.radius = width/2;
    }

    @Override
    protected void draw() {
        move(speed);
        this.canvas.drawCircle(position.x, position.y, radius, paint);
    }

    @Override
    public void onTouch(Vector2d p) {

    }

    @Override
    protected void onCollision(GameObject obj, Vector2d collisionInfo) {
        super.onCollision(obj, collisionInfo);
        collisionInfo.direction();
        move(speed.cpy().mul(-1));
        Vector2d oldCollision = getCollisionSize(obj).direction();

        if (oldCollision.x != collisionInfo.x) {
            speed.mul(new Vector2d(-1, 1));
        } else if (oldCollision.y != collisionInfo.y){
            speed.mul(new Vector2d(1, -1));
        } else {
            speed.mul(-1);
        }
    }
}

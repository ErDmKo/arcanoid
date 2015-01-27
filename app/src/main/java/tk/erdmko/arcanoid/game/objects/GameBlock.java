package tk.erdmko.arcanoid.game.objects;

import android.util.Log;

/**
 * Created by erdmko on 26.01.15.
 */
public class GameBlock extends Block implements BallCollision {
    private int live = 1;
    private static final String TAG = "GameBlock";

    public GameBlock(int width, int height, int color) {
        super(width, height, color);
    }

    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public void onBallCollision(Vector2d collisionInfo, Vector2d oldCollisionInfo) {
        live -=1;
        Log.i(TAG, "ball touch");
    }

    @Override
    public boolean isAlive() {
        return live > 0;
    }
}

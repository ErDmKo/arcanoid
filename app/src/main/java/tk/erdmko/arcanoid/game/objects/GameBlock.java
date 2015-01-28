package tk.erdmko.arcanoid.game.objects;

import android.util.Log;

import tk.erdmko.arcanoid.game.Scene;

/**
 * Created by erdmko on 26.01.15.
 */
public class GameBlock extends Block implements BallCollision {

    public void setCallback(Scene.onBallHit callback) {
        this.callback = callback;
    }

    private Scene.onBallHit callback;

    public int getLive() {
        return live;
    }

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
        callback.run();
    }

    public boolean isAlive() {
        return live > 0;
    }
}

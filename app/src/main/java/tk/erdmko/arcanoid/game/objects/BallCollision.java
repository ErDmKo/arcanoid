package tk.erdmko.arcanoid.game.objects;

import tk.erdmko.arcanoid.game.Scene;

/**
 * Created by erdmko on 26.01.15.
 */
public interface BallCollision {
    public void onBallCollision(Vector2d collisionInfo, Vector2d oldCollisionInfo);
    public void setCallback(Scene.onBallHit callback);
}

package tk.erdmko.arcanoid.game.objects;

/**
 * Created by erdmko on 26.01.15.
 */
public interface BallCollision {
    public void onBallCollision(Vector2d collisionInfo, Vector2d oldCollisionInfo);
    public boolean isAlive();
}

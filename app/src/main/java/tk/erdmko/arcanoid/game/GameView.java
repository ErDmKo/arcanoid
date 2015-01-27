package tk.erdmko.arcanoid.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import tk.erdmko.arcanoid.game.objects.Ball;
import tk.erdmko.arcanoid.game.objects.Block;
import tk.erdmko.arcanoid.game.objects.BlockArray;
import tk.erdmko.arcanoid.game.objects.GameBlock;
import tk.erdmko.arcanoid.game.objects.Platform;
import tk.erdmko.arcanoid.game.objects.Vector2d;


/**
 * Created by erdmko on 21.01.15.
 */
public class GameView extends SurfaceView {
    private GameLoopThread gameLoop;

    public Scene getScene() {
        return scene;
    }

    private Scene scene;
    private static final String TAG = "gameView";

    private void createScene() {
        scene = new Scene();
        BlockArray blocks = new BlockArray(4, 4, new GameBlock(100, 30, Color.GREEN), BlockArray.TOP);
        blocks.setSceneSize(getWidth(), getHeight());
        scene.addObject(blocks);
        scene.addObject(new Block(10, getHeight(), new Vector2d(5, getHeight()/2), Color.RED));
        scene.addObject(new Block(10, getHeight(), new Vector2d(getWidth()-5, getHeight()/2), Color.RED));
        scene.addObject(new Block(getWidth(), 10, new Vector2d(getWidth()/2, 0), Color.RED));
        scene.addObject(new Platform(100, 10, new Vector2d(60, getHeight()-20), Color.BLUE));
        scene.addObject(new Ball(30, new Vector2d(getWidth()/2+320, getHeight()/2), Color.WHITE), true);
        scene.ready();
    }

    private void init_method(){
        Log.i(TAG, "constructor");
        SurfaceHolder holder = getHolder();
        gameLoop = new GameLoopThread(this);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scene.onTouch(event);
                return false;
            }
        });
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (scene==null) {
                    createScene();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoop.setActive(false);
                while (retry) {
                    try {
                        gameLoop.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_method();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scene.draw(canvas);
    }

    public void startLoop() {
        if (gameLoop.getState() == Thread.State.TERMINATED) {
            gameLoop = new GameLoopThread(this);
        }
        gameLoop.setActive(true);
        gameLoop.start();
    }

    public void stopLoop() {
        gameLoop.setActive(false);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

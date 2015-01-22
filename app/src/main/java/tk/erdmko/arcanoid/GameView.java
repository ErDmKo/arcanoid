package tk.erdmko.arcanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by erdmko on 21.01.15.
 */
public class GameView extends SurfaceView {
    private GameLoopThread gameLoop;
    private Scene scene;
    private static final String TAG = "gameView";

    private void crateScene(){
        scene = new Scene();
        scene.addObject(new Platform(100, 10, new Point(60, getHeight()-20), Color.RED));
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
                crateScene();
                gameLoop.setActive(true);
                gameLoop.start();
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
    protected void onDraw(Canvas canvas) {
        scene.draw(canvas);
    }
}

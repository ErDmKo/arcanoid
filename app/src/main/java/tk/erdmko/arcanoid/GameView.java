package tk.erdmko.arcanoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by erdmko on 21.01.15.
 */
public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoop;
    private static final String TAG = "gameView";
    private int x = 0;

    private void init_method(){
        Log.i(TAG, "constructor");
        holder = getHolder();
        gameLoop = new GameLoopThread(this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoop.setActive(true);
                Log.i(TAG, "createSurface");
                gameLoop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            public void surfaceDestroyed(SurfaceHolder holder){
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
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
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

    private Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        if (x < getWidth() - 10){
            x += 1;
        }
        canvas.drawBitmap(bmp, x, x, paint);
        Log.i(TAG, "onDraw coors " + x + " width " + getWidth());
    }
}

package tk.erdmko.arcanoid;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by erdmko on 21.01.15.
 */
public class GameLoopThread extends Thread {
    private GameView view;
    private Boolean active;
    private SurfaceHolder holder;
    private static final String TAG = "GameLoopThread";

    @Override
    public void run() {
        Log.i(TAG, "loop");
        holder = view.getHolder();
        while (active) {
            Canvas c = null;

            try {
                c = holder.lockCanvas();
                synchronized (holder) {
                    view.onDraw(c);
                    sleep(50);
                    Activity act = (Activity) view.getContext();
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.invalidate();
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (c != null){
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public GameLoopThread(GameView view) {
        this.view = view;
    }
}

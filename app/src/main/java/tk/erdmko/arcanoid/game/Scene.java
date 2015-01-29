package tk.erdmko.arcanoid.game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tk.erdmko.arcanoid.R;
import tk.erdmko.arcanoid.game.objects.BallCollision;
import tk.erdmko.arcanoid.game.objects.BlockArray;
import tk.erdmko.arcanoid.game.objects.GameBlock;
import tk.erdmko.arcanoid.game.objects.GameObject;
import tk.erdmko.arcanoid.game.objects.Vector2d;

/**
 * Created by erdmko on 21.01.15.
 */
public class Scene implements Serializable {
    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> forDel = new ArrayList<>();
    private static Scene ourInstance = new Scene();
    private int score = 0;
    public static final int WIN_STATUS = 2;
    public static final int GAME_OVER_STATUS = 1;
    public static final int PANGING_STATUS = 0;
    public int status = PANGING_STATUS;
    private transient Paint paintText;
    private Vector2d sceneSize;

    public void setR(Resources r) {
        this.r = r;
        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(r.getDimension(R.dimen.scoreFontSize));
    }

    private transient Resources r;
    private final static String TAG = "Scene";

    public static Scene getInstance() {
        return ourInstance;
    }

    public void addObject(BlockArray objList){
        objList.setSceneSize(sceneSize.x, sceneSize.y);
        for (List<GameObject> objLIst : objList.objects)
            for (GameObject obj: objLIst) {
                objects.add(obj);
            }
    }
    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void addObject(GameObject obj, boolean testCollision) {
        obj.setTestCollision(testCollision);
        objects.add(obj);
    }
    public void draw(Canvas c) {
        for (GameObject obj : objects) {
            obj.show(c);
        }
        for (GameObject obj: forDel) {
            objects.remove(obj);
        }
        c.drawText(r.getString(R.string.score_text) + " "+ score, 100, 30, paintText);
        if (status != PANGING_STATUS) {
            String statusStr = "";
            if (status == WIN_STATUS) {
                statusStr = r.getString(R.string.win_text);
            }
            if (status == GAME_OVER_STATUS) {
                statusStr = r.getString(R.string.game_over_text);
            }
            c.drawText(statusStr, 200, 500, paintText);
        }
    }

    public void onTouch(MotionEvent event) {
        for (GameObject obj : objects) {
            obj.onTouch(new Vector2d((int) event.getX(), (int) event.getY()));
        }
    }

    public void setSceneSize(int width, int height) {
        this.sceneSize = new Vector2d(width, height);
    }

    public class onBallHit implements Runnable, Serializable {
        private GameBlock obj;
        private onBallHit(GameBlock obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            Scene.this.score += 1;
            if (!obj.isAlive()){
                Scene.this.forDel.add(obj);
                int liveObj = 0;
                for (GameObject obj : objects) {
                    if (BallCollision.class.isInstance(obj)) {
                        liveObj++;
                    }
                }
                if (liveObj == 1) {
                    Scene.this.status = Scene.WIN_STATUS;
                }
            }
        }
    }
    public class gameOverTest implements Runnable, Serializable {
        private GameObject ball;

        public gameOverTest(GameObject ball) {
            this.ball = ball;
        }

        @Override
        public void run() {
            Vector2d position = ball.getPosition();
            if (Scene.this.sceneSize.y != 0 && position.y > Scene.this.sceneSize.y){
                Scene.this.status = Scene.GAME_OVER_STATUS;
            }
        }
    }
    public void ready() {

        for (final GameObject obj : objects) {
            if (obj.isTestCollision()) {
                obj.setCollisionsObjects(objects);
                obj.setMoveCallback(new gameOverTest(obj));
            }
            if (BallCollision.class.isInstance(obj)) {
                final BallCollision objColl = (BallCollision) obj;
                objColl.setCallback(new onBallHit((GameBlock) objColl));

            }
        }
    }
}

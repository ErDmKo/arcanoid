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
    private transient Paint paintText;

    public void setR(Resources r) {
        this.r = r;
    }

    private transient Resources r;
    private final static String TAG = "Scene";

    public static Scene getInstance() {
        return ourInstance;
    }

    public void addObject(BlockArray objList){
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
    }

    public void onTouch(MotionEvent event) {
        for (GameObject obj : objects) {
            obj.onTouch(new Vector2d((int) event.getX(), (int) event.getY()));
        }
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
            }
        }
    }
    public void ready() {

        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(r.getDimension(R.dimen.myFontSize));

        for (final GameObject obj : objects) {
            if (obj.isTestCollision()) {
                obj.setCollisionsObjects(objects);
            }
            if (BallCollision.class.isInstance(obj)) {
                final BallCollision objColl = (BallCollision) obj;
                objColl.setCallback(new onBallHit((GameBlock) objColl));

            }
        }
    }
}

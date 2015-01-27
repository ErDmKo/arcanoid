package tk.erdmko.arcanoid;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tk.erdmko.arcanoid.objects.BallCollision;
import tk.erdmko.arcanoid.objects.Block;
import tk.erdmko.arcanoid.objects.BlockArray;
import tk.erdmko.arcanoid.objects.GameObject;
import tk.erdmko.arcanoid.objects.Vector2d;

/**
 * Created by erdmko on 21.01.15.
 */
public class Scene implements Serializable {
    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> forDel = new ArrayList<>();
    private static Scene ourInstance = new Scene();
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
            if (BallCollision.class.isInstance(obj)) {
                if (!((BallCollision) obj).isAlive()){
                    forDel.add(obj);
                }
            }
        }
        if (forDel.size() > 0){
            for (GameObject obj : forDel){
               objects.remove(obj);
            }
            forDel.clear();
        }
    }

    public void onTouch(MotionEvent event) {
        for (GameObject obj : objects) {
            obj.onTouch(new Vector2d((int) event.getX(), (int) event.getY()));
        }
    }

    public void ready() {
        for (GameObject obj : objects) {
            if (obj.isTestCollision()) {
                obj.setCollisionsObjects(objects);
            }
        }
    }
}

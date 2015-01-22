package tk.erdmko.arcanoid;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import tk.erdmko.arcanoid.objects.GameObject;

/**
 * Created by erdmko on 21.01.15.
 */
public class Scene {
    private List <GameObject> objects = new ArrayList<>();
    private static Scene ourInstance = new Scene();

    public static Scene getInstance() {
        return ourInstance;
    }

    public void addObject(GameObject obj){
        objects.add(obj);
    }

    public void draw(Canvas c) {
        for (GameObject obj : objects) {
            obj.setCanvas(c);
            obj.show();
        }
    }

    public void onTouch(MotionEvent event) {
        for (GameObject obj : objects) {
            obj.onTouch(new Point((int)event.getX(), (int)event.getY()));
        }
    }
}

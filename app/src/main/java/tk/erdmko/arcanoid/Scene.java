package tk.erdmko.arcanoid;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erdmko on 21.01.15.
 */
public class Scene {
    private List <GameObject> objects = new ArrayList<GameObject>();
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
}

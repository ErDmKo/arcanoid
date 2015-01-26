package tk.erdmko.arcanoid.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erdmko on 21.01.15.
 */
public abstract class GameObject implements Cloneable {
    protected float coord_left, coord_top, coord_right, coord_bottom;
    protected Canvas canvas;
    protected Vector2d position;
    protected Paint paint = new Paint();
    private boolean testCollision = false;
    private static final String TAG = "GameObject";
    private List<GameObject> collisionsObjects = new ArrayList<>();


    protected GameObject(int width, int height, Vector2d center, int color) {
        position = center;
        this.coord_left = center.x - width/2;
        this.coord_right = this.coord_left + width;
        this.coord_top = center.y - height/2;
        this.coord_bottom = this.coord_top + height;
        setPaint(color);
    }
    protected GameObject(int coord_left, int coord_top, int coord_right, int coord_bottom, int color) {
        this.coord_left = coord_left;
        this.coord_top = coord_top;
        this.coord_right = coord_right;
        this.coord_bottom = coord_bottom;
        float centerX = coord_left + getWidth() / 2;
        float cenrerY = coord_top + getHeight() / 2;
        this.position = new Vector2d(centerX, cenrerY);
        setPaint(color);
    }

    public void setPosition(Vector2d position) {
        float width = getWidth();
        float height = getHeight();
        this.position = position;
        this.coord_left = position.x - width/2;
        this.coord_right = this.coord_left + width;
        this.coord_top = position.y - height/2;
        this.coord_bottom = this.coord_top + height;
    }

    public void setCollisionsObjects(List<GameObject> collisionsObjects) {
        this.collisionsObjects = collisionsObjects;
    }


    public void setTestCollision(boolean testCollision) {
        this.testCollision = testCollision;
    }

    public boolean isTestCollision() {
        return testCollision;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    private void setPaint(int color){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    protected float getWidth(){
        return coord_right - coord_left;
    }
    protected float getHeight(){
        return coord_bottom - coord_top;
    }

    protected abstract void draw();
    public abstract void onTouch(Vector2d v);

    public void show(){
        if (testCollision){
            testObjectsCollision();
        }
        draw();
    }

    private void testObjectsCollision() {
        for (GameObject obj: collisionsObjects){
            Vector2d collisionInfo = testCollision(obj);
            if (!this.equals(obj) && collisionInfo.len2() > 0){
                onCollision(obj, collisionInfo);
            }
        }
    }
    public GameObject clone() throws CloneNotSupportedException{
        GameObject obj = (GameObject)super.clone();
        obj.position = new Vector2d(position.x, position.y);
        obj.paint = new Paint();
        obj.paint.setStyle(Paint.Style.FILL);
        obj.paint.setColor(paint.getColor());
        obj.collisionsObjects = new ArrayList<>(collisionsObjects);
        return obj;
    }
    protected void onCollision(GameObject obj, Vector2d collisionInfo) {
        //Log.i(TAG, "collision !!!!! "+this.toString()+" "+obj.toString()+" "+collisionInfo.toString());
    }
    protected Vector2d getCollisionSize(GameObject obj){
        float x_collision;
        float y_collision;
        if (obj.coord_left < coord_left && obj.coord_right > coord_right) {
            x_collision = getWidth();
        } else {
            if (coord_left < obj.coord_left) {
                x_collision = coord_right - obj.coord_left;
            } else {
                x_collision = coord_left - obj.coord_right;
            }
        }
        if (obj.coord_top < coord_top && obj.coord_bottom > coord_bottom) {
            y_collision = getHeight();
        } else {
            if (coord_top < obj.coord_top) {
                y_collision = coord_bottom - obj.coord_top;
            } else {
                y_collision = coord_top - obj.coord_bottom;
            }
        }
        return new Vector2d(x_collision, y_collision);
    }
    public Vector2d testCollision(GameObject obj) {

        if (!this.equals(obj) && coord_left < obj.coord_right &&
            coord_right > obj.coord_left &&
            coord_top < obj.coord_bottom &&
            coord_bottom > obj.coord_top) {
            return getCollisionSize(obj);
        }
        return new Vector2d (0, 0);
    }
    protected void move(Vector2d v) {
        position.add(v);
        float width = getWidth();
        float height = getHeight();
        coord_left = position.x - width/2;
        coord_right = coord_left + width;
        coord_bottom = position.y + height/2;
        coord_top = coord_bottom - height;
    }
    protected void move(float dx, float dy) {
        this.coord_left += dx;
        this.coord_top += dy;
        this.coord_right += dx;
        this.coord_bottom += dy;
        this.position.add(dx, dy);
    }

}

package tk.erdmko.arcanoid.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erdmko on 25.01.15.
 */
public class BlockArray {
    public List<List<GameObject>> objects = new ArrayList<>();
    private Block blockPattern;
    public final static int TOP = 1;
    protected int sizeX;
    protected int sizeY;
    private Vector2d sceneSize;
    private int position;
    private static float padding = 30;
    private static final String TAG = "BlockArray";

    public BlockArray(int sizeX, int sizeY, Block block, int position) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.blockPattern = block;
        this.position = position;
    }

    private void addObjects(){
        float colsXSize = sceneSize.x/sizeX;
        float colsYSize = blockPattern.getHeight()/2 + padding;
        float colYPosition;
        if (position == TOP) {
            colYPosition = 0;
        } else {
            colYPosition = 0;
        }
        for (int i = 0; i< sizeY; i++) {
            float colXPosition = colsXSize/2;
            colYPosition += colsYSize+padding;
            List<GameObject> rowList = new ArrayList<>();
            for (int j = 0; j<sizeX; j++){

                try {
                    Block block = (Block) blockPattern.clone();
                    block.setPosition(new Vector2d(colXPosition, colYPosition));
                    rowList.add(block);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                colXPosition += colsXSize;
            }
        objects.add(rowList);
        }
    }

    public void setSceneSize(int width, int height) {
        this.sceneSize = new Vector2d(width, height);
        addObjects();
    }
}

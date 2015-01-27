package tk.erdmko.arcanoid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;


public class MainActivity extends Activity {
    private GameView gameView;
    private static final String TAG = "MainActivity";
    private static final String SCENE_STATE = "sceneInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.gameView);
        if (savedInstanceState != null) {
            gameView.setScene((Scene) savedInstanceState.getSerializable(SCENE_STATE));
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        gameView.setScene((Scene) savedInstanceState.getSerializable(SCENE_STATE));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putSerializable(SCENE_STATE, gameView.getScene());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        gameView.startLoop();

    }

    @Override
    public void onPause() {
        super.onPause();
        gameView.stopLoop();
    }
}

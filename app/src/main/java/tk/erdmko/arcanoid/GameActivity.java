package tk.erdmko.arcanoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import tk.erdmko.arcanoid.game.GameView;
import tk.erdmko.arcanoid.game.Scene;


public class GameActivity extends Activity {
    private GameView gameView;
    private static final String TAG = "GameActivity";
    public static final String SCENE_STATE = "sceneInfo";
    public static final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_activity);
        gameView = (GameView) findViewById(R.id.gameView);
        Intent in = getIntent();
        Scene scene = (Scene) in.getSerializableExtra(SCENE_STATE);
        if (scene != null) {
            gameView.setScene(scene);
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

    @Override
    public void finish() {
        Intent out = new Intent();
        out.putExtra(SCENE_STATE, gameView.getScene());
        setResult(RESULT_OK, out);
        super.finish();
    }
}

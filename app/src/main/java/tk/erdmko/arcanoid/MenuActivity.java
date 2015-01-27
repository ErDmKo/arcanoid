package tk.erdmko.arcanoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import tk.erdmko.arcanoid.game.Scene;


public class MenuActivity extends Activity {
    private static final int GAME_CODE = 1;
    private Scene gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        Button startButton = (Button) findViewById(R.id.new_game);
        Button resumeButton = (Button) findViewById(R.id.resume_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                startActivityForResult(intent, GAME_CODE);
            }
        });
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MenuActivity.this.gameState != null){
                    Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                    intent.putExtra(GameActivity.SCENE_STATE, MenuActivity.this.gameState);
                    startActivityForResult(intent, GAME_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_CODE) {
            if(resultCode == GameActivity.RESULT_OK){
                gameState = (Scene) data.getSerializableExtra(GameActivity.SCENE_STATE);
            }
        }
    }
}

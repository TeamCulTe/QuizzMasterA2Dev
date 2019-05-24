package com.imie.a2dev.teamculte.quizzmaster.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.GameMode;
import com.imie.a2dev.teamculte.quizzmaster.managers.GameDbManager;
import com.imie.a2dev.teamculte.quizzmaster.managers.GameModeDbManager;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.GameModeSpinnerAdapter;

/**
 * Main activity.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Stores the game intent tag.
     */
    public static final String GAME_INTENT = "Game";
    
    /**
     * Stores the game mode selection.
     */
    private Spinner spinnerGameMode;

    /**
     * Stores the spinner adapter.
     */
    private GameModeSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        this.init();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        
        switch (view.getId()) {
            case R.id.btn_new_game:
                Game game = new Game(this.adapter.getItem(this.spinnerGameMode.getSelectedItemPosition()));
                
                new GameDbManager(this).createSQLite(game);
                
                if (view.getId() == R.id.btn_new_game) {
//                    intent = new Intent(this, InitGameActivity.class);
//                    intent.putExtra(InitGameActivity.STEP, InitGameActivity.CREATE_PLAYER_STEP);
                } else {
//                    intent = new Intent(this, InitGameActivity.class);
//                    intent.putExtra(InitGameActivity.STEP, InitGameActivity.LOAD_PLAYER_STEP);
                }
                
//                intent.putExtra(GAME_INTENT, game);
                
                break;

            case R.id.btn_show_score:
//                intent = new Intent(this, ShowScoresActivity.class);
            
            case R.id.btn_settings:
//                intent = new Intent(this, SettingsActivity.class);
                
                break;
             
            default:
                return;
        }
        
//        this.startActivity(intent);
    }

    /**
     * Initializes the view components.
     */
    private void init() {
        this.findViewById(R.id.btn_new_game).setOnClickListener(this);
        this.findViewById(R.id.btn_show_score).setOnClickListener(this);
        this.findViewById(R.id.btn_settings).setOnClickListener(this);
        
        // TODO: Remove here
        new GameModeDbManager(this).createSQLite(new GameMode("Player vs IA", 1));
        new GameModeDbManager(this).createSQLite(new GameMode("Player vs Player", 2));
        
        this.adapter = new GameModeSpinnerAdapter(this, new GameModeDbManager(this).queryAllSQLite());
        this.spinnerGameMode = this.findViewById(R.id.spinner_game_mode);
        
        this.spinnerGameMode.setAdapter(this.adapter);
    }
    
}

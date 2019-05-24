package com.imie.a2dev.teamculte.quizzmaster.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGamesPlayersDifficultiesDbSchema.GAME;
import static com.imie.a2dev.teamculte.quizzmaster.views.MainActivity.GAME_INTENT;

/**
 * Activity containing the game and results flows.  
 */
public class GameActivity extends AppCompatActivity {
    /**
     * Stores the current game.
     */
    private transient Game game;

    /**
     * Gets the game attribute.
     * @return The Game value of game attribute.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Sets the game attribute.
     * @param newGame The new Game value to set.
     */
    public void setGame(Game newGame) {
        this.game = newGame;
    }
    
    /**
     * Replaces the content fragment.
     * @param fragment The fragment to push.
     */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();

        transaction.replace(R.id.frame_content, fragment);

        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_game);
        
        this.game = (Game) this.getIntent().getSerializableExtra(GAME_INTENT);
        
        this.replaceFragment(new PlayFragment());
    }
}

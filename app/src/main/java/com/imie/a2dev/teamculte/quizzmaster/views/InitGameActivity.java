package com.imie.a2dev.teamculte.quizzmaster.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;

import static com.imie.a2dev.teamculte.quizzmaster.views.MainActivity.GAME_INTENT;

/**
 * Activity managing the create player and question fragments.
 */
public class InitGameActivity extends AppCompatActivity {
    /**
     * Stores the step tag used to get the intent values.
     */
    public static final String STEP = "Step";
    
    /**
     * Stores the create player string used to define the current step.
     */
    public static final String CREATE_PLAYER_STEP = "CreatePlayer";

    /**
     * Stores the create player string used to define the current step.
     */
    public static final String CREATE_QUESTION_STEP = "CreateQuestion";

    /**
     * Stores the create player string used to define the current step.
     */
    public static final String LOAD_PLAYER_STEP = "LoadPlayer";

    /**
     * Stores the current game.
     */
    private Game game;

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
     * Checks if all the players has been set.
     * @return True if so else false.
     */
    public boolean hasAllPlayers() {
        return this.game.getMode().getPlayerNumber() == this.game.getPlayers().size();
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
        setContentView(R.layout.activity_init_game);
        Intent intent = this.getIntent();
        
        this.game = (Game) intent.getSerializableExtra(GAME_INTENT);
        
        Fragment fragment;
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        switch (intent.getStringExtra(STEP)) {
            case CREATE_PLAYER_STEP:
                fragment = new CreatePlayerFragment();
                
                break;
            case CREATE_QUESTION_STEP:
                fragment = new CreateQuestionFragment();
                
                break;
            case LOAD_PLAYER_STEP:
                fragment = new LoadPlayerFragment();
                
                break;
            default:
                
                return;
        }
        
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();
    }
}

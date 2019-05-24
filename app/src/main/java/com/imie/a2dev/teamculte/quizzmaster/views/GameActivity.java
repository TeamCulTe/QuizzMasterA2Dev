package com.imie.a2dev.teamculte.quizzmaster.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.imie.a2dev.teamculte.quizzmaster.R;

/**
 * Activity containing the game and results flows.  
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_game);
        
        this.replaceFragment(new PlayFragment());
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
}

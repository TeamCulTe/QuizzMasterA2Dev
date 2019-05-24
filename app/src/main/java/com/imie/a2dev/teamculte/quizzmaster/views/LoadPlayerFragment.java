package com.imie.a2dev.teamculte.quizzmaster.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.PlayerRecyclerViewAdapter;

/**
 * Fragment managing the player loading.
 */
public class LoadPlayerFragment extends Fragment {
    /**
     * Stores the player list.
     */
    private RecyclerView recyclerPlayerList;

    /**
     * Stores the recycler's adapter.
     */
    private PlayerRecyclerViewAdapter playerRecyclerViewAdapter;
    
    /**
     * Default constructor.
     */
    public LoadPlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_load_player, container, false);
    }

    /**
     * Initializes the fragment's view components.
     * @param view The fragment's view.
     */
    private void init(View view) {
        
    }
}

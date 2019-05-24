package com.imie.a2dev.teamculte.quizzmaster.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Player;
import com.imie.a2dev.teamculte.quizzmaster.managers.PlayerDbManager;
import com.imie.a2dev.teamculte.quizzmaster.views.adapters.PlayerRecyclerViewAdapter;

/**
 * Fragment managing the player loading.
 */
public class LoadPlayerFragment extends Fragment implements PlayerRecyclerViewAdapter.PlayerRowListener {
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
        
        View view = inflater.inflate(R.layout.fragment_load_player, container, false);
        
        this.init(view);
        
        return view;
    }

    /**
     * Initializes the fragment's view components.
     * @param view The fragment's view.
     */
    private void init(View view) {
        this.recyclerPlayerList = view.findViewById(R.id.recycler_player_list);
        
        this.recyclerPlayerList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        
        this.playerRecyclerViewAdapter =
                new PlayerRecyclerViewAdapter(new PlayerDbManager(this.getContext()).queryAllSQLite());
        
        this.playerRecyclerViewAdapter.setListener(this);
        
        this.recyclerPlayerList.setAdapter(this.playerRecyclerViewAdapter);
    }

    @Override
    public void playerRowSelected(Player player) {
        this.getRealActivity().getGame().getPlayers().add(player);
        Toast.makeText(this.getContext(), R.string.player_selected, Toast.LENGTH_LONG).show();
        if (this.getRealActivity().getGame().getPlayers().size() == this.getRealActivity().getGame().getMode().getPlayerNumber()) {
            this.getRealActivity().replaceFragment(new CreateQuestionFragment());
        }
    }

    /**
     * Gets the real activity managing the fragment.
     * @return The activity.
     */
    private InitGameActivity getRealActivity() {
        return (InitGameActivity) this.getActivity();
    }
}

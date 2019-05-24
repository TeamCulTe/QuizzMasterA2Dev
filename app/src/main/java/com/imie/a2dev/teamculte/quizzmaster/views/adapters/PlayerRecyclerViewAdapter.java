package com.imie.a2dev.teamculte.quizzmaster.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imie.a2dev.teamculte.quizzmaster.R;
import com.imie.a2dev.teamculte.quizzmaster.entities.Question;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Player;

import java.util.List;

/**
 * Custom recycler view adapter used to display the list of players.
 */
public final class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRecyclerViewAdapter.PlayerViewHolder> {
    /**
     * Stores the associated listener.
     */
    private PlayerRowListener listener;
    
    /**
     * Stores the list of players to display.
     */
    private List<Player> players;

    /**
     * Full filled constructor
     * @param players The players to set.
     */
    public PlayerRecyclerViewAdapter(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets the listener attribute.
     * @return The PlayerRowListener value of listener attribute.
     */
    public PlayerRowListener getListener() {
        return this.listener;
    }

    /**
     * Sets the listener attribute.
     * @param newListener The new PlayerRowListener value to set.
     */
    public void setListener(PlayerRowListener newListener) {
        this.listener = newListener;
    }
    
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player, parent, false);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(this.players.get(position));
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    /**
     * Inner ViewHolder class used to display the data into the rows.
     */
    public final class PlayerViewHolder extends RecyclerView.ViewHolder {
        /**
         * Stores the text displaying the player name.
         */
        private TextView txtPlayerName;

        /**
         * PlayerViewHolder's constructor.
         * @param itemView The generated view.
         */
        public PlayerViewHolder(View itemView) {
            super(itemView);

            this.txtPlayerName = itemView.findViewById(R.id.txt_player_name);
        }

        /**
         * Bind the data to the view.
         * @param player The player to display.
         */
        private void bind(Player player) {
            this.txtPlayerName.setText(player.getName());
            this.txtPlayerName.setOnClickListener(
                    view -> PlayerRecyclerViewAdapter.this.listener.playerRowSelected(player));
        }
    }

    /**
     * Listener interface used to notify when a player row is selected.
     */
    public interface PlayerRowListener {
        void playerRowSelected(Player player);
    }
}

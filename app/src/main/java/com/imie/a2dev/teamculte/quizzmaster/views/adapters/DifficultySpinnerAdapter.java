package com.imie.a2dev.teamculte.quizzmaster.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Difficulty;

import java.util.List;

/**
 * Custom adapter used to feed the difficulty spinners.
 */
public final class DifficultySpinnerAdapter extends ArrayAdapter {
    /**
     * Defines the default spinner items layout.
     */
    private static final int DEFAULT_LAYOUT = android.R.layout.simple_spinner_dropdown_item;

    /**
     * Stores the list of game modes to display.
     */
    private List<Difficulty> items;

    /**
     * Stores the associated context.
     */
    private Context context;

    /**
     * GameModeSpinnerAdapter's constructor.
     * @param context The associated context.
     * @param items The list of difficulties to display.
     */
    public DifficultySpinnerAdapter(Context context, List<Difficulty> items) {
        super(context, DEFAULT_LAYOUT);
        
        this.items = items;
        this.context = context;
    }
    
    @NonNull
    @Override
    public TextView getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(this.context, DEFAULT_LAYOUT, null);
        }
        
        TextView text = convertView.findViewById(android.R.id.text1);
        
        text.setText(this.items.get(position).getName());
        
        return text;
    }

    @NonNull
    @Override
    public TextView getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(this.context, DEFAULT_LAYOUT, null);
        }

        TextView text = convertView.findViewById(android.R.id.text1);

        text.setText(this.items.get(position).getName());

        return text;
    }
    
    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Difficulty getItem(int i) {
        return this.items.get(i);
    }
}

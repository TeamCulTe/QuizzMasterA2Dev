package com.imie.a2dev.teamculte.quizzmaster.entities.dbentities;

import android.database.Cursor;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractDifficultyDbSchema.ID;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractDifficultyDbSchema.NAME;

/**
 * Clas defining the difficulties of the game.
 */
public final class Difficulty extends DbEntity {
    /**
     * Stores the associated name.
     */
    private String name;

    /**
     * Default constructor
     */
    public Difficulty() {
    }

    /**
     * Nearly full filled constructor.
     * @param name The name to set.
     */
    public Difficulty(String name) {
        this.name = name;
    }

    /**
     * Full filled constructor.
     * @param id The id to set.
     * @param name The name to set.
     */
    public Difficulty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Cursor constructor.
     * @param result The cursor used to set.
     */
    public Difficulty(Cursor result) {
        this.init(result, true);
    }

    /**
     * Gets the name attribute.
     * @return The String value of name attribute.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name attribute.
     * @param newName The new String value to set.
     */
    public void setName(String newName) {
        this.name = newName;
    }
    
    @Override
    public void init(Cursor result, boolean close) {
        if (result.isBeforeFirst()) {
            result.moveToNext();
        }
        
        this.id = result.getInt(result.getColumnIndex(ID));
        this.name = result.getString(result.getColumnIndex(NAME));
        
        if (close) {
            result.close();
        }
    }
}
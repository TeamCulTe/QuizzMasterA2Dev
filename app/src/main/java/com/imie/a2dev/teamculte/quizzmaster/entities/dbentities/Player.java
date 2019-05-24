package com.imie.a2dev.teamculte.quizzmaster.entities.dbentities;

import android.database.Cursor;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema.ID;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema.NAME;

/**
 * Class defining a player of the game.
 */
public final class Player extends DbEntity {
    /**
     * Stores the user name.
     */
    private String name;

    /**
     * Store the user points.
     */
    private int points;

    /**
     * Defines the player's game difficulty.
     */
    private Difficulty difficulty;
    
    /**
     * Default constructor.
     */
    public Player() {
    }

    /**
     * Nearly full filled constructor.
     * @param name The name to set.
     */
    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    /**
     * Nearly full filled constructor.
     * @param name The name to set.
     * @param difficulty The difficulty to set.
     */
    public Player(String name, Difficulty difficulty) {
        this.name = name;
        this.points = 0;
        this.difficulty = difficulty;
    }
    
    /**
     * Nearly full filled constructor.
     * @param id The id to set.
     * @param name The name to set.
     * @param difficulty The difficulty to set.
     * @param points The points to set.
     */
    public Player(int id, String name, Difficulty difficulty, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.difficulty = difficulty;
    }

    /**
     * Cursor constructor.
     * @param result The cursor used to set.
     */
    public Player(Cursor result) {
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

    /**
     * Gets the points attribute.
     * @return The int value of points attribute.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Sets the points attribute.
     * @param newPoints The new int value to set.
     */
    public void setPoints(int newPoints) {
        this.points = newPoints;
    }

    /**
     * Gets the difficulty attribute.
     * @return The Difficulty value of difficulty attribute.
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets the difficulty attribute.
     * @param newDifficulty The new Difficulty value to set.
     */
    public void setDifficulty(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
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
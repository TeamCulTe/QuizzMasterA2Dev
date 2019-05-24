package com.imie.a2dev.teamculte.quizzmaster.entities.dbentities;

import android.database.Cursor;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameModeDbSchema.ID;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameModeDbSchema.NAME;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameModeDbSchema.PLAYER_NB;

/**
 * Enum defining the different game modes (player vs player / player vs IA).
 */
public final class GameMode extends DbEntity {
    /**
     * Stores the game mode name.
     */
    private String name;

    /**
     * Defines the number of players for the mode.
     */
    private int playerNumber;

    /**
     * Default constructor
     */
    public GameMode() {
    }

    /**
     * Nearly full filled constructor.
     * @param name The name to set.
     * @param playerNumber The number of player to set.
     */
    public GameMode(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
    }

    /**
     * Full filled constructor.
     * @param id The id to set.
     * @param name The name to set.
     * @param playerNumber The number of player to set.
     */
    public GameMode(int id, String name, int playerNumber) {
        super(id);
        this.name = name;
        this.playerNumber = playerNumber;
    }

    /**
     * Cursor constructor.
     * @param result The cursor used to set.
     */
    public GameMode(Cursor result) {
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
     * Gets the playerNumber attribute.
     * @return The int value of playerNumber attribute.
     */
    public int getPlayerNumber() {
        return this.playerNumber;
    }

    /**
     * Sets the playerNumber attribute.
     * @param newPlayerNumber The new int value to set.
     */
    public void setPlayerNumber(int newPlayerNumber) {
        this.playerNumber = newPlayerNumber;
    }

    @Override
    public void init(Cursor result, boolean close) {
        if (result.isBeforeFirst()) {
            result.moveToNext();
        }

        this.id = result.getInt(result.getColumnIndex(ID));
        this.name = result.getString(result.getColumnIndex(NAME));
        this.playerNumber = result.getInt(result.getColumnIndex(PLAYER_NB));
        
        if (close) {
            result.close();
        }
    }
}
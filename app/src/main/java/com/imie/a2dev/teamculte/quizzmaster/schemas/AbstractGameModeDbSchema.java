package com.imie.a2dev.teamculte.quizzmaster.schemas;

/**
 * Abstract class defining the database schema of the game mode entity.
 */
public abstract class AbstractGameModeDbSchema {
    /**
     * Define the table name.
     */
    public static final String TABLE = "GameModel";
    
    /**
     * Define the name of the id field.
     */
    public static final String ID = "id_game_mode";

    /**
     * Define the name of the name field.
     */
    public static final String NAME = "name_game_mode";

    /**
     * Define the name of the player number field.
     */
    public static final String PLAYER_NB = "player_number";

    /**
     * Define the size of the name field.
     */
    private static final String NAME_SIZE = "50";

    /**
     * Stores the create table statement.
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + ID + " INTEGER PRIMARY " +
            "KEY, " + NAME + " TEXT(" + NAME_SIZE + ") UNIQUE NOT NULL, " + PLAYER_NB + " INT NOT NULL);";
}

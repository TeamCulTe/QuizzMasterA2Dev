package com.imie.a2dev.teamculte.quizzmaster.schemas;

/**
 * Abstract class defining the database schema of the game entity.
 */
public abstract class AbstractGameDbSchema {
    /**
     * Define the table name.
     */
    public static final String TABLE = "Game";
    
    /**
     * Define the name of the id field.
     */
    public static final String ID = "id_game";

    /**
     * Define the name of the id field.
     */
    public static final String GAME_MODE = AbstractGameModeDbSchema.ID;

    /**
     * Stores the create table statement.
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + ID + " INTEGER PRIMARY " +
            "KEY, " + GAME_MODE + " INTEGER NOT NULL, CONSTRAINT " + TABLE + "_" + AbstractGameModeDbSchema.TABLE + 
            "_FK FOREIGN KEY(" + GAME_MODE + ") REFERENCES " + AbstractGameModeDbSchema.TABLE + "(" + GAME_MODE + "));";
}

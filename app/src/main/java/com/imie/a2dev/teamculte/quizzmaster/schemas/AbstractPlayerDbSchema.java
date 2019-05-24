package com.imie.a2dev.teamculte.quizzmaster.schemas;

/**
 * Abstract class defining the database schema of the player entity.
 */
public abstract class AbstractPlayerDbSchema {
    /**
     * Define the table name.
     */
    public static final String TABLE = "Player";
    
    /**
     * Define the name of the id field.
     */
    public static final String ID = "id_player";

    /**
     * Define the name of the name field.
     */
    public static final String NAME = "name_player";

    /**
     * Define the size of the name field.
     */
    private static final String NAME_SIZE = "50";

    /**
     * Stores the create table statement.
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + ID + " INTEGER PRIMARY " +
            "KEY, " + NAME + " TEXT(" + NAME_SIZE + ") UNIQUE NOT NULL);";
}

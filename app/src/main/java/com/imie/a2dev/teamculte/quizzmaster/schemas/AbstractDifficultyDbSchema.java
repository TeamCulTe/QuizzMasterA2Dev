package com.imie.a2dev.teamculte.quizzmaster.schemas;

/**
 * Abstract class defining the database schema of the difficulty entity.
 */
public abstract class AbstractDifficultyDbSchema {
    /**
     * Define the table name.
     */
    public static final String TABLE = "Difficulty";

    /**
     * Define the name of the id field.
     */
    public static final String ID = "id_difficulty";

    /**
     * Define the name of the name field.
     */
    public static final String NAME = "name_difficulty";

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

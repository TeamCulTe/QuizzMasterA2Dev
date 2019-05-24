package com.imie.a2dev.teamculte.quizzmaster.schemas;

/**
 * Abstract class defining the database schema of the join tables between player, game and difficulty.
 */
public abstract class AbstractGamesPlayersDifficultiesDbSchema {
    /**
     * Define the table name.
     */
    public static final String TABLE = "Games_Players_Difficulties";
    
    /**
     * Define the name of the player id field.
     */
    public static final String PLAYER = AbstractPlayerDbSchema.ID;

    /**
     * Define the name of the game id field.
     */
    public static final String GAME = AbstractGameDbSchema.ID;

    /**
     * Define the name of the difficulty id field.
     */
    public static final String DIFFICULTY = AbstractDifficultyDbSchema.ID;

    /**
     * Stores the create table statement.
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + PLAYER + " INTEGER NOT " +
            "NULL, " + GAME + " INTEGER NOT NULL, " + DIFFICULTY + " INTEGER NOT NULL, CONSTRAINT " + TABLE + "_" + 
            AbstractPlayerDbSchema.TABLE + "_FK FOREIGN KEY(" + PLAYER + ") REFERENCES " + AbstractPlayerDbSchema.TABLE 
            + "(" + PLAYER + "), CONSTRAINT " + TABLE + "_" + AbstractGameDbSchema.TABLE + "_FK FOREIGN KEY(" + GAME + 
            ") REFERENCES " + AbstractGameDbSchema.TABLE + "(" +  GAME + "), CONSTRAINT " + TABLE + "_" + 
            AbstractDifficultyDbSchema.TABLE + "_FK FOREIGN KEY(" + DIFFICULTY + ") REFERENCES " + 
            AbstractDifficultyDbSchema.TABLE + "(" + DIFFICULTY +  "));";
}

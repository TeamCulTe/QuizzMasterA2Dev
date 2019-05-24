package com.imie.a2dev.teamculte.quizzmaster.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractDifficultyDbSchema;
import com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameDbSchema;
import com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameModeDbSchema;
import com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGamesPlayersDifficultiesDbSchema;
import com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema;

/**
 * Class used to manage database structure (tables, upgrades...).
 */
public final class DbHandlerUtils extends SQLiteOpenHelper {
    /**
     * Defines the drop statement.
     */
    private static final String DROP_STATEMENT = "DROP TABLE IF EXISTS %s;";

    /**
     * DBHandler's constructor.
     * @param context The associated application context.
     * @param name The name of the database file.
     * @param factory If creating cursor objects, null if default.
     * @param version The database version number.
     */
    public DbHandlerUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format(DROP_STATEMENT, AbstractDifficultyDbSchema.TABLE));
        db.execSQL(String.format(DROP_STATEMENT, AbstractGameDbSchema.TABLE));
        db.execSQL(String.format(DROP_STATEMENT, AbstractGameModeDbSchema.TABLE));
        db.execSQL(String.format(DROP_STATEMENT, AbstractGamesPlayersDifficultiesDbSchema.TABLE));
        db.execSQL(String.format(DROP_STATEMENT, AbstractPlayerDbSchema.TABLE));

        this.onCreate(db);
    }

    /**
     * Creates the database.
     * @param db The database object.
     */
    private void create(SQLiteDatabase db) {
        // Difficulty table
        db.execSQL(AbstractDifficultyDbSchema.CREATE_TABLE);
        // Game table
        db.execSQL(AbstractGameDbSchema.CREATE_TABLE);
        // GameMode table
        db.execSQL(AbstractGameModeDbSchema.CREATE_TABLE);
        // GamesPlayersDifficulties table
        db.execSQL(AbstractGamesPlayersDifficultiesDbSchema.CREATE_TABLE);
        // Player table
        db.execSQL(AbstractPlayerDbSchema.CREATE_TABLE);
    }
}

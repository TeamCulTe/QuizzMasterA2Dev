package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Player;

import java.util.ArrayList;
import java.util.List;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema.ID;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema.NAME;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractPlayerDbSchema.TABLE;
import static com.imie.a2dev.teamculte.quizzmaster.utils.TagUtils.SQLITE_TAG;

/**
 * Class managing the Player entities into the database.
 */
public final class PlayerDbManager extends DbManager {
    /**
     * Full filled constructor initializing the handler attribute and opening the database.
     * @param context The associated context.
     */
    public PlayerDbManager(Context context) {
        super(context);

        this.table = TABLE;
        this.ids = new String[]{ID};
    }

    /**
     * From a java entity creates the associated entity into the database.
     * @param entity The model to store into the database.
     * @return true if success else false.
     */
    public boolean createSQLite(@NonNull Player entity) {
        try {
            ContentValues data = new ContentValues();
            
            data.put(NAME, entity.getName());
            
            if (entity.getId() != 0) {
                data.put(this.ids[0], entity.getId());
                this.database.insertOrThrow(this.table, null, data);
            } else {
                entity.setId((int) this.database.insertOrThrow(this.table, null, data));
            }

            return true;
        } catch (SQLiteException e) {
            Log.e(SQLITE_TAG, e.getMessage());

            return false;
        }
    }

    /**
     * From an id, returns the associated java entity.
     * @param id The id of entity to load from the database.
     * @return The loaded entity if exists else null.
     */
    public Player loadSQLite(int id) {
        Cursor result = this.loadCursorSQLite(id);

        if (result == null || result.getCount() == 0) {
            return null;
        }

        return new Player(result);
    }

    /**
     * From a player name, returns the associated java entity.
     * @param name The name of entity to load from the database.
     * @return The loaded entity if exists else null.
     */
    public Player loadSQLite(String name) {
        Cursor result = this.database.rawQuery(String.format(SIMPLE_QUERY_ALL, this.table, NAME), new String[]{name});

        if (result == null || result.getCount() == 0) {
            return null;
        }

        return new Player(result);
    }

    /**
     * From a java entity updates the associated entity into the database.
     * @param entity The model to update into the database.
     * @return true if success else false.
     */
    public boolean updateSQLite(@NonNull Player entity) {
        try {
            ContentValues data = new ContentValues();
            String whereClause = String.format("%s = ?", this.ids[0]);
            String[] whereArgs = new String[]{String.valueOf(entity.getId())};

            data.put(NAME, entity.getName());

            return this.database.update(this.table, data, whereClause, whereArgs) != 0;
        } catch (SQLiteException e) {
            Log.e(SQLITE_TAG, e.getMessage());

            return false;
        }
    }

    /**
     * Query all the entities from database.
     * @return The list of entities loaded.
     */
    public List<Player> queryAllSQLite() {
        Player player;
        List<Player> players = new ArrayList<>();
        Cursor cursor = this.database.rawQuery(String.format(DbManager.QUERY_ALL, this.table), null);

        while (cursor.moveToNext()) {
            player = new Player();

            player.init(cursor, false);
            players.add(player);
        }

        cursor.close();

        return players;
    }
}

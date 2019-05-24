package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;

import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameDbSchema.GAME_MODE;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameDbSchema.ID;
import static com.imie.a2dev.teamculte.quizzmaster.schemas.AbstractGameDbSchema.TABLE;
import static com.imie.a2dev.teamculte.quizzmaster.utils.TagUtils.SQLITE_TAG;

/**
 * Class managing the Game entities into the database.
 */
public final class GameDbManager extends DbManager {
    /**
     * Full filled constructor initializing the handler attribute and opening the database.
     * @param context The associated context.
     */
    public GameDbManager(Context context) {
        super(context);

        this.table = TABLE;
        this.ids = new String[]{ID};
    }

    /**
     * From a java entity creates the associated entity into the database.
     * @param entity The model to store into the database.
     * @return true if success else false.
     */
    public boolean createSQLite(@NonNull Game entity) {
        try {
            ContentValues data = new ContentValues();
            
            data.put(GAME_MODE, entity.getMode().getId());

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
    public Game loadSQLite(int id) {
        Cursor result = this.loadCursorSQLite(id);

        if (result == null || result.getCount() == 0) {
            return null;
        }

        return new Game(result);
    }
}

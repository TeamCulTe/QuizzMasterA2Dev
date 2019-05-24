package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.imie.a2dev.teamculte.quizzmaster.utils.DbHandlerUtils;
import com.imie.a2dev.teamculte.quizzmaster.utils.TagUtils;

import static com.imie.a2dev.teamculte.quizzmaster.utils.TagUtils.SQLITE_TAG;

/**
 * Abstract class extended by all DBManager classes (used to manage entities into databases).
 */
public abstract class DbManager {
    /**
     * Defines the database version.
     */
    private final int VERSION = 1;

    /**
     * Defines the count param and json value from MySQL query alias.
     */
    private final String COUNT = "count";

    /**
     * Stores a query all string for entities. 
     */
    protected static final String QUERY_ALL = "SELECT * FROM %s";

    /**
     * Stores a query all string for entity with only one id. 
     */
    protected static final String SIMPLE_QUERY_ALL = "SELECT * FROM %s WHERE %s = ?";

    /**
     * Stores the count query for entity with only one id.
     */
    protected static final String SIMPLE_QUERY_COUNT = "SELECT COUNT(*) as %s FROM %s";

    /**
     * Defines the database file name.
     */
    protected static String dbFileName = "quizz_master.db";

    /**
     * Stores the DBManager's managed table.
     */
    protected String table;

    /**
     * Stores the DBManager's managed entities id labels.
     */
    protected String[] ids;

    /**
     * Stores the managers database in order to manage.
     */
    protected SQLiteDatabase database;

    /**
     * Stores the associated context in order to use other managers from managers classes.
     */
    private Context context;

    /**
     * Stores the associated DBHandler to get the database object.
     */
    private DbHandlerUtils handler;

    /**
     * DBManager's full filled constructor initializing the handler attribute and opening the database.
     * @param context The associated context.
     */
    protected DbManager(Context context) {
        this.handler = new DbHandlerUtils(context, dbFileName, null, this.VERSION);
        this.context = context;

        this.open();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();

        super.finalize();
    }

    /**
     * Returns the database file name.
     * @return The name of the file.
     */
    public static String getDbFileName() {
        return dbFileName;
    }

    /**
     * Sets the database file name.
     * @param newDbFileName The new name to set.
     */
    public static void setDbFileName(String newDbFileName) {
        dbFileName = newDbFileName;
    }

    /**
     * Gets the database attribute.
     * @return The SQLiteDatabase value of database attribute.
     */
    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    /**
     * Gets the handler attribute.
     * @return The DBHandler value of handler attribute.
     */
    public final DbHandlerUtils getHandler() {
        return this.handler;
    }

    /**
     * Sets the handler attribute.
     * @param newHandler The new DBHandler value to set.
     */
    public final void setHandler(DbHandlerUtils newHandler) {
        this.handler = newHandler;
    }

    /**
     * Gets the context attribute.
     * @return The Context value of context attribute.
     */
    public final Context getContext() {
        return this.context;
    }

    /**
     * Sets the context attribute.
     * @param newContext The new Context value to set.
     */
    public final void setContext(Context newContext) {
        this.context = newContext;
    }

    /**
     * Gets the ids attribute.
     * @return The String value of ids attribute.
     */
    public String[] getIds() {
        return this.ids;
    }

    /**
     * Gets the table attribute.
     * @return The String value of table attribute.
     */
    public final String getTable() {
        return this.table;
    }

    /**
     * Queries the number of entries for a specific table.
     * @return The number of entries or -1 if an error occurred.
     */
    public final int countSQLite() {
        try {
            String[] selectArgs = {};
            String query = String.format(SIMPLE_QUERY_COUNT, COUNT, this.table);
            Cursor result = this.database.rawQuery(query, selectArgs);

            result.moveToNext();

            int count = result.getInt(result.getColumnIndexOrThrow(COUNT));

            result.close();

            return count;
        } catch (SQLiteException e) {
            Log.e(SQLITE_TAG, e.getMessage());

            return -1;
        }
    }

    /**
     * From an id given in parameter, deletes the associated entity in the database.
     * @param ids The ids of the entity to delete.
     * @return true if success else false.
     */
    public boolean deleteSQLite(int ... ids) {
        try {
            StringBuilder builder = new StringBuilder();
            String[] whereArgs = new String[ids.length];

            for (int i = 0; i < ids.length; i++) {
                builder.append(this.ids[i]);
                builder.append(" = ?");

                whereArgs[i] = String.valueOf(ids[i]);

                if (i < (ids.length - 1)) {
                    builder.append(" AND ");
                }
            }

            return this.database.delete(this.table, builder.toString(), whereArgs) != 0;
        } catch (SQLiteException e) {
            Log.e(SQLITE_TAG, e.getMessage());

            return false;
        }
    }

    /**
     * From an id, returns the associated java entity as a cursor (simple entities).
     * @param id The id of entity to load from the database.
     * @return The cursor of the loaded entity.
     */
    protected Cursor loadCursorSQLite(int id) {
        try {
            String[] selectArgs = {String.valueOf(id)};
            String query = String.format(SIMPLE_QUERY_ALL, this.table, this.ids[0]);

            return this.database.rawQuery(query, selectArgs);
        } catch (SQLiteException e) {
            Log.e(SQLITE_TAG, e.getMessage());

            return null;
        }
    }

    /**
     * Opens and set the SQLiteDatabase.
     */
    private void open() {
        if (this.database == null) {
            this.database = this.handler.getWritableDatabase();
        }
    }
    
    /**
     * Closes the database.
     */
    private void close() {
        this.handler.close();
        this.database.close();
    }
}

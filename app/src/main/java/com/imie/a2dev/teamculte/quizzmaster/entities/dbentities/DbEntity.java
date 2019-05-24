package com.imie.a2dev.teamculte.quizzmaster.entities.dbentities;

import android.database.Cursor;

import java.io.Serializable;

/**
 * Abstract entity extended by all the entities stored into database.
 */
public abstract class DbEntity  implements Serializable {
    /**
     * Stores the entity id.
     */
    protected int id;

    /**
     * Default constructor.
     */
    public DbEntity() {
    }

    /**
     * Default constructor.
     */
    public DbEntity(int id) {
        this.id = id;
    }

    /**
     * Gets the id attribute.
     * @return The $field.typeName value of id attribute.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the id attribute.
     * @param newId The new $field.typeName value to set.
     */
    public void setId(int newId) {
        this.id = newId;
    }

    /**
     * Initializes the entity from a cursor object.
     * @param result The cursor used to init the entity.
     * @param close The boolean defining if cursor needs to be closed or not to set.
     */
    public abstract void init(Cursor result, boolean close);
}

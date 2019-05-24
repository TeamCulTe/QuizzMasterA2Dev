package com.imie.a2dev.teamculte.quizzmaster.entities;

/**
 * Class defining a category of question in the app.
 */
public final class Category {
    /**
     * Stores the category name.
     */
    private String name;
    
    /**
     * Default constructor
     */
    public Category() {
    }

    /**
     * Full filled constructor.
     * @param name The name to set.
     */
    public Category(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name attribute.
     * @return The String value of name attribute.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name attribute.
     * @param newName The new String value to set.
     */
    public void setName(String newName) {
        this.name = newName;
    }
}
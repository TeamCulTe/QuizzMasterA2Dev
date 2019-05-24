package com.imie.a2dev.teamculte.quizzmaster.entities;

/**
 * Class defining a clue for a specific question in the app.
 */
public final class Clue {
    /**
     *
     */
    private String value;
    
    /**
     * Default constructor
     */
    public Clue() {
    }

    /**
     * Full filled constructor.
     * @param value The value to set.
     */
    public Clue(String value) {
        this.value = value;
    }

    /**
     * Gets the value attribute.
     * @return The String value of value attribute.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value attribute.
     * @param newValue The new String value to set.
     */
    public void setValue(String newValue) {
        this.value = newValue;
    }
}
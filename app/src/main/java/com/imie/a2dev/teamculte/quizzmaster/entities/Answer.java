package com.imie.a2dev.teamculte.quizzmaster.entities;

/**
 * Class defining an answer in the app.
 */
public final class Answer {
    /**
     * Stores the answer value.
     */
    private String value;
    
    /**
     * Default constructor.
     */
    public Answer() {
    }

    /**
     * Full filled constructor.
     * @param value The value to set.
     */
    public Answer(String value) {
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
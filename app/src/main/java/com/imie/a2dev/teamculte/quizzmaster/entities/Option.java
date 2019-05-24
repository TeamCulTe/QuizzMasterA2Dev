package com.imie.a2dev.teamculte.quizzmaster.entities;

/**
 * Class defining an option of the game.
 */
public final class Option {
    /**
     * Stores the key of the option.
     */
    private String key;

    /**
     * Stores the associated value.
     */
    private String value;
    
    /**
     * Default constructor.
     */
    public Option() {
    }

    /**
     * Full filled constructor.
     * @param key The key to set.
     * @param value The value to set.
     */
    public Option(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key attribute.
     * @return The String value of key attribute.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key attribute.
     * @param newKey The new String value to set.
     */
    public void setKey(String newKey) {
        this.key = newKey;
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
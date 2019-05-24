package com.imie.a2dev.teamculte.quizzmaster.entities;

import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defining a question in the game.
 */
public final class Question {
    /**
     * Defines the number of possible answer per question.
     */
    public static final int ANSWER_NB = 4;

    /**
     * Stores the label of the question.
     */
    private String value;
    
    /**
     * Stores the index of the correct answer.
     */
    private int correctAnswerIndex;

    /**
     * Stores the difficulty of the question.
     */
    private Difficulty difficulty;

    /**
     * Stores the associated clue.
     */
    private Clue clue;

    /**
     * Stores the possible answers.
     */
    private List<Answer> answers = new ArrayList<>();

    /**
     * Stores the path of the image picked for the question.
     */
    private String imagePath;

    /**
     * Default constructor.
     */
    public Question() {
    }
    
    /**
     * Full filled constructor.
     */
    public Question(String value, int correctAnswerIndex, Difficulty difficulty, Clue clue, List<Answer> answers,
                    String imagePath) {
        this.value = value;
        this.correctAnswerIndex = correctAnswerIndex;
        this.difficulty = difficulty;
        this.clue = clue;
        this.answers = answers;
        this.imagePath = imagePath;
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

    /**
     * Gets the correctAnswerIndex attribute.
     * @return The int value of correctAnswerIndex attribute.
     */
    public int getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    /**
     * Sets the correctAnswerIndex attribute.
     * @param newCorrectAnswerIndex The new int value to set.
     */
    public void setCorrectAnswerIndex(int newCorrectAnswerIndex) {
        this.correctAnswerIndex = newCorrectAnswerIndex;
    }

    /**
     * Gets the difficulty attribute.
     * @return The Difficulty value of difficulty attribute.
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets the difficulty attribute.
     * @param newDifficulty The new Difficulty value to set.
     */
    public void setDifficulty(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
    }

    /**
     * Gets the clue attribute.
     * @return The Clue value of clue attribute.
     */
    public Clue getClue() {
        return this.clue;
    }

    /**
     * Sets the clue attribute.
     * @param newClue The new Clue value to set.
     */
    public void setClue(Clue newClue) {
        this.clue = newClue;
    }

    /**
     * Gets the answers attribute.
     * @return The List<Answer> value of answers attribute.
     */
    public List<Answer> getAnswers() {
        return this.answers;
    }

    /**
     * Sets the answers attribute.
     * @param newAnswers The new List<Answer> value to set.
     */
    public void setAnswers(List<Answer> newAnswers) {
        this.answers = newAnswers;
    }

    /**
     * Gets the imagePath attribute.
     * @return The String value of imagePath attribute.
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Sets the imagePath attribute.
     * @param newImagePath The new String value to set.
     */
    public void setImagePath(String newImagePath) {
        this.imagePath = newImagePath;
    }

    /**
     * Gets the answer to the question.
     * @return The correct answer.
     */
    public Answer getCorrectAnswer() {
        return this.answers.get(this.correctAnswerIndex);
    }
}
package com.imie.a2dev.teamculte.quizzmaster.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class used to test the associated entity.
 */
public final class QuestionTest {
    /**
     * Stores the tested entity.
     */
    private Question question;

    @Before
    public void setUp() throws Exception {
        this.question = new Question();
    }

    @Test
    public void testGetCorrectAnswer() {
        String valuePrefix = "value";
        List<Answer> answers = new ArrayList<>();
        int correctAnswerIndex = 1;

        for (int i = 0; i < Question.ANSWER_NB; i++) {
            answers.add(new Answer(valuePrefix + String.valueOf(i)));
        }

        this.question.setAnswers(answers);
        this.question.setCorrectAnswerIndex(correctAnswerIndex);

        assertEquals(answers.get(correctAnswerIndex), this.question.getCorrectAnswer());
    }
}

package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Abstract test class extended by all the DbManagerTest classes.
 */
@RunWith(AndroidJUnit4.class)
public abstract class CommonDbManagerTest {
    /**
     * Defines the test database name.
     */
    private static final String TEST_DB = "quizz_master.test.db";

    /**
     * Stores the context used to manage database.
     */
    protected Context context = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() throws Exception {
        DbManager.setDbFileName(TEST_DB);
    }

    @After
    public void tearDown() throws Exception {
        this.context.deleteDatabase(TEST_DB);
    }

    /**
     * Initializes the test entity.
     */
    protected abstract void initTestEntity();
}

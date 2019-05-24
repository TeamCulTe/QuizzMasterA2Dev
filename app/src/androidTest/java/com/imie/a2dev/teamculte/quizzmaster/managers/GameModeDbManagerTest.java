package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.support.test.runner.AndroidJUnit4;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.GameMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Abstract test class extended by all the DbManagerTest classes.
 */
@RunWith(AndroidJUnit4.class)
public final class GameModeDbManagerTest extends CommonDbManagerTest {
    /**
     * Stores the name used for tests.
     */
    protected static final String TEST_NAME = "testGameMode";

    /**
     * Stores the player number used for tests.
     */
    protected static final int TEST_PLAYER_NUMBER = 2;
    
    /**
     * Stores the associated DbManager.
     */
    private GameModeDbManager manager;

    /**
     * Stores the test entity.
     */
    private GameMode testEntity;

    /**
     * Gets the testEntity attribute.
     * @return The GameMode value of testEntity attribute.
     */
    public GameMode getTestEntity() {
        return this.testEntity;
    }

    /**
     * Sets the testEntity attribute.
     * @param newTestEntity The new GameMode value to set.
     */
    public void setTestEntity(GameMode newTestEntity) {
        this.testEntity = newTestEntity;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.manager = new GameModeDbManager(this.context);
        this.initTestEntity();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateSQLite() {
        assertTrue(this.testEntity.getId() != 0);
        assertEquals(this.testEntity.getName(), TEST_NAME);
        assertEquals(this.testEntity.getPlayerNumber(), TEST_PLAYER_NUMBER);
    }

    @Test
    public void testLoadSQLite() {
        GameMode loaded = this.manager.loadSQLite(this.testEntity.getId());

        assertNotNull(loaded);
        assertEquals(this.testEntity.getId(), loaded.getId());
        assertEquals(this.testEntity.getName(), loaded.getName());
        assertEquals(this.testEntity.getPlayerNumber(), loaded.getPlayerNumber());
    }

    @Test
    public void testUpdateSQLite() {
        String newName = "newName";
        int newPlayerNb = 1;

        this.testEntity.setName(newName);
        this.manager.updateSQLite(this.testEntity);

        this.testEntity = this.manager.loadSQLite(this.testEntity.getId());

        assertNotNull(this.testEntity);
        assertEquals(newName, this.testEntity.getName());
        assertEquals(newPlayerNb, this.testEntity.getPlayerNumber());
    }

    @Test
    public void testDeleteSQLite() {
        this.manager.deleteSQLite(this.testEntity.getId());

        assertNull(this.manager.loadSQLite(this.testEntity.getId()));
    }

    @Test
    public void testCountSQLite() {
        assertEquals(1, this.manager.countSQLite());
    }

    @Test
    public void testQueryAllSQLite() {
        GameMode secMode = new GameMode("SecMode", 1);

        this.manager.createSQLite(secMode);

        assertEquals(2, this.manager.queryAllSQLite().size());
    }

    @Override
    protected void initTestEntity() {
        GameMode mode = new GameMode(TEST_NAME, TEST_PLAYER_NUMBER);

        this.manager.createSQLite(mode);

        this.testEntity = mode;
    }
}

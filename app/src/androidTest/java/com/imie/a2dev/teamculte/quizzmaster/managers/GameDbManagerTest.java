package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.support.test.runner.AndroidJUnit4;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Game;
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
public final class GameDbManagerTest extends CommonDbManagerTest {
    /**
     * Stores the associated DbManager.
     */
    private GameDbManager manager;

    /**
     * Stores the test entity.
     */
    private Game testEntity;

    /**
     * Gets the testEntity attribute.
     * @return The Game value of testEntity attribute.
     */
    public Game getTestEntity() {
        return this.testEntity;
    }

    /**
     * Sets the testEntity attribute.
     * @param newTestEntity The new Game value to set.
     */
    public void setTestEntity(Game newTestEntity) {
        this.testEntity = newTestEntity;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.manager = new GameDbManager(this.context);
        this.initTestEntity();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateSQLite() {
        assertTrue(this.testEntity.getId() != 0);
    }

    @Test
    public void testLoadSQLite() {
        Game loaded = this.manager.loadSQLite(this.testEntity.getId());

        assertNotNull(loaded);
        assertEquals(this.testEntity.getId(), loaded.getId());
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

    @Override
    protected void initTestEntity() {
        GameMode mode = new GameMode(GameModeDbManagerTest.TEST_NAME, GameModeDbManagerTest.TEST_PLAYER_NUMBER);

        new GameModeDbManager(this.context).createSQLite(mode);
        
        Game game = new Game(mode);
        
        this.manager.createSQLite(game);

        this.testEntity = game;
    }
}

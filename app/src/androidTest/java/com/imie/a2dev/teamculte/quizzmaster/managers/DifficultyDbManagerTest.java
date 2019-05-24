package com.imie.a2dev.teamculte.quizzmaster.managers;

import android.support.test.runner.AndroidJUnit4;
import com.imie.a2dev.teamculte.quizzmaster.entities.dbentities.Difficulty;
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
public final class DifficultyDbManagerTest extends CommonDbManagerTest {
    /**
     * Stores the name used for tests.
     */
    protected static final String TEST_NAME = "testDifficulty";
    
    /**
     * Stores the associated DbManager.
     */
    private DifficultyDbManager manager;

    /**
     * Stores the test entity.
     */
    private Difficulty testEntity;

    /**
     * Gets the testEntity attribute.
     * @return The Difficulty value of testEntity attribute.
     */
    public Difficulty getTestEntity() {
        return this.testEntity;
    }

    /**
     * Sets the testEntity attribute.
     * @param newTestEntity The new Difficulty value to set.
     */
    public void setTestEntity(Difficulty newTestEntity) {
        this.testEntity = newTestEntity;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.manager = new DifficultyDbManager(this.context);
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
    }
    
    @Test
    public void testLoadSQLite() {
        Difficulty loaded = this.manager.loadSQLite(this.testEntity.getId());
        
        assertNotNull(loaded);
        assertEquals(this.testEntity.getId(), loaded.getId());
        assertEquals(this.testEntity.getName(), loaded.getName());
    }
    
    @Test
    public void testUpdateSQLite() {
        String newName = "newName";

        this.testEntity.setName(newName);
        this.manager.updateSQLite(this.testEntity);

        this.testEntity = this.manager.loadSQLite(this.testEntity.getId());

        assertNotNull(this.testEntity);
        assertEquals(newName, this.testEntity.getName());
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
        Difficulty secDifficulty = new Difficulty("SecDifficulty");
        
        this.manager.createSQLite(secDifficulty);
        
        assertEquals(2, this.manager.queryAllSQLite().size());
    }

    @Override
    protected void initTestEntity() {
        Difficulty difficulty = new Difficulty(TEST_NAME);
        
        this.manager.createSQLite(difficulty);
        
        this.testEntity = difficulty;
    }
}

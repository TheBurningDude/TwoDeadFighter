/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nasib
 */
public class CollisionProcessingServiceTest {
    
    private static Entity e1;
    private static Entity e2;
    private static CollisionProcessingService cp;
    
   
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class CollisionProcessingService.
     */
    @Test
    public void testProcess() {
        e1 = new Entity();
        e2 = new Entity();
        cp = new CollisionProcessingService();
        e1.setEntityPosition(10, 5);
        e2.setEntityPosition(12, 5);
        e1.setType(EntityType.PLAYER);
        e2.setType(EntityType.ENEMY);
        e1.setRadius(5);
        e2.setRadius(5);
        
        boolean rsl = cp.isOverLappingCircleRect(e1, e2);
        
        assertTrue(rsl);
    }
    
}

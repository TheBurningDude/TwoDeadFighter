/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.NodeType;
import dk.four.group.common.services.IGamePluginService;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> origin/master
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
<<<<<<< HEAD
/**
 *
 * @author nasib
=======
import org.netbeans.junit.MockServices;
/**
 *
 * @author Sidon K. K
>>>>>>> origin/master
 */
public class PathFinderTest {
    
    public PathFinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    
    public static Entity createDummyEntity(){
        Entity e = new Entity();
        e.setEntityPosition(0, 0);
        return e;
            
        
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
     * Test of findPath method, of class PathFinder.
     */
    @Test
    public void testFindPath() {
         System.out.println("findPath");
        Entity s = createDummyEntity();
        Entity e = createDummyEntity();
        PathFinder instance = new PathFinder(320, 320, 64,64);
        instance.SetGridNode((int) s.getEntityPosition().getX(), (int) s.getEntityPosition().getY(), NodeType.START);
        instance.SetGridNode((int) e.getEntityPosition().getX(), (int) e.getEntityPosition().getY(), NodeType.END);
        int expResult = 1;
        int result = instance.findPath();
        assertEquals(expResult, result);
    }
    
    
    public static final class MapMock implements IGamePluginService{

        private Map<String, Entity> world;
        
        
        @Override
        public void start(GameData gameData, Map<String, Entity> world) {
            this.world = (Map<String, Entity>) world.values();
        }

        @Override
        public void stop(GameData gamedata) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
<<<<<<< HEAD
=======
    
>>>>>>> origin/master
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

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
public class NodeTest {
    
    public NodeTest() {
    }
    
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
     * Test of getX method, of class Node.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Node instance = new Node(0, 0);
        float expResult = 0;
        float result = instance.getX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getY method, of class Node.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Node instance = new Node(0, 0);
        float expResult = 0;
        float result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getF method, of class Node.
     */
    @Test
    public void testGetF() {
        System.out.println("getF");
        Node instance = new Node(0, 0);
        float expResult = 0;
        float result = instance.getF();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getG method, of class Node.
     */
    @Test
    public void testGetG() {
        System.out.println("getG");
        Node instance = new Node(0, 0);
        float expResult = 0;
        float result = instance.getG();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of getH method, of class Node.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        Node instance = new Node(0, 0);
        float expResult = 0;
        float result = instance.getH();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setF method, of class Node.
     */
    @Test
    public void testSetF() {
        System.out.println("setF");
        float F = 10;
        Node instance = new Node(0, 0);
        instance.setF(F);
        assertEquals(F,instance.getF(), 0.0);
    }

    /**
     * Test of setG method, of class Node.
     */
    @Test
    public void testSetG() {
        System.out.println("setG");
        float G = 15;
        Node instance = new Node(0, 0);
        instance.setG(G);
        assertEquals(G,instance.getG(), 0.0);
    }

    /**
     * Test of setH method, of class Node.
     */
    @Test
    public void testSetH() {
        System.out.println("setH");
        float H = 50;
        Node instance = new Node(0, 0);
        instance.setH(H);
        assertEquals(H,instance.getH(), 0.0);
    }
    
}

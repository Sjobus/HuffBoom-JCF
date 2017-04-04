/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf41_huffboom_groepc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sibe
 */
public class JCF41_HuffBoom_GroepCTest {
    
    public JCF41_HuffBoom_GroepCTest() {
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
     * Test of main method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JCF41_HuffBoom_GroepC.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Frequentie method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testFrequentie() {
        System.out.println("Frequentie");
        char[] karacters = null;
        HashMap expResult = null;
        HashMap result = JCF41_HuffBoom_GroepC.Frequentie(karacters);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MaakKnoop method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testMaakKnoop() {
        System.out.println("MaakKnoop");
        HashMap<Character, Integer> freq = null;
        PriorityQueue<HuffKnoop> expResult = null;
        PriorityQueue<HuffKnoop> result = JCF41_HuffBoom_GroepC.MaakKnoop(freq);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BouwBoom method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testBouwBoom() {
        System.out.println("BouwBoom");
        PriorityQueue<HuffKnoop> pq = null;
        HuffKnoop expResult = null;
        HuffKnoop result = JCF41_HuffBoom_GroepC.BouwBoom(pq);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCharacterCode method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testGetCharacterCode() {
        System.out.println("getCharacterCode");
        HuffKnoop root = null;
        String path = "";
        HashMap<Character, String> characterCode = null;
        HashMap expResult = null;
        HashMap result = JCF41_HuffBoom_GroepC.getCharacterCode(root, path, characterCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Compress method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testCompress() {
        System.out.println("Compress");
        ArrayList<String> text = null;
        HashMap<Character, String> codeMap = null;
        String expResult = "";
        String result = JCF41_HuffBoom_GroepC.Compress(text, codeMap);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LezenBestand method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testLezenBestand() {
        System.out.println("LezenBestand");
        String bestand = "";
        ArrayList<String> expResult = null;
        ArrayList<String> result = JCF41_HuffBoom_GroepC.LezenBestand(bestand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LezenBoomObject method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testLezenBoomObject() {
        System.out.println("LezenBoomObject");
        HuffKnoop expResult = null;
        HuffKnoop result = JCF41_HuffBoom_GroepC.LezenBoomObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decompress method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testDecompress() {
        System.out.println("decompress");
        HuffKnoop root = null;
        String msg = "";
        String expResult = "";
        String result = JCF41_HuffBoom_GroepC.decompress(root, msg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

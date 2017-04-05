/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf41_huffboom_groepc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import static org.hamcrest.CoreMatchers.is;
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
     
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JCF41_HuffBoom_GroepC.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of Frequentie method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testFrequentie() {
        String s = "test1";
        System.out.println("Frequentie");
        char[] karacters = s.toCharArray();
        HashMap<Character, Integer> expResult = new HashMap<>();
        expResult.put('t',2);
        expResult.put('e',1);
        expResult.put('s',1);
        expResult.put('1',1);
        HashMap<Character, Integer> result = JCF41_HuffBoom_GroepC.Frequentie(karacters);        
        assertEquals(expResult.size(),result.size());
        for(Map.Entry<Character,Integer> entry : expResult.entrySet())
        {
            //System.out.println("entry: " + entry.getKey() + " value: " + entry.getValue());
            assertEquals(entry.getValue(),result.get(entry.getKey()));
        }
    }

    /**
     * Test of MaakKnoop method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testMaakKnoop() {
        System.out.println("MaakKnoop");
        HashMap<Character, Integer> freq = new HashMap<>();
        freq.put('t',2);
        freq.put('e',1);
        freq.put('s',1);
        freq.put('1',1);
        PriorityQueue<HuffKnoop> expResult = new PriorityQueue<>();
        for(char c : freq.keySet())
        {
            HuffKnoop knoop = new HuffKnoop(c,freq.get(c));
            System.out.println("entry: " + knoop.character + " freq: " + knoop.freq );
            expResult.add(knoop);
        }
        PriorityQueue<HuffKnoop> result = JCF41_HuffBoom_GroepC.MaakKnoop(freq);
        while(result.size() > 1)
        {  
            HuffKnoop knoopResult = result.poll();
            HuffKnoop expKnoop = expResult.poll();
            assertEquals(knoopResult.character,expKnoop.character); 
            assertEquals(knoopResult.freq,expKnoop.freq);
        }
        
        
        
    }

    /**
     * Test of BouwBoom method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testBouwBoom() {
        System.out.println("BouwBoom");
        HashMap<Character, Integer> freq = new HashMap<>();
        freq.put('t',2);
        freq.put('e',1);
        freq.put('s',1);
        freq.put('1',1);
        PriorityQueue<HuffKnoop> pq = new PriorityQueue<HuffKnoop>();
        for(char c : freq.keySet())
        {
            HuffKnoop knoop = new HuffKnoop(c,freq.get(c));            
            pq.add(knoop);
        }
        HuffKnoop expResult = bouwboom(pq);
        for(char c : freq.keySet())
        {
            HuffKnoop knoop = new HuffKnoop(c,freq.get(c));            
            pq.add(knoop);
        }
        HuffKnoop result = JCF41_HuffBoom_GroepC.BouwBoom(pq);
        assertEquals(expResult.character, result.character);
        assertEquals(expResult.freq,result.freq);
        
    }

    public static HuffKnoop bouwboom(PriorityQueue<HuffKnoop> pq)
    {
        while(pq.size() > 1)
        {
            HuffKnoop left = pq.poll();
            HuffKnoop right = pq.poll();
            HuffKnoop parentKnoop = new HuffKnoop(left, right);
            //System.out.println("Made Knoop LeftChild: " + parentKnoop.leftChild.character + parentKnoop.leftChild.freq + " Rightchild: " + parentKnoop.rightChild.character + parentKnoop.rightChild.freq);
            pq.add(parentKnoop);
        }
        return pq.poll();
    }
    /**
     * Test of getCharacterCode method, of class JCF41_HuffBoom_GroepC.
     
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
    }*/

    /**
     * Test of Compress method, of class JCF41_HuffBoom_GroepC.
     
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
    }*/

    /**
     * Test of LezenBestand method, of class JCF41_HuffBoom_GroepC.
     
    @Test
    public void testLezenBestand() {
        System.out.println("LezenBestand");
        String bestand = "";
        ArrayList<String> expResult = null;
        ArrayList<String> result = JCF41_HuffBoom_GroepC.LezenBestand(bestand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of LezenBoomObject method, of class JCF41_HuffBoom_GroepC.
     
    @Test
    public void testLezenBoomObject() {
        System.out.println("LezenBoomObject");
        HuffKnoop expResult = null;
        HuffKnoop result = JCF41_HuffBoom_GroepC.LezenBoomObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of decompress method, of class JCF41_HuffBoom_GroepC.
     
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
    }*/
    
}

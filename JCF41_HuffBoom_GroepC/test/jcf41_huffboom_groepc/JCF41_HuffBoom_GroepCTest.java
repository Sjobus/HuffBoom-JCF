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
import static jcf41_huffboom_groepc.JCF41_HuffBoom_GroepC.getCharacterCode;
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
    
    public static HuffKnoop deRootKnoop;
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
        System.out.println("TestMaakKnoop");
        //Vull map voor methode input
        HashMap<Character, Integer> freq = new HashMap<>();
        freq.put('t',2);
        freq.put('e',1);
        freq.put('s',1);
        freq.put('1',1);
        //Maak het verwachte resultaat
        PriorityQueue<HuffKnoop> expResult = new PriorityQueue<>();
        for(char c : freq.keySet())
        {
            HuffKnoop knoop = new HuffKnoop(c,freq.get(c));            
            expResult.add(knoop);
        }
        //Voer methode uit om resultaat te krijgen
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
        deRootKnoop = JCF41_HuffBoom_GroepC.BouwBoom(pq);
       
        assertEquals(expResult.character, deRootKnoop.character);
        assertEquals(expResult.freq,deRootKnoop.freq);
        
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
     */
    @Test
    public void testGetCharacterCode() {
        System.out.println("getCharacterCode");
        HuffKnoop root = deRootKnoop;        
        String path = "";
        HashMap<Character, String> characterCodeExp = new HashMap<>();
        HashMap<Character, String> characterCodeResult = new HashMap<>();
        HashMap<Character, String> expResult = getCharacterCode(root, path, characterCodeExp);
        HashMap<Character, String> result = JCF41_HuffBoom_GroepC.getCharacterCode(root, path, characterCodeResult);
        for(Map.Entry<Character,String> entry : result.entrySet())
        {
            assertEquals(entry.getValue(),expResult.get(entry.getKey()));
        }
        
    }
    
    public static HashMap getCharacterCode(HuffKnoop root, String path, HashMap<Character, String> characterCode)
    {
        if (root.leftChild != null && root.rightChild != null)
        {
           characterCode.putAll(getCharacterCode(root.leftChild, path + "0",characterCode));
           
           characterCode.putAll(getCharacterCode(root.rightChild, path + "1",characterCode));
        }
        else 
        {
            //System.out.println(root.character + " : " + path);
            characterCode.put(root.character, path);
        }
        return characterCode;
    }

    /**
     * Test of Compress method, of class JCF41_HuffBoom_GroepC.
     */
    @Test
    public void testCompress() {
        System.out.println("Compress");
        ArrayList<String> text = new ArrayList<>();
        text.add("test1");

        HashMap<Character, String> codeMap = new HashMap<>();
        codeMap = getCharacterCode(deRootKnoop,"", codeMap); 
        String expResult = "010000010110";
        String result = JCF41_HuffBoom_GroepC.Compress(text, codeMap);
        assertEquals(expResult, result);
    }

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

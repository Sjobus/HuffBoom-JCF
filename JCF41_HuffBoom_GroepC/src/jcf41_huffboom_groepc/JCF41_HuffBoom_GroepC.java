/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf41_huffboom_groepc;

import java.util.*;

/**
 *
 * @author Sibe
 */
public class JCF41_HuffBoom_GroepC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String zin = "Hello world";
        char[] chars = zin.toCharArray();
        HashMap<Character, Integer> freq = new HashMap<>();
        PriorityQueue<HuffKnoop> knoopList = new PriorityQueue<>();
        HashMap<Character, String> characterCodeMap = new HashMap<>();
        freq = Frequentie(chars);
        knoopList = MaakKnoop(freq);
        HuffKnoop rootKnoop = BouwBoom(knoopList);
        getCharacterCode(rootKnoop,"", characterCodeMap);
        System.out.println(freq);
        /*while(!knoopList.isEmpty())
        {
            System.out.println(knoopList.poll());
        }*/
        
    }
    
    /**
     * Kijkt hoe vaak een char voor komt in de char[].
     * @param karacters
     * @return HashMap<Character,Integer>
     */
    public static HashMap Frequentie(char[] karacters)
    {
        HashMap<Character, Integer> frequentie = new HashMap<>();
        for(char c : karacters)
        {
            Integer n = frequentie.get(c);
            if(n == null)
            {
                n = 1;
            }
            else
            {
                n++;
            }
            frequentie.put(c,n);
        }
        
        
        return frequentie;
    }
    
    /**
     * Maakt een huffknoop vna de char en zijn frequentie en zet ze in een PriorityQueue
     * @param HashMap<Character, Integer> freq
     * @return PriorityQueue<HuffKnoop>
     */
    public static PriorityQueue<HuffKnoop>MaakKnoop(HashMap<Character, Integer> freq)
    {
        //List<HuffKnoop> knopen = new ArrayList<>();
        PriorityQueue pq = new PriorityQueue<HuffKnoop>();
        for(char c : freq.keySet())
        {
            HuffKnoop knoop = new HuffKnoop(c,freq.get(c));
            pq.add(knoop);
        }
        
        return pq;
    }
    
    /**
     * maak een huffboom.
     * pak 2 knopen uit de priorityqueue en maak er een nieuwe knoop van. zet deze weer in de queue.
     * @param pq
     * @return 
     */
    public static HuffKnoop BouwBoom(PriorityQueue<HuffKnoop> pq)
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
     * geeft alle chars hun binaire code. Hij kijkt in de root node of deze nog een leftChild en rightChild heeft.
     * Als hij het wel heeft roept hij zicht zelf weer aan voor zowel de linker als de rechter child.
     * De linker child krijgt aan zijn path de waarde 0 en de rechter de waarde 1.
     * dit blijft hij door lopen tot dat de root note geen children meer heeft.
     * @param HuffKnoop root 
     * @param String path
     * @param HashMap<Character, String> characterCode
     * @return HashMap<Character, String> characterCode
     */
    public static HashMap getCharacterCode(HuffKnoop root, String path, HashMap<Character, String> characterCode)
    {
        if (root.leftChild != null && root.rightChild != null)
        {
           characterCode.putAll(getCharacterCode(root.leftChild, path + "0",characterCode));
           
           characterCode.putAll(getCharacterCode(root.rightChild, path + "1",characterCode));
        }
        else 
        {
            System.out.println(root.character + " : " + path);
            characterCode.put(root.character, path);
        }
        return characterCode;
    }
}

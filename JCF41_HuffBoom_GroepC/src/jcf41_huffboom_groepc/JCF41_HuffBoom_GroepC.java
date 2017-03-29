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
        freq = Frequentie(chars);
        knoopList = MaakKnoop(freq);
        System.out.println(freq);
        System.out.println(knoopList.toString());
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
     * 
     * @param freq
     * @return List<HuffKnoop>
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
    
    
}

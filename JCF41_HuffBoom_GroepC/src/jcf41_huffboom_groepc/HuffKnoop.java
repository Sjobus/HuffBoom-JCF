/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf41_huffboom_groepc;

/**
 *
 * @author Sibe
 */
public class HuffKnoop implements Comparable<HuffKnoop> {
    public char character;
    public Integer freq;
    public HuffKnoop leftChild, rightChild;
    
    public HuffKnoop(char character, int freq)
    {
        this.character = character;
        this.freq = freq;
    }
    
    public HuffKnoop(HuffKnoop left, HuffKnoop right)
    {
        character = '*';
        freq = left.freq + right.freq;
        leftChild = left;
        rightChild = right;
    }
    
    @Override
    public int compareTo(HuffKnoop other) {
        return Integer.compare(this.freq, other.freq);
    }
    @Override
    public String toString(){
        return character + ": " + freq;
    }
}

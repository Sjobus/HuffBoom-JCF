/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf41_huffboom_groepc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 *
 * @author Sibe
 */
public class JCF41_HuffBoom_GroepC {

    private final static String alice = "..\\AliceInWonderLand.txt";
    private final static String binaryCode ="..\\BinaryFile.dat";
    private final static String boomCode ="..\\Huffboom.txt";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> zinnen = LezenBestand(alice);
        char[] chars = zinnen.toString().toCharArray();
        HashMap<Character, Integer> freq = new HashMap<>();
        PriorityQueue<HuffKnoop> knoopList = new PriorityQueue<>();
        HashMap<Character, String> characterCodeMap = new HashMap<>();
        freq = Frequentie(chars);
        knoopList = MaakKnoop(freq);
        HuffKnoop rootKnoop = BouwBoom(knoopList);
        SchrijfBoomObject(rootKnoop);
        getCharacterCode(rootKnoop,"", characterCodeMap);        
        rootKnoop = LezenBoomObject();
        SchrijfBinairyFile(Compress(zinnen, characterCodeMap));        
        System.out.println(decompress(rootKnoop,LezenBestand(binaryCode).get(0)));
       
       
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
    
    /**
     * 
     * @param text
     * @param codeMap
     * @return 
     */
    public static String Compress(ArrayList<String> text, HashMap<Character, String> codeMap)
    {
        StringBuilder sb = new StringBuilder();
        
        
        for(char c : text.toString().toCharArray())
        {
            sb.append(codeMap.get(c));
        }
        //System.out.println(sb.toString());
        return sb.toString();
    } 
    
    /**
     * Leest de file van Alice in Wonderland en zet ze in een ArrayList
     * @return ArrayList<String> zinnen
     */
    public static ArrayList<String> LezenBestand(String bestand)
    {
        ArrayList<String> zinnen = new ArrayList<>();
        BufferedReader reader = null;
        try
        {
           File file = new File(bestand);
           reader = new BufferedReader(new FileReader(file));
           
           String line;
           while((line = reader.readLine()) != null)
           {
               zinnen.add(line);
           }
        }
        catch(IOException e)
        {
            System.out.println("Error bij het lezen: " + e.getMessage());
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                System.out.println("Error bij het sluiten van reader: " + e.getMessage());
            }
        }
        return zinnen;
    }
    
   /**
    * Het uitlezen van het bestand waar de huffknoopboom in staat
    * @return HuffKnoop de boom
    */
   public static HuffKnoop LezenBoomObject()
   {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        HuffKnoop boom = null;
        try
        {

            fis = new FileInputStream(boomCode);
            in = new ObjectInputStream(fis);
            boom = (HuffKnoop)in.readObject();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error bij lezen Binary file");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
                if (fis != null)
                {
                    fis.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return boom;
    }
    /**
     * Het wegschrijven van de huffboom in een bestand als object
     * @param HuffKnoop de boom
     */
    private static void SchrijfBoomObject(HuffKnoop knoop) 
    {
        ObjectOutputStream binWriter = null;
        OutputStream buffer = null;
        OutputStream fileStream = null;
        try
        {
            fileStream = new FileOutputStream(boomCode);
            buffer = new BufferedOutputStream(fileStream);
            binWriter = new ObjectOutputStream(buffer);
            binWriter.writeObject(knoop);
            System.out.println("Klaar met schrijven van boomobject in file.");

        }
        catch (IOException e)
        {
            System.out.println("error Binbuf : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (binWriter != null)
                {
                    binWriter.close();
                }
                if (binWriter != null)
                {
                    binWriter.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 
     * @param binary 
     */
    private static void SchrijfBinairyFile(String binary)
    {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(binaryCode);
            dos = new DataOutputStream(fos);

            dos.writeBytes(binary);

        }
        catch (IOException e)
        {
            System.out.println("Error Binaryfilewriter : " + e.getMessage());
        }
        finally
        {
            // waarom schrijft ie niet als je hem niet .closed()?
            try
            {
                if (dos != null)
                {
                    dos.close();
                }
                if (fos != null)
                {
                    fos.close();
                }

            }
            catch (IOException e)
            {
                System.out.println("Error Binaryfilewriter closer : " + e.getMessage());
            }
        }
    }
    
    /**
     * 
     * @param root
     * @param msg
     * @return 
     */
    public static String decompress(HuffKnoop root, String msg)
    {
        String finalMsg = "";
        HuffKnoop realRoot = root;
        
        for (char charazard : msg.toCharArray())
        {
                if (charazard == '0')
                {
                    //System.out.println("ga naar links");
                    
                    root = root.leftChild;

                }
                else if (charazard == '1')
                {
                    //System.out.println("ga naar rechts");
                    
                    root = root.rightChild;
                }
            
            if (root.leftChild == null && root.rightChild == null)
            {
                //System.out.println("is blaadje: " + root.karakter);
                finalMsg += root.character;
                root = realRoot;
            }            
        }
        
        return finalMsg;
    }
}

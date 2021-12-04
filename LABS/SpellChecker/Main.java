import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main
{
   
    // Return a scanner for the given file with the given name.
    private static Scanner openFileWithScanner(String fileName)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Unable to open " + fileName);
            System.exit(1);
            return null;
        }
        return scanner;
    }

   public static void main(String[] args)
   {
        // Open a scanner for the dictionary
        if(args.length < 1)
        {
            System.err.println("No dictionary file name given.");
        }
        final Scanner dictionaryScanner = openFileWithScanner(args[0]);

        // Get the scanner for the input
        final Scanner inputScanner;
        if(args.length < 2)
        {
            inputScanner = new Scanner(new BufferedInputStream(System.in));
        }
        else
        {
            inputScanner = openFileWithScanner(args[1]);
        }

        /* Add your code here */
        ArrayList<String> dict = new ArrayList<>();
        int maxSize = 0;
        String temporary;
        while(dictionaryScanner.hasNextLine()){
            temporary = dictionaryScanner.nextLine();
            if(temporary.length() > maxSize) maxSize = temporary.length();
            dict.add(temporary);
        }
        Collections.sort(dict);
        //System.out.println(dict.size());
        ArrayList<String> possibleInputs = new ArrayList<>();
        while(inputScanner.hasNext()){
            String word = inputScanner.next();
            if(!dict.contains(word)){
                possibleInputs.add(word);
            }
        }
        for(String word : possibleInputs){
           //System.out.println("testing word: " + word);
            StringBuilder sb = new StringBuilder();
            sb.append(word);
            sb.append(" : ");
            if(word.length() <= maxSize + 1){
               ArrayList<String> possibleAlts = alterHelper(word);
               
               int counter = 0;
               for(String alt: possibleAlts){
                   if(dict.contains(alt)){
                       counter++;
                       sb.append(alt);
                       sb.append(", ");
                   }
               }
               if(counter > 0) sb.delete(sb.length() - 2, sb.length());
               else sb.append("*no alternates found*");
               
            }else{
               sb.append("*no alternates found*");
            }
            System.out.println(sb.toString());
        }
   }
   public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static char[] alph = alphabet.toCharArray();
   /* Add Methods as needed */
   public static ArrayList<String> alterHelper(String word){
        int wL = word.length();
        ArrayList<String> alts = new ArrayList<>();
        for (int i = 0; i < wL + 1; i++) {
            StringBuilder sb;
            for (char character : alph) {
                sb = new StringBuilder(word);
                sb.insert(i, character);
                alts.add(sb.toString());
                if(i < wL){
                    sb = new StringBuilder(word);
                    sb.setCharAt(i, character);
                    alts.add(sb.toString());
                }
            }
            if(i < wL - 1){
                sb = new StringBuilder(word);
                char temp = sb.charAt(i);
                sb.setCharAt(i, word.charAt(i + 1));
                sb.setCharAt(i + 1, temp);
                alts.add(sb.toString());
            }
            if(i < wL){
                sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                alts.add(sb.toString());
            }
        }
        Collections.sort(alts);
        return alts;
   }
}

package LeastSignificantRadix;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    // Exit code for errors
    public static final int FILE_ERROR = 1;
    public static final int WORD_LENGTH_ERROR = 2;
    public static final int ALPHABET_ERROR = 3;
    
    /**
     * Read the name of the alphabet to use;
     * Reads in a sequence of fixed-length strings from standard input;
     * LSD radix sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args args[0] is the optional name of a file to read for input.
     */
    public static void main(String[] args)
    {
        // A scanner to read the words from either a file or System.in
        Scanner scanner;
        if(args.length > 0)
        {
            try
            {
                scanner = new Scanner(new FileReader(args[0]));
            }
            catch (IOException ex)
            {
                System.err.println("Unable to open " + args[0]);
                System.exit(FILE_ERROR);
                return;
            }
        }
        else
        {
            scanner = new Scanner(System.in);
        }

        // An array list to hold the values
        ArrayList<String> wordList = new ArrayList<>();

        // Get the Alphabet via some reflection
        String alphabetType = scanner.next().toUpperCase();
        Alphabet alphabet;
        try
        {
            alphabet = (Alphabet) Alphabet.class.getField(alphabetType).get(null);
        }
        catch (NoSuchFieldException | IllegalAccessException ex)
        {
            System.err.println("Bad alphabet name");

            // Show all of the alphabets we know
            System.err.println("Valid alphabets are:");
            Field[] fields = Alphabet.class.getFields();
            for(Field f : fields)
            {
                System.err.println("\t" + f.getName());
            }
            System.exit(ALPHABET_ERROR);
            return;
        }

        // Get the first word and word length
        String firstWord = scanner.next();
        int wordLength = firstWord.length();
        wordList.add(firstWord);

        // While there is more to read, add them to the list
        while(scanner.hasNext())
        {
            String nextWord = scanner.next();
            if(nextWord.length() != wordLength)
            {
                System.err.println("Words must all have the same length");
                System.exit(WORD_LENGTH_ERROR);
            }
            wordList.add(nextWord);
        }

        LeastSignificantRadix.sort(wordList, wordLength, alphabet);
        for(String word : wordList)
        {
            System.out.println(word);
        }
    }
}

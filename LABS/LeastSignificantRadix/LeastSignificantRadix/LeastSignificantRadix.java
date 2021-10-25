package LeastSignificantRadix;
import java.util.ArrayList;

public class LeastSignificantRadix
{
    // do not instantiate
    private LeastSignificantRadix() { }

    /**
     * Rearranges the array of w-character strings in ascending order (based
     * on each character's index in the Alphabet).
     * 
     * @param a        the ArrayList to be sorted
     * @param w        the number of characters per string
     * @param alphabet the Alphabet the strings are encoded in
     */
    public static void sort(ArrayList<String> a, int w, Alphabet alphabet)
    {
        int N = a.size();
        int R = alphabet.radix();
        String[] aux = new String[N];

        for(int d = w - 1; d >= 0; d--){
            int[] count = new int[R+1];
            for(int i = 0; i < N; i++)
                count[alphabet.toIndex(a.get(i).charAt(d)) + 1]++;
            for(int r = 0; r < R; r++)
                count[r+1] += count[r];
            for(int i = 0; i < N; i++)
                aux[count[alphabet.toIndex(a.get(i).charAt(d))]++] = a.get(i);
            for(int i = 0; i < N; i++)
                a.set(i, aux[i]);
        }
        
    }
}
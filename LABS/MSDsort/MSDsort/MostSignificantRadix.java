package MSDsort;
import java.util.ArrayList;

public class MostSignificantRadix
{
    // do not instantiate
    private MostSignificantRadix() { }

    // cutoff to insertion sort
    private static final int CUTOFF = 15; 

    /**
     * Rearranges the array of strings in ascending order (based on each
     * character's index in the Alphabet)
     *
     * @param a        the ArrayList to be sorted
     * @param alphabet the Alphabet the strings are encoded in
     */
    private static int charAt(String s, int d){
        if(d < s.length()) return ap.toIndex(s.charAt(d));
        else return -1;
    }
    private static String[] aux;
    private static int R;
    private static Alphabet ap;
    public static void sort(ArrayList<String> a, Alphabet alphabet)
    {
        ap = alphabet;
        aux = new String[a.size()];
        R = alphabet.radix();
        sort(a, aux, 0, a.size() - 1, 0);
    }
    private static void sort(ArrayList<String> a, String[] aux, int low, int high, int d) {
        if(high <= low) return;
        int[] count = new int[R+2];
        for(int i = low; i <= high; i++)
            count[charAt(a.get(i), d) + 2]++;
        for(int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for(int i = low; i <= high; i++)
            aux[count[charAt(a.get(i), d) + 1]++] = a.get(i);
        for(int i = low; i <= high; i++)
            a.set(i, aux[i - low]);
        for(int r = 0; r < R; r++)
            sort(a, aux, low + count[r], low + count[r+1]-1, d+1);
    }

}

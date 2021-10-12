package Ring;
import java.io.PrintWriter;
import java.util.Scanner;



public class Main
{
    // A small test of Ring
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        Ring<String> ring = new Ring<>();

        // Add all of the given strings to the list
        while(cin.hasNext())
        {
            ring.offer(cin.next());
            System.out.println("Size: " + ring.size() + ", Front: " + ring.poll());

        }

        // Print all of the strings in the list
        while(!ring.isEmpty())
        {
            System.out.println("Size: " + ring.size() + ", Front: " + ring.poll());
        }

        // You can add your own tests here
    }
}

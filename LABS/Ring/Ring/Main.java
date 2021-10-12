package Ring;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;



public class Main
{
    // A small test of Ring
    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        Ring<String> ring = new Ring<>();

        // Add all of the given strings to the list
        /*
        while(cin.hasNext())
        {
            ring.offer(cin.next());
            System.out.println("Capacity: " + ring.capacity() + ", Size: " + ring.size() + ", Front: " + ring.poll());
            System.out.println(ring.poll());
        }
        */
        
        for(int i = 0; i < 35; i++){
            ring.offer("help");
        }
        for(int i = 0; i < 12; i++){
            ring.poll();
        }
        
        for(int i = 0; i < 42; i++){
            ring.offer("ADDED");
        }
        
        Iterator<String> a = ring.iterator();
        while(a.hasNext()){
            
            System.out.println(a.next());
        }
        ring.getArray();
        /*
        while(!ring.isEmpty())
        {
            System.out.println("Capacity: " + ring.capacity() + ", Size: " + ring.size() + ", Front: " + ring.poll());
            //System.out.println(ring.poll());
        }
        */
        // a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
    }
}

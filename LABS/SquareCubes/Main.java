import java.util.Scanner;
import java.util.HashSet;
public class Main
{
    public static void main(String[] args)
    {
        final Scanner scanner = new Scanner(System.in);
        Long max = scanner.nextLong();
        long count = 0;
        boolean greater = false;
        long i = 1;
        HashSet<Long> cubic = new HashSet<Long>();
        while(i * i * i <= max && i <= max){
           
           cubic.add(i * i * i);
           i++;
        }
        i = 1;
        while(i * i <= max && i <= max){
            if(cubic.contains(i * i)){
               count++;
            }
            i++;
        }
        System.out.println(count);
    }
}
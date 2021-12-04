import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        final Scanner cin = new Scanner(new BufferedInputStream(System.in));
        final int numberOfCases = cin.nextInt();
        for(int caseNumber = 0; caseNumber < numberOfCases; caseNumber++)
        {
            final int numberOfItems = cin.nextInt();
            System.out.println(getMaxBundleSize(cin, numberOfItems));
        }
    }

    public static int getMaxBundleSize(Scanner inputScanner, int size)
    {
       HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
       int[] arr = new int[size];
       int maxSize = 0;
       int counter = 0;
       for(int i = 0; i < size; i++){
         arr[i] = inputScanner.nextInt();   
       }
       int index = 0;
       while(index < size){
         if(map.containsKey(arr[index])){
            
            index = map.get(arr[index]) + 1;
            map = new HashMap<Integer, Integer>();
            counter = 0;
         }else{
            map.put(arr[index], index);
            counter++;
            index++;
         }
         if(maxSize < counter) maxSize = counter;
       }
       
       return maxSize;
    }
}
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
public class ListIntersection {
    private static Scanner scanner;

    public static void main(String[] args)
    {
        // Set the scanner
        if(args.length < 1)
        {
            scanner = new Scanner(new BufferedInputStream(System.in));
        }
        else
        {
            try
            {
                scanner = new Scanner(new FileReader(args[0]));
            }
            catch (FileNotFoundException ex)
            {
                System.err.println("Unable to open " + args[0]);
                return;
            }
        }

        // How many tests are there
        final int numberOfTests = scanner.nextInt();

        // Read each test case
        for(int caseNumber = 0; caseNumber < numberOfTests; caseNumber++)
        {
            // I thought this was a nice way to decompose the problem. However, it can
            // be solved faster with just one HashMap. So feel free to change this
            // framework to fit your own ideas.
           
            // Get the size of the lists, use the data to fill a HashMap for each list.
            int size1 = scanner.nextInt();
            int size2 = scanner.nextInt();
            HashMap<Integer, Integer> map1 = readIntoMap(size1);
            HashMap<Integer, Integer> map2 = readIntoMap(size2);

            // Use the maps to solve the test case
            System.out.println(minimumNumberOfItemsToRemove(map1, map2));
        }
    }

    // Add methods as needed.
    
    private static HashMap<Integer, Integer> readIntoMap(int size)
    {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
        int n;
        for(int i = 0; i < size; i++){
            n = scanner.nextInt();
            temp.put(n, temp.getOrDefault(n, 0) + 1);
        }
        return temp;
    }

    private static int minimumNumberOfItemsToRemove(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2)
    {
        int sum = 0;
        for(int key : map1.keySet()){
            if(map2.containsKey(key)){
               if(map1.get(key) < map2.get(key)){
                   sum += map2.get(key) - map1.get(key);
                   //map2.put(key, map1.get(key));
               }
            }else{
               //map1.remove(key);  
               sum+=map1.get(key);
            }
        }
        for(int key : map2.keySet()){
            if(map1.containsKey(key)){
               if(map2.get(key) < map1.get(key)){
                   sum += map1.get(key) - map2.get(key);
                   //map1.put(key, map2.get(key));
               }
            }else{
               //map2.remove(key);  
               sum+=map2.get(key);
            }
        }
        
        return sum;
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        String string = scanner.next();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        // Add code here
        String sub;
       for(int i = 0; i <= string.length() - size; i++){
         sub = string.substring(i, i+size);
         map.put(sub, map.getOrDefault(sub, 0) + 1);
       }
       String result = string.substring(0, size);
       for(String n : map.keySet()){
           if(map.get(n) > map.get(result)){
             result = n; 
           }else if(map.get(n) == map.get(result)){
             if(n.compareTo(result) < 0){
               result = n;
             }
           }
       }
       System.out.println(result);
    }
}

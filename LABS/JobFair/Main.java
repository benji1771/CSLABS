import java.util.HashMap;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      /* Type your code here. */
      while(scnr.hasNextLine()){
         String name = scnr.nextLine();
         String current;
         char test = name.charAt(0);
         HashMap<String, Integer> company = new HashMap<String, Integer>();
         HashMap<String, String> userid = new HashMap<String, String>();
         if(Character.isDigit(test)){

         }else if(Character.isUpperCase(test)){
            company.put(name,)
            current = name;
         }else{
            
         }
      }
   }
}

package permutations;
import java.util.Scanner;
import java.util.ArrayList;

public class PhotoLineups {

   // TODO: Write method to create and output all permutations of the list of names.
   public static void allPermutations(ArrayList<String> permList, ArrayList<String> nameList) {
      
      if(nameList.size() <= 1){
         for(String n: permList){

            System.out.print(n + " ");
            
         }
         System.out.println(nameList.get(0) + " ");
      }else{
         
         for(int i = 0; i < nameList.size(); i++){
            permList.add(nameList.remove(i));
            allPermutations(permList, nameList);
            nameList.add(i, permList.remove(permList.size() - 1));

         }

      }
      

   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      ArrayList<String> nameList = new ArrayList<String>();
      ArrayList<String> permList = new ArrayList<String>();
      String name;
      
      // TODO: Read in a list of names; stop when -1 is read. Then call recursive method.
      name = scnr.next();
      while(!name.equals("-1")){
         nameList.add(name);
         name = scnr.next();
      }
      allPermutations(permList, nameList);
      scnr.close();
   }
}

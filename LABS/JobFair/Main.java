import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      HashSet<String> universal = new HashSet<>();
      TreeMap<String, HashSet<String>> company = new TreeMap<String, HashSet<String>>();
      String current = null;
      /* Type your code here. */
      while(scnr.hasNextLine()){
         String name = scnr.nextLine();
         
         char test = name.charAt(0);
         
         
         
         if(Character.isDigit(test)){
            if(Character.getNumericValue(test) == 1){
               for(String i : company.keySet()){
                  for(String j : company.keySet()){
                     String first = i;
                     String second = j;
                     if(first!=second){
                        HashSet<String> temp = new HashSet<String>(company.get(i));
                        temp.retainAll(company.get(j));
                        universal.addAll(temp);
                        temp.addAll(universal);
                        company.get(i).removeAll(temp);

                     }
                  }
               }
               
               // for(String entries : company.keySet()){
               //    StringBuilder sb = new StringBuilder();
               //    sb.append(entries);
               //    sb.append(" ");
               //    sb.append(company.get(entries).size());
               //    System.out.println(sb.toString());
               // }
               company.entrySet().stream().sorted(Map.Entry.<String, HashSet<String>>comparingByValue(new sortBySize()).reversed()
                           .thenComparing(Map.Entry.comparingByKey()))
                           .forEach(e -> System.out.println(e.getKey() + " " + e.getValue().size()));
               universal = new HashSet<>();
               company = new TreeMap<String, HashSet<String>>();
               current = null;
            }
         }else if(Character.isUpperCase(test)){
            
            company.put(name, new HashSet<String>());
            current = name;
         }else{
            company.get(current).add(name);
         }
         
         
      }
   }
}
class sortBySize implements Comparator<HashSet<String>> {

   @Override
   public int compare(HashSet<String> o1, HashSet<String> o2) {
      if(o1.size() > o2.size()) return 1;
      if(o1.size() < o2.size()) return -1;
      return 0;
   }
   
}
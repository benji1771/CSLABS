package SolveEquation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> first, second;
        Long temp = 0L;
        first = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        second = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        
        allPermutations(first, temp, second);
        System.out.println(answer);
        scanner.close();
    }
    static String answer = "no";
    static int index = 0;
    public static void allPermutations(ArrayList<String> original, Long sum, ArrayList<String> myList) {     
        String temp;
        
        if(index >= original.size() - 1){ 
            //System.out.println(myList);
            
           for(int i = 0; i < myList.size(); i++){
               if(sum + (Long.parseLong(myList.get(i)) * Long.parseLong(original.get(original.size() - 1))) == 0L){
                  answer = "yes";
                  return;
               }
           }
           
        }else{       
           for(int i = 0; i < myList.size(); i++){
              temp = myList.remove(i);
              sum+=(Long.parseLong(original.get(index)) * Long.parseLong(temp));
              index++;
              allPermutations(original, sum, myList);
              index--;
              myList.add(i, temp);
              sum-=(Long.parseLong(original.get(index)) * Long.parseLong(temp));
           }
           
        }

     }
}

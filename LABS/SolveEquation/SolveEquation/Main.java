package SolveEquation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> first, second, temp;
        temp = new ArrayList<>();
        first = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        second = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        allPermutations(first, temp, second);
        System.out.println(answer);
    }
    static String answer = "no";

    public static void allPermutations(ArrayList<String> original, ArrayList<String> permList, ArrayList<String> myList) {     
        if(permList.size() >= original.size()){
           long sum = 0L; 
           for(int k = 0; k < permList.size(); k++){
                sum+=(Long.parseLong(original.get(k)) * Long.parseLong(permList.get(k)));
           }
           if(sum == 0L){
              answer = "yes";
              return;
           }
        }else{       
           for(int i = 0; i < myList.size(); i++){
              permList.add(myList.remove(i));
              allPermutations(original, permList, myList);
              myList.add(i, permList.remove(permList.size() - 1));
           }
           
        }

     }
}

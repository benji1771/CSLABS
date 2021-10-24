import java.util.Scanner;

public class DescendingOrder {
   // TODO: Write a void method selectionSortDescendTrace() that takes 
   //       an integer array and the number of elements in the array as arguments, 
   //       and sorts the array into descending order.
   public static void selectionSortDescendTrace(int [] numbers, int numElements) {
      for(int i = 0; i < numElements - 1; i++){
         int max = i;
         for(int j = i; j < numElements; j++){
            if(numbers[j] > numbers[max]) max = j;
         }
         int temp = numbers[i];
         numbers[i] = numbers[max];
         numbers[max] = temp;
         for(int j = 0; j < numElements; j++){
            System.out.print(numbers[j] + " ");
         }
         System.out.println();
         
      }
   }
   // 10 20 30 40 50 60 70 80 90 100 -1 11 21
   
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      
      int input, i = 0;
      int numElements = 0;
      int [] numbers = new int[10];
      while(i < 10){
         input = scnr.nextInt();
         if(input < 0) break;
         numbers[i] = input;
         numElements++;
         i++;
      }
      selectionSortDescendTrace(numbers, numElements);
      // TODO: Read in a list of up to 10 positive integers; stop when
      //       -1 is read. Then call selectionSortDescendTrace() method.

   }
}


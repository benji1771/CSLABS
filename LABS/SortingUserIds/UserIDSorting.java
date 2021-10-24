import java.util.Scanner;
import java.util.ArrayList;

public class UserIDSorting {
   // TODO: Write the partitioning algorithm - pick the middle element as the 
   //       pivot, compare the values using two index variables l and h (low and high), 
   //       initialized to the left and right sides of the current elements being sorted,
   //       and determine if a swap is necessary

   public static void swapNums(ArrayList<String> userIDs, int index1, int index2){
        String tempo = userIDs.get(index1);
        userIDs.set(index1, userIDs.get(index2));
        userIDs.set(index2, tempo);
   }
   public static int partition(ArrayList<String> userIDs, int i, int k) {
        int middle = (i + k) / 2;
        swapNums(userIDs, i, middle);
        int l = i + 1;
        for(int j = l; j <= k; j++){
            if(userIDs.get(j).compareTo(userIDs.get(i)) < 0) {
                swapNums(userIDs, j, l);
                l++;
            }
        }
        swapNums(userIDs, i, l-1);
        return l - 1;
   }

   // TODO: Write the quicksort algorithm that recursively sorts the low and 
   //       high partitions
   public static void quicksort(ArrayList<String> userIDs, int i, int k) {
        if(k + 1 > i){
            int p = partition(userIDs, i, k);
            quicksort(userIDs, i, p - 1);
            quicksort(userIDs, p + 1, k);
        }
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      ArrayList<String> userIDList = new ArrayList<String>();

      String userID;

      userID = scnr.next();
      while (!userID.equals("-1")) {
         userIDList.add(userID);
         userID = scnr.next();
      }
      
      // Initial call to quicksort 
      quicksort(userIDList, 0, userIDList.size() - 1);

      for (int i = 0; i < userIDList.size(); ++i) {
         System.out.println(userIDList.get(i));
      }
   }
}


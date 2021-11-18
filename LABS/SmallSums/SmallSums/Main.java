package SmallSums;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        // Make a scanner to read the input from either a file or System.in
        Scanner input;
        if(args.length > 0)
        {
            try
            {
                input= new Scanner(new BufferedReader(new FileReader(args[0])));
            }
            catch (FileNotFoundException ex)
            {
                System.err.println("Unable to open file");
                System.exit(1);
                return;
            }
        }
        else
        {
            input = new Scanner(System.in);
        }

      /* Type your code here. */
        while(input.hasNextInt()){
            int n = input.nextInt();
            listQueue[] lst = new listQueue[n];
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int i = 0; i < n; i++){
                lst[i] = new listQueue();
                for(int j = 0; j < n; j++){
                    if(input.hasNextInt()){
                        lst[i].put(input.nextInt());
                    }
                }
                lst[i].setCurrent();
            }
            
            printSum(lst);
            for(int j = 1; j < n; j++){
                int min = 0;
                for(int i = 1; i < n; i++){
                    if(lst[i].getSub() < lst[min].getSub()){
                        min = i;
                    }
                }
                int tj = lst[min].getCurr();
                lst[min].pull();
                if(j < n - 1)
                    printSum(lst);
                else
                    printSumEnd(lst);
                lst[min].setCurrent(tj);
            }

        }
   }
   static void printSum(listQueue[] a){
        int sum = 0;
        for(listQueue n: a){
            sum+=n.getCurr();
        }
        System.out.print(sum + " ");
   }
   static void printSumEnd(listQueue[] a){
        int sum = 0;
        for(listQueue n: a){
            sum+=n.getCurr();
        }
        System.out.println(sum);
   }
}
class SmallSums {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    public SmallSums(int i, int j, int k){
        
    }
}
class listQueue {
    private PriorityQueue<Integer> pq;
    private int current = 0;
    public listQueue(){
        pq = new PriorityQueue<>();
    }
    
    public void put(int n){
        pq.add(n);
    }
    public void setCurrent(int n){
        current = n;
    }
    public void setCurrent(){
        current = pq.poll();
    }
    public int getCurr(){
        return current;
    }
    public int getSub(){
        return pq.peek() - current;
    }
    public int pull(){
        current = pq.poll();
        return current;
    }
}

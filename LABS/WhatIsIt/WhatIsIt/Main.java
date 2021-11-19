package WhatIsIt;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static PriorityQueue<Integer> pq;
    static ArrayDeque<Integer> s;
    static ArrayDeque<Integer> q;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        /* Type your code here. */
        while(sc.hasNextInt()){
            int n = sc.nextInt();
            pq = new PriorityQueue<>(n, Collections.reverseOrder());
            s = new ArrayDeque<>();
            q = new ArrayDeque<>();
            int[] test = {1, 1, 1};
            for(int i = 0; i < n; i++){
                if(sc.hasNextLine()){
                    String command = sc.next();
                    int num = sc.nextInt();
                    if(command.equals("push")){
                        pushIt(num);
                        
                    }else if(command.equals("pop")){
                        popIt(test, num);
                    }

                }
            }
            int sum = 0;
            for(int tc: test){
                sum+=tc;
            }
            if(sum > 1) System.out.println("not sure");
            else if(sum < 1) System.out.println("impossible");
            else if(test[0] == 1) System.out.println("priority queue");
            else if(test[1] == 1) System.out.println("stack");
            else if(test[2] == 1) System.out.println("queue");
        }
        sc.close();

    }
    public static void pushIt(int num){
        pq.add(num);
        s.push(num);
        q.offer(num);
    }
    public static void popIt(int[] test, int num){
        if(pq.isEmpty()){
            test[0] = 0;
            test[1] = 0;
            test[2] = 0;
            return;
        }
        if(pq.poll() != num) test[0] = 0;
        if(s.pop() != num) test[1] = 0;
        if(q.poll() != num) test[2] = 0;
    }
    
}
// public class UnorderedMaxPQ<Key extends Comparable<Key>> {
//     private Key[] pq;
//     private int N;

//     public UnorderedMaxPQ(int capacity){
//         pq = (Key[]) new Comparable[capacity];
//     }

//     public boolean isEmpty() { return N == 0; }
//     public void insert(Key x){ pq[N++] = x; }
//     public Key delMax()
//     {
//         int max = 0;
//         for(int i = 1; i < N; i++)
//             if( pq[max].compareTo(pq[i]) < 0) max = i;
//         Key temp = pq[N-1];
//         pq[N-1] = pq[max];
//         pq[max] = temp;

//         return pq[--N];
//     }
// }
package EventSimulation;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      final int n = Integer.parseInt(args[0]);
      Scanner scnr = new Scanner(System.in);
      PriorityQueue<simEvent> pq = new PriorityQueue<>();
      /* Type your code here. */
      while(scnr.hasNextLine()){
         if(scnr.next().equals("event")){
            int id = scnr.nextInt();
            int period = scnr.nextInt();
            if(id >= 0 && period > 0)
               pq.add(new simEvent(id, period));
         }
      }
      int sum = 0;
      int i = 1;
      while(sum < n){
         PriorityQueue<simEvent> pqt = new PriorityQueue<>();
         while(!pq.isEmpty()){
            simEvent temp = pq.remove();
            sum+=temp.tick(i);
            pqt.add(temp);
         }
         i++;
         pq = pqt;
      }
   }

   
}
class simEvent implements Comparable<simEvent>{
   private int id, period;
   public simEvent(int id, int period){
      this.id = id;
      this.period = period;
   }

   public int getId(){
      return id;
   }
   public int period(){
      return period;
   }
   public int tick(int t){
      if(t%period == 0){
         System.out.println(id);
         return 1;
      }
      return 0;
   }

   @Override
   public int compareTo(simEvent o) {
      if(id > o.getId()) return 1;
      if(id < o.getId()) return -1;
      return 0;
   }
}
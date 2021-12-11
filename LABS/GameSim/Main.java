import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int testCases = scnr.nextInt();
        
        
        /* Type your code here. */
        for(int i = 0; i < testCases; i++){
            PriorityQueue<Integer> redTeam = new PriorityQueue<Integer>(Collections.reverseOrder());
            PriorityQueue<Integer> blueTeam = new PriorityQueue<Integer>(Collections.reverseOrder());
            int location = scnr.nextInt();
            int sizeRed = scnr.nextInt();
            int sizeBlue = scnr.nextInt();
            for(int mem = 0; mem < sizeRed; mem++){
                redTeam.add(scnr.nextInt());
            }
            for(int mem = 0; mem < sizeBlue; mem++){
                blueTeam.add(scnr.nextInt());
            }
            while(redTeam.size() > 0 && blueTeam.size() > 0){
                for(int l = 0; l < location; l++){
                    if(redTeam.size() == 0){

                    }else if(blueTeam.size() == 0){

                    }else{
                        int sum = redTeam.poll() - blueTeam.poll();
                        if(sum > 0){
                            
                        }
                    }
                }
            }
        }

    }
}

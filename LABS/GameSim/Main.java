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
            int[] locations = new int[scnr.nextInt()];
            int sizeRed = scnr.nextInt();
            int sizeBlue = scnr.nextInt();

            for(int mem = 0; mem < sizeRed; mem++){
                redTeam.add(scnr.nextInt());
            }
            for(int mem = 0; mem < sizeBlue; mem++){
                blueTeam.add(scnr.nextInt());
            }
            while(redTeam.size() > 0 && blueTeam.size() > 0){
                for(int l = 0; l < locations.length; l++){
                    if(redTeam.isEmpty() && blueTeam.isEmpty()){
                        locations[l] = 0;
                    }else if(redTeam.isEmpty()){
                        locations[l] = blueTeam.poll() * -1;
                    }else if(blueTeam.isEmpty()){
                        locations[l] = redTeam.poll();
                    }else{
                        locations[l] = redTeam.poll() - blueTeam.poll();
                    }
                }
                for(int s = 0; s < locations.length; s++){
                    if(locations[s] > 0){
                        redTeam.add(locations[s]);
                    }else if(locations[s] < 0){
                        blueTeam.add(locations[s] * -1);
                    }
                }
            }
            if(redTeam.isEmpty() && blueTeam.isEmpty()){
                System.out.println("draw");
            }else if(redTeam.isEmpty()){
                System.out.println("blue wins");
                printTeam(blueTeam);
            }else{
                System.out.println("red wins");
                printTeam(redTeam);
            }
            if(i != testCases - 1)
            System.out.println();
        }

    }

    public static void printTeam(PriorityQueue<Integer> team){
        while(!team.isEmpty()){
            System.out.println(team.poll());
        }
    }
}

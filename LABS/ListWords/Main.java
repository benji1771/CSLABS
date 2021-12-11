
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
   public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        TreeSet<String> set = new TreeSet<String>();
        char temp;
        StringBuilder sb;
        String str;
      /* Type your code here. */
        while(scnr.hasNextLine()){
                str = scnr.nextLine().toLowerCase();
                sb = new StringBuilder();
                for(int i = 0; i < str.length(); i++){
                   temp = str.charAt(i);
                    if(!Character.isLetter(temp)){
                        if(sb.length() > 0){
                            set.add(sb.toString());
                            sb = new StringBuilder();
                        }
                    }else{
                        sb.append(temp);
                    }
                }
                if(sb.length() > 0)
                set.add(sb.toString());
        }
    
        for(String s : set){
            System.out.println(s);
        }
   }
}

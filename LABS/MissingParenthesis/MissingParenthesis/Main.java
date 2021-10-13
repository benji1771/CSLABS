package MissingParenthesis;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    
    public static String fixExpression(String brokenExpression)
    {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<String> deck = new ArrayDeque<>();
        ArrayDeque<String> op = new ArrayDeque<>();
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(brokenExpression.split(" ")));
        int temp;
        String text;
        boolean open = true;
        for(int i = expr.size() - 1; i >= 0; i--){
            text = expr.get(i);
            temp = exp(text);
            
            if(temp == 2){
                deck.push(text);
            }else if(temp == -1){
                op.push(text);
            }else{
                if(open){
                    open = false;
                    op.push(text);
                }else{
                    sb.insert(0, deck.pop());
                    while(!op.isEmpty()){
                        sb.insert(0, op.removeLast());
                    }
                    sb.insert(0, "(");
                    sb.insert(0, text);
                    open = true;
                }
            }
        } 
        sb.insert(0, deck.pop());
        while(!op.isEmpty()){
            sb.insert(0, op.removeLast());
        }
        sb.insert(0, "(");
        while(!deck.isEmpty()){
            sb.append(deck.pop());
            sb.insert(0, "(");
        }
        return sb.toString();
        // Add your code here
    }

    // Add methods and data members here as needed
    
    static int exp(String c) {
        switch(c) {
            case ")": return 2;

            case "*":
            case "/":
            case "+":
            case "-":
                      return 0;
            
            default: return -1;
            
        }

    }
    // Do not edit main
     public static void main(String[] args)
     {
         Scanner cin = new Scanner(System.in);
         
         while(cin.hasNextLine())
         {
             System.out.println(fixExpression(cin.nextLine()));
         }
     }
}


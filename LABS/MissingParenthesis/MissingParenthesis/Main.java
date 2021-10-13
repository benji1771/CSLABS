package MissingParenthesis;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main
{
    //1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
    //( 1 + 2 ) * ( 3 - 4 ) * ( 5 - 6 ) ) )

    public static String fixExpression(String brokenExpression)
    {
        Scanner str = new Scanner(brokenExpression);
        String n;
        StringBuilder sb = new StringBuilder();
        ArrayDeque<String> deck = new ArrayDeque<>();
        ArrayDeque<String> tempo = new ArrayDeque<>();
        while(str.hasNext()){
            n = str.next();
            if(exp(n) == -1)
            {
                deck.push(n);
            }else if(exp(n) == 2)
            {
                sb.append("( ");
                for(int i = 0; i < 3; i++)
                {
                    tempo.push(deck.pop() + " ");
                }
                for(int i = 0; i < 3; i++){
                    sb.append(tempo.pop());
                }
                sb.append(")");
                
                deck.push(sb.toString());
                sb.delete(0, sb.length());

            }
        }
        return deck.pop();
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


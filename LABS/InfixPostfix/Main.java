
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String infix;
        StringBuilder postFix = new StringBuilder();
        int exValue;
        char ex;

        while(scnr.hasNextLine()){
            infix = scnr.nextLine();

            for(int i = 0; i < infix.length(); i++){
                ex = infix.charAt(i);
                exValue = exp(ex);
                if(exValue == -1) {
                    postFix.append(ex);
                    
                }else if(exValue == 0){
                    stack.push(ex);
                }else if(exValue == 1){

                    while(!stack.isEmpty() && exp(stack.peek()) != 0){
                        //System.out.println(stack.peek());
                        postFix.append(stack.pop());
                    }
                    
                    //System.out.println(stack);
                    stack.pop();
                }else{
                    while(!stack.isEmpty() && exValue >= exp(stack.peek()) && exp(stack.peek()) != 0){
                        postFix.append(stack.pop());
                    }
                    stack.push(ex);
                }
                
                //System.out.println("stack: " + stack);
                //System.out.println("tempStack: " + tempStack);
            }
            while(!stack.isEmpty()){
                if(stack.peek() == '('){
                    System.out.println("Invalid");
                }
                postFix.append(stack.pop());
            }
            System.out.println(postFix.toString());
            postFix = new StringBuilder();

        }

        scnr.close();
   }
    static int exp(char c) {
        switch(c) {
            case '(': return 0;
            case ')': return 1;
            case '^': return 2;
            case '*':
            case '/':
                      return 3;
            case '%':
            case '+':
            case '-':
                      return 4;
            
            default: return -1;
            
        }
   }
   
}

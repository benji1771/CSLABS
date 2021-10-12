package Calculator_two;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Scanner;



public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<BigInteger> result = new ArrayDeque<>();
        int value;
        BigInteger temp1, temp2;
        
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            for(String n: line.split(" ")){
                value = exp(n);
                if(value == -1){
                    result.push(new BigInteger(n));
                }else if(value == 3){
                    stack.push(n);
                }else if(value == 2){
                    while(exp(stack.peek()) != 3){
                        temp2 = result.pop();
                        temp1 = result.pop();
                        result.push(calc(stack.pop(), temp1, temp2));
                    }
                    stack.pop();
                }else{
                    while(!stack.isEmpty() && exp(stack.peek()) != 3 && value <= exp(stack.peek())){
                        temp2 = result.pop();
                        temp1 = result.pop();
                        result.push(calc(stack.pop(), temp1, temp2));
                    }
                    stack.push(n);
                }
            }
            while(!stack.isEmpty()){
                temp2 = result.pop();
                temp1 = result.pop();
                result.push(calc(stack.pop(), temp1, temp2));
            }
            System.out.println(result.peek().toString());
        }
    }
    static int exp(String c) {
        switch(c) {
            case "(": return 3;
            case ")": return 2;
            case "*":
            case "/":
            case "%":
                      return 1;
            
            case "+":
            case "-":
                      return 0;
            
            default: return -1;
            
        }

    }

    static BigInteger calc(String n, BigInteger n1, BigInteger n2){
        BigInteger tmp1 =  n1;
        BigInteger tmp2 = n2;
        switch(n) {
            case "/": return n1.divide(n2);
            case "*": return n1.multiply(n2);
            case "+": return n1.add(n2);
            case "-": return n1.subtract(n2);
            case "%": /*
                      if(n1.signum() == -1){
                          tmp1 = n1.negate();
                      }
                      if(n2.signum() == -1){
                          tmp2 = n2.negate();
                      }
                    
                      if(n1.signum() == -1 || n2.signum() == -1){
                        if(n1.signum() == -1 && n2.signum() == -1){
                            return n1.negate().mod(n2.negate()).negate();
                        }else if(n1.signum() == -1){
                            return n1.negate().mod(n2).negate();
                        }else{
                            return n1.mod(n2.negate()).negate();
                        }
                      } 
                      */
                     return tmp1.remainder(tmp2);

        }

        return new BigInteger("0");
    }
    
}
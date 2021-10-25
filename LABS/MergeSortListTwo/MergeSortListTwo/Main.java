package MergeSortListTwo;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Node<String> head = null;
        // Scanner scanner = new Scanner(System.in);
        // 3 4 2 1 7 5 8 9 0 6
        // while(scanner.hasNext())
        // {
        //     head = Node.push(head, scanner.next());
        // }
        head = Node.push(head, "6");
        head = Node.push(head, "0");
        head = Node.push(head, "9");
        head = Node.push(head, "8");
        head = Node.push(head, "5");
        head = Node.push(head, "7");
        head = Node.push(head, "1");
        head = Node.push(head, "2");
        head = Node.push(head, "4");
        head = Node.push(head, "3");

        // head = Node.push(head, "z");
        // head = Node.push(head, "y");
        // head = Node.push(head, "x");
        // head = Node.push(head, "w");
        // head = Node.push(head, "v");
        // head = Node.push(head, "u");
        // head = Node.push(head, "t");
        // head = Node.push(head, "s");
        // head = Node.push(head, "r");
        // head = Node.push(head, "q");
        // head = Node.push(head, "p");
        // head = Node.push(head, "o");
        // head = Node.push(head, "n");
        // head = Node.push(head, "m");
        // head = Node.push(head, "l");
        // head = Node.push(head, "k");
        // head = Node.push(head, "j");
        // head = Node.push(head, "i");
        // head = Node.push(head, "h");
        // head = Node.push(head, "g");
        // head = Node.push(head, "f");
        // head = Node.push(head, "e");
        // head = Node.push(head, "d");
        // head = Node.push(head, "c");
        // head = Node.push(head, "b");
        // head = Node.push(head, "a");
        
        // Node<String> right = Node.split(head);
        // Node<String> left = head;
        // head = Node.merge(left, right);
        // System.out.println(left);
        // System.out.println(right);
        Node.sort(head, System.out);
    }
}
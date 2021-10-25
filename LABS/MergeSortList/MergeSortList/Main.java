package MergeSortList;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Node<String> head = null;
        // Scanner scanner = new Scanner(System.in);

        // while(scanner.hasNext())
        // {
        //     head = Node.push(head, scanner.next());
        // }
        head = Node.push(head, "kaylasimms");
        head = Node.push(head, "julia");
        head = Node.push(head, "myron1994");
        head = Node.push(head, "kaylajones");
        // Node<String> right = Node.split(head);
        // Node<String> left = head;
        // head = Node.merge(left, right);
        // System.out.println(left);
        // System.out.println(right);
        head = Node.sort(head);
        System.out.println(head);
    }
}


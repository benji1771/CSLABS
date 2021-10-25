package MergeSortListTwo;

import java.io.PrintStream;
import java.util.Arrays;

// Note you can edit, add, or remove the comments if you wish, They are
// just there to help you get started.
//
// We will use the following Node class to build a simple bare (not wrapped in
// a list class) linked list implementation, that is not tied into the Java
// Collections framework. The idea is to build a trimmed down class class that
// is fast and easy to implement. This style is more common in C programming.
// It should be noted that, we are sacrificing features and the safety checks
// of our other implementations (which is no small thing). This is also *not*
// good practice from an encapsulation standpoint, as it will allow other
// parts of our program to become dependent on the inner workings of our
// implementation. However, our goal in this case is to see just how simple a
// linked list can be.
//
// That said, it is *very* easy to make mistakes when working with links and
// Nodes directly. I suggest drawing pictures of the operations and using a
// debugger to help visualize your lists.
//
// You will be building a top down recursive merge sort for linked lists built
// from these nodes. Because mergesort is comparison based we will require that
// the data stored in a Node to be Comparable.
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{
    // In a true POD (plain old data) class, which is similar to a struct in C
    // we would just have public data members. No getter and setters, just
    // direct access to the members.  However, we will be adding some
    // convenience methods.
    public T data;
    public Node<T> next;

    // A toString method that shows all of the data in the list from this
    // node to the end of the list. This also lets nodes be printed nicely
    // in a debugger. Note the for loop shows how to traverse the list.
    @Override
    public String toString()
    {
        // Buffer to build the result
        StringBuilder result = new StringBuilder();

        // Use a node reference to walk the list.
        for (Node<T> look = this; look != null; look = look.next)
        {
            // Add the data at the current location to the buffer
            result.append(look.data);
            result.append(" ");
        }

        // Remove the last space and return the string
        return result.substring(0, result.length() - 1);
    }

    // A convenience method for comparing nodes by comparing their data.
    @Override
    public int compareTo(Node<T> o)
    {
        return data.compareTo(o.data);
    }

    // A size() method to get the length of the list for testing. This is slow
    // (linear time) so you do not want to call it for other purposes. In other
    // words, do not use it in your sorting functions. Instead, think about how
    // you can quickly determine if a list is empty or has size 1 for your
    // sorting function (you do not need to traverse the whole list to
    // figure that out).
    public int size()
    {
        int count = 0;

        // Use a node reference to walk the list.
        for (Node<T> look = this; look != null; look = look.next)
        {
            count++;
        }

        return count;
    }

    // A method to test if the list starting at this node is sorted for testing
    // purposes.
    public boolean isSorted()
    {
        Node<T> last = this;
        for (Node<T> look = next; look != null; look = look.next)
        {
            // Have we found an inversion
            if(last.compareTo(look) > 0) return false;
            last = look;
        }
        return true;
    }

    // Note POD classes do not have methods of their own. So we will have many
    // static methods to work with them. This is a very un-OOP type of design.
    // The remaining methods are all static.

    // Adds the given data to the start of the list. Since Java only allows
    // pass by value for method arguments we cannot update head within the
    // method itself. So we return the new head of the list to allow the
    // caller to update the head. Thus, you could build a list with:
    //
    // Node<Integer> head;
    // head = Node.push(null, 1);
    // head = Node.push(head, 2);
    // head = Node.push(head, 3);
    // System.out.println(head.toString()); // 3 2 1
    //
    // Data should not be null;
    public static <T extends Comparable<T>> Node<T> push(Node<T> head, T data)
    {
        // Make a new node
        Node<T> newNode = new Node<>();

        // Add the data to the node
        newNode.data = data;

        // Link the node into the list
        newNode.next = head;

        // Return the new head of the list
        return newNode;
    }

    // Some helper methods for building Linked lists from arrays and collections for testing.
    public static <T extends Comparable<T>> Node<T> push(Iterable<T> a)
    {
        Node<T> head = null;
        for(T item : a)
        {
            head = push(head, item);
        }

        return head;
    }

    public static <T extends Comparable<T>> Node<T> push(T[] a)
    {
        return push(Arrays.asList(a));
    }
    
    // While there is an easy to build recursive version of merge, you are to
    // build an iterative version. That is because the recursive version will
    // require excessive stack space that will lead to stack overflow for large
    // lists.
    //
    // merge takes the heads of two sorted lists and returns the head of a
    // sorted merged list. Hint: create a temporary node to hang your merged
    // list off of. Aside from this one temporary node you should not need to
    // make any other new nodes. Thus, you are to complete this method with
    // only using a constant amount of memory (the recursive version would use
    // a linear amount of memory that would come from the stack).
    public static <T extends Comparable<T>> Node<T> merge(Node<T> head1, Node<T> head2)
    {
        Node<T> result = new Node<T>();
        Node<T> pointer = result;
        while(head1 != null || head2 != null){
            if(head1 == null){
                while(head2 != null){
                    pointer.next = head2;
                    head2 = head2.next;
                    pointer = pointer.next;
                }
            }
            else if(head2 == null){
                while(head1 != null){
                    pointer.next = head1;
                    head1 = head1.next;
                    pointer = pointer.next;
                }
            }

            else if(head1.compareTo(head2) <= 0){
                pointer.next = head1;
                head1 = head1.next;
                pointer = pointer.next;
            }else{
                pointer.next = head2;
                head2 = head2.next;
                pointer = pointer.next;
            }

        }

        return result.next;
    }

    // Write a bottom-up iterative merge sort that takes advantage of the
    // ordering of linked list by proceeding as follows each time it needs
    // to find two sub lists to merge:
    //
    // - Find a sorted sub-list (by moving a reference along the list until
    //   finding an entry that is smaller than its predecessor)
    // - Find the next sorted sub-list in the same way
    // - Merge the two sub-lists
    // - Continue until the whole list is sorted.
    //
    // This method returns the new head of the sorted list. If w is not null then the
    // list will be printed to w after each pass.
    public static <T extends Comparable<T>> Node<T> sort(Node<T> head, PrintStream w)
    {
        // Note that split is not needed only merge as you will be spliting based on
        // runs and working bottom-up. 
        Node<T> ptr = head;
        Node<T> first, second;
        Node<T> temp;
        Node<T> end = null;
        boolean firstTime = false;
        // if(head.isSorted()){
        //    if(w != null) w.println(head);
        //    return head;
        // }
        int counter = 2;
        while(counter > 1){
            if(w != null) w.println(head);
            ptr = head;
            counter = 0;
            while(ptr != null){
                firstTime = false;
                first = ptr;
                if(first == head) firstTime = true;
                while(ptr != null && ptr.next != null && ptr.compareTo(ptr.next) <= 0){
                    ptr = ptr.next;
                }
                // second = ptr.next;
                // ptr.next = null;
                // ptr = second;
                if(ptr != null){
                    second = ptr.next;
                    ptr.next = null;
                }else second = ptr;
                ptr = second;
                while(ptr != null && ptr.next != null && ptr.compareTo(ptr.next) <= 0){
                    ptr = ptr.next;
                }
                if(ptr != null){
                    temp = ptr.next;
                    ptr.next = null;
                }else temp = ptr;
                if(firstTime){
                    if(ptr == null) return head; 
                    head = Node.merge(first, second);
                    end = head;
                }else{

                    
                    while(end.next != null){
                        end = end.next;
                    }
                    end.next = Node.merge(first,second);
                }
                ptr = temp;
                counter++;
            }
            //
        }
        if(w != null) w.println(head);
        return head;
    }

}

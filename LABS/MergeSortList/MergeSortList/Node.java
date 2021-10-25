package MergeSortList;
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
    
    // Split the list into two halves, returning the head of the start of the
    // split.
    public static <T extends Comparable<T>> Node<T> split(Node<T> head)
    {
        // If the given list is empty there is nothing to do
        if(head.size() == 0) return null;

        // You are to use Floyd's Tortoise and Hare algorithm to find the split
        // (it can also be used to find cycles in a linked list, but that is
        // not what we are doing here). Start by making two node references,
        // tortoise and hare, that refer to the start of our list.
        Node<T> tortoise = head;
        Node<T> hare = head;


        // Build a loop that moves the hare two steps and the tortoise one step
        // until the hare reaches the end of the list. It takes a little bit of
        // care to avoid NullPointerExceptions. Remember that the hare may not
        // always be able to move two spaces (i.e. test on odd and even
        // length lists).
        while(hare.next != null){
            hare = hare.next;
            if(hare.next != null){

                hare = hare.next;
                tortoise = tortoise.next;
            } 
        }


        // Now the tortoise is at the middle of the list. Break the list into
        // two lists (this can be done with one assignment) and return the start
        // of split off list.
        Node<T> secondHalf = tortoise.next;
        tortoise.next = null;
        return secondHalf;
    }
    
    // While it is easy to build a recursive version of merge, you are to
    // build an iterative version. That is because the recursive version will
    // require excessive stack space that will lead to a stack overflow for large
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

    // Write a classic version of a top down recursive merge sort for a linked
    // list. This is not the most efficient version of merge sort for linked
    // lists as the recursion requires stack space that could be avoided.
    // However, the order of growth should still be n log (n). Follow the
    // framework to create a n log (n) recursive merge sort for a linked list.
    //
    // This method returns the new head of the sorted list.
    public static <T extends Comparable<T>> Node<T> sort(Node<T> head)
    {
        // If the list has 1 or fewer nodes then there is nothing to do. (do
        // not use size() for this as that is too slow)
        if(head == null || head.next == null){
            return head;
        }else{
        
        // Split the list in the middle
        Node<T> left = head;
        Node<T> right = Node.split(head);
        
        // Sort the left side
        left = Node.sort(left);

        // Sort the right side
        right = Node.sort(right);

        // Merge and return the left and right lists
        return Node.merge(left, right);
        
        }
        
    }

}

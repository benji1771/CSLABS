package Ring;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
// Here you are to implement a queue as a circular array. As we saw in class
// this is a very efficient implementation. We will tie this class into the
// Java collection framework by extending AbstractQueue<T>. Thus,
// enqueue = offer
// dequeue = poll
public class Ring<T> extends AbstractQueue<T>
{

    // The start of an Iterator for your Ring.
    private class RingIterator implements Iterator<T>
    {
        int counter = front;
        @Override
        public boolean hasNext()
        {
            //System.out.println(counter);
            if(isEmpty()) return false;
            if(counter == -1) return false;
            if(counter > arr.length - 1) counter = 0;
            if(arr[counter] != null){
                return true;
            }
            return false;
        }

        @Override
        public T next()
        {
            @SuppressWarnings("unchecked")
            T result = (T) arr[counter];
            if(counter == rear) counter = -1;
            else counter++;
            return result;
        }
    }
    //@SuppressWarnings("unchecked")
    public static final int DEFAULT_CAPACITY = 32;
    private Object[] arr;
    private int front, rear, size;

    public void getArray(){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        
    }
    // Builds an empty Ring with the default capacity.
    public Ring()
    {
        arr = new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;

    }

    // Builds a Ring with the given positive capacity, throws
    // IllegalArgumentException if capacity < 2.
    public Ring(int capacity)
    {
        if(capacity < 2) throw new IllegalArgumentException();
        arr = new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;

    }

    // Builds a Ring with capacity equal to c.size() and that holds all of
    // the items in c in the order that c's iterator presents them in.
    public Ring(Collection<T> c)
    {
        int cCap = c.size();
        if(cCap < 2) throw new IllegalArgumentException();
        arr = new Object[cCap];
        front = 0;
        rear = 0;
        size = 0;
        Iterator<T> it = c.iterator();
        while(it.hasNext()){
            offer(it.next());
        }
    }

    // Returns an iterator that walks the queue from front to back. This
    // iterator does not support modification of the Ring.
    @Override
    public Iterator<T> iterator()
    {
        return new RingIterator();
    }

    // Returns the number of items in the Ring.
    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    // Returns the current capacity of the ring
    public int capacity()
    {
        return arr.length;
    }

    // Adds the given non-null item to the back of the queue. Returns true on
    // success. If the backing array is full then the capacity is doubled.
    // Throws a NullPointerException if given item is is null (this queue does
    // not permit null elements).
    @Override
    public boolean offer(T t)
    {
        if(t == null) throw new NullPointerException();
        if(isEmpty()){
            arr[rear] = t;
            size++;
            return true;
        }
        
        expand();
        if(rear == arr.length - 1){
            arr[0] = t;
            rear = 0;
        }else{
            rear++;
            arr[rear] = t;
            
        }
        size++;
        return true;
    }

    public void expand(){
        if(size == arr.length){
            Object[] temp = new Object[arr.length * 2];
            int index = 0;
            Iterator<T> it = this.iterator();

            while(it.hasNext()){
                temp[index] = it.next();
                //System.out.println(index);
                index++;
            }
            arr = temp;
            front = 0;
            rear = index - 1;
        }
    }

    // Removes and returns the item at the front of the queue or null if the
    // queue is empty.
    @Override
    public T poll()
    {
        
        if(isEmpty()) return null;

        @SuppressWarnings("unchecked")
        T result = (T) arr[front];
        arr[front] = null;
        if(front != rear){
            if(front == arr.length - 1) front = 0;
            else front++;
        }
        
        size--;
        return result;

    }

    // Returns the item at the front of the queue or null if the queue is
    // empty.
    public T peek()
    {
        if(isEmpty()) return null;
        @SuppressWarnings("unchecked")
        T res = (T) arr[front];
        return res;
    }

    // Add more members as needed.

}

public interface IQueue<T> {

    void enqueue(T data);   // insert a data item at the back of the queue
    T dequeue();            // remove the data item from the front of the queue and return the item
    T peek();               // return the data item at the front of the queue without removing it
    boolean isEmpty();      // return true if the queue is empty otherwise return false
    int getLength();        // return the number of elements currently in the queue
}

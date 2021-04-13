/**
 * Methods for the priority queue
 * @param <T> T is a data type that must implement IPrioirty and IKey
 */
public interface IPriorityQueue<T extends IPriority & IKey<Integer>> {

    void push(T node);  // place node into priority queue
    T pop();            // remove highest priority node from priority queue
    T peek();           // get (do not remove) highest priority node in priority queue
    boolean isEmpty();  // return true if the priority queue is empty
    int getLength();    // return the number of nodes in the queue

}

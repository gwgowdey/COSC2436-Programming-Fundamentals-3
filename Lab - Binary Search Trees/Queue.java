public class Queue<T> implements IQueue<T> {

    private IList<T> list; // use a list for the stack

    /**
     * Default constructor to instantiate linked list
     */
    public Queue() {
        list = new LinkedList<>();
    }

    /**
     * Constructor to instantiate linked list and place element data in stack
     * @param data element to be placed in the queue
     */
    public Queue(T data) {
        this();
        list.append(data);
    }

    /**
     * Insert element data to back of queue
     * @param data element to insert to queue
     */
    @Override
    public void enqueue(T data) {
        list.append(data);
    }

    /**
     * Remove element from front of queue
     * @return the element removed from the front of queue
     */
    @Override
    public T dequeue() {
        return list.removeFront();
    }

    /**
     * Examine the element at the front of the queue
     * @return the element at the front of the queue
     */
    @Override
    public T peek() {
        return list.get(0);
    }

    /**
     * Determine if there are any elements on the queue
     * @return true if the queue has no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Get the number of elements on the queue
     * @return the number of elements on the queue
     */
    @Override
    public int getLength() {
        return list.getLength();
    }
}

import java.util.*;

/**
 * <h1>Singly Linked List</h1>
 * <p>
 * This is a class to create a singly linked list *
 * </p>
 *
 * @Author: Douglas Atkinson
 * @version 0.1
 * @since 02/16/2021
 * @see tutorialspoint.com/java/java_documentation.htm
 */
public class LinkedList<T> implements IList<T> {

    // instance variables
    protected ListNode<T> head;
    protected ListNode<T> tail;
    protected int size;

    // Constructors
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList(T data) {
        head = new ListNode<>(data);
        tail = head;
        size = 1;
    }

    public LinkedList(T[] array) {
        this();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
    }

    // Copy constructor
    public LinkedList(LinkedList<T> rhs) {
        this();
        ListNode<T> currNode = rhs.head;
        while (currNode != null) {
            append(currNode.getData());
            currNode = currNode.getNext();
        }
    }

    // DRY
    // Don't Repeat Yourself
    private void insertFirstNode(T data) {
        head = new ListNode<>(data);
        tail = head;
    }

    /**
     * Place node at end of list
     * @param item is a type T
     */
    @Override
    public void append(T item) {
        // Check if list is empty
        if (head == null) {
            insertFirstNode(item);
        }
        else {
            ListNode<T> node = new ListNode<>(item);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Add a node to the beginning of the list
     * @param item
     */
    @Override
    public void prepend(T item) {
        // Check if list is empty
        if (head == null) {
            insertFirstNode(item);
        }
        else {
            ListNode<T> node = new ListNode<>(item, head);
            head = node;
        }
        size++;
    }

    /**
     * Insert newNode after the node the currNode is pointing to
     * @param currNode points to a node in the list
     * @param newNode is a node object to insert into the list
     */
    private void insertNodeAfter(ListNode<T> currNode, ListNode<T> newNode) {
        if (currNode == tail) {
            tail.setNext(newNode);
            tail = newNode;
        }
        else {
            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);
        }
    }

    /**
     * Add a node with newItem after the node that contains curItem
     * @param curItem
     * @param newItem
     * @return
     */
    @Override
    public boolean insertAfter(T curItem, T newItem) {
        // Check if list is empty
        if (head == null) {
            insertFirstNode(newItem);
            size++;
            return true;
        }
        else {
            // Create a node
            ListNode<T> currNode = head;
            // Iterate through the list, looking for curItem
            while (currNode != null) {
                // Have we found the node with item equal to curItem
                // Don't if (currNode.getDate() == curItem) will not work with objects
                // equals method in all classes (objects)
                // Assuming data type T has correctly overridden the equals method
                if (currNode.getData().equals(curItem)) {
                    ListNode<T> newNode = new ListNode<>(newItem);
                    insertNodeAfter(currNode, newNode);
                    size++;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Insert node with value item after node at position
     * @param item
     * @param position 0-based index, use -1 to insert at front of the list
     * @return
     */
    @Override
    public boolean insertAfter(T item, int position) {
        if (position < -1 || position >= size) {
            throw new IndexOutOfBoundsException("position must be between -1 and size - 1");
        }
        // Is list empty
        if (head == null) {
            insertFirstNode(item);
            size++;
            return true;
        }
        // do we insert at the front
        else if(position == -1) {
            prepend(item);
            size++;
            return true;
        }
        else {
            ListNode<T> newNode = new ListNode<>(item);
            ListNode<T> currNode = head;
            for (int i = 0; i < position; i++) {
                currNode = currNode.getNext();
            }
            insertNodeAfter(currNode, newNode);
            size++;
            return true;
        }
    }

    /**
     * Remove the currNode from the list
     * @param prevNode
     * @param currNode
     */
    private void removeNode(ListNode<T> prevNode, ListNode<T> currNode) {
        // Remove the head node
        if (currNode == head) {
            head = currNode.getNext();
        }
        // Remove the tail node
        else if (currNode == tail) {
            prevNode.setNext(null);
            tail = prevNode;
        }
        // Internal node
        else {
            prevNode.setNext(currNode.getNext());
        }
        // Check if list is not empty
        if (head == null) {
            tail = null;
        }
    }

    /**
     * Remove first node in list that as a value equal to item
     * @param item
     * @return
     */
    @Override
    public T remove(T item) {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove item from empty list");
        }
        ListNode<T> currNode = head;
        ListNode<T> prevNode = null;
        // Iterate through list, look for item at currNode
        while (currNode != null) {
            if (currNode.getData().equals(item)) {
                T itemDeleted = currNode.getData();
                removeNode(prevNode, currNode);
                size--;
            return itemDeleted;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * Remove node in list at position
     * @param position 0-based index, so node 0 is at the head
     * @return
     */
    @Override
    public T removeAt(int position) {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove item from empty list");
        }
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("position must be between 0 and size - 1");
        }
        ListNode<T> currNode = head;
        ListNode<T> prevNode = null;
        // Move currNode to the correct location on the list
        for (int i = 0; i < position; i++) {
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        T itemDeleted = currNode.getData();
        removeNode(prevNode, currNode);
        size--;
        return itemDeleted;
    }

    /**
     * Remove the node at the front of the list
     * @return
     */
    @Override
    public T removeFront() {
        return removeAt(0);
    }

    /**
     * Remove the item at the end of the list
     * @return
     */
    @Override
    public T removeBack() {
        return removeAt(size - 1);
    }

    /**
     * Search for item in list. If we find it, return the item otherwise
     * return null
     * @param item
     * @return
     */
    @Override
    public T search(T item) {
        ListNode<T> currNode = head;
        // Iterate over the list (traversal)
        while (currNode != null) {
            if (currNode.getData().equals(item)) {
                return currNode.getData();
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * Search for item in list. Return true if found, false otherwise.
     * @param item
     * @return
     */
    @Override
    public boolean contains(T item) {
        ListNode<T> currNode = head;
        // Iterate over the list (traversal)
        while (currNode != null) {
            if (currNode.getData().equals(item)) {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }

    @Override
    public void sort() {
        // Ascending sort
        T[] array = toArray();
        // This will only work if the data type of the array (elements in the
        // array) implements Comparable interface
        Arrays.sort(array);
        clear();
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
    }

    /**
     * Sort in ascending order if ascending is true, sort in descending order
     * if ascending is false
     * @param ascending
     */
    @Override
    public void sort(boolean ascending) {
        if (ascending) {
            sort();
        }
        else {
            T[] array = toArray();
            Arrays.sort(array, Collections.reverseOrder());
            clear();
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
    }

    /**
     * Return the value of the item of the node at position
     * @param position 0-based indexed
     * @return
     */
    @Override
    public T get(int position) {
        if (size == 0) {
            throw new NoSuchElementException("Cannot retrieve an element from an empty list");
        }
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("position must be between 0 and size - 1");
        }
        ListNode<T> currNode = head;
        for (int i = 1; i <= position; i++) {
            currNode = currNode.getNext();
        }
        return currNode.getData();
    }

    /**
     * Makes the list empty
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return true if the list is empty, return false otherwise
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of nodes in the list
     * @return
     */
    @Override
    public int getLength() {
        return size;
    }

    /**
     * Print the list from head to tail
     */
    @Override
    public void print() {
        ListNode<T> currNode = head;
        System.out.print("[ ");
        while (currNode != null) {
            if (currNode != head) {
                System.out.print(", ");
            }
            System.out.print(currNode.getData());
            currNode = currNode.getNext();
        }
        System.out.print(" ]");
    }

    /**
     * Print the list from tail to head
     */
    @Override
    public void printReverse() {
        System.out.print("[ ");
        printReverseRecursive(head);
        System.out.print(" ]");
    }

    /**
     * Recursively display each node in the list
     * @return
     */
    private void printReverseRecursive(ListNode<T> node) {
        if (node != null) {
            printReverseRecursive(node.getNext());
            if (node != tail) {
                System.out.print(", ");
            }
            System.out.print(node.getData());
        }
    }

    /**
     * Converts the linked list to an array of type T
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {

        Object[] array = new Object[size];
        ListNode<T> currNode = head;
        int i = 0;
        while (currNode != null) {
            array[i] = currNode.getData();
            currNode = currNode.getNext();
            i++;
        }

        return (T[])array;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements ListIterator<T> {

        ListNode<T> next;
        ListNode<T> cur;
        ListNode<T> prev;

        // Constructor
        LinkedListIterator() {
            next = head;
            cur = null;
            prev = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T data = next.getData();
            prev = cur;
            cur = next;
            next = next.getNext();
            return data;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
            //return false;
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
            //return null;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
            //return 0;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
            //return 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            cur.setData(t);
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}

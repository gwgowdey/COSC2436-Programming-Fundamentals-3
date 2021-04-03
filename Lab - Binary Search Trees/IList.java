// T is the data type for the node data, it is not the node
// interfaces enforce operations
public interface IList<T> extends Iterable<T> {
    // Insertion operations
    void append(T item);    // insert data T at back
    void prepend(T item);   // insert data T at front
    // insert data T newItem after data T curItem
    boolean insertAfter(T curItem, T newItem);
    // insert data T after node at position (use -1 to insert at head)
    boolean insertAfter(T item, int position);

    // Removal operations
    T remove(T item);    // remove first node with data T, return data T
    T removeAt(int position);   // remove node at position
    T removeFront();     // remove the head node
    T removeBack();      // remove the tail node

    // Search operations
    // search for node with data T, if found return T otherwise return null
    T search(T item);
    // search for node with data T, return true if found otherwise return fals
    boolean contains(T item);

    // Sort operations
    void sort();        // sort the list in ascending order
    // sort ascending if parameter is true otherwise sort descending
    void sort(boolean ascending);

    // getter
    T get(int position);    // return data T at position

    // list methods
    void clear();       // remove all nodes from list, make the list empty
    boolean isEmpty();  // return true if the list is empty
    int getLength();    // return the number of nodes in the list

    // support methods
    void print();           // print data T from head to tail
    void printReverse();    // print data T from tail to head
    T[] toArray();          // convert list to an array of T
}

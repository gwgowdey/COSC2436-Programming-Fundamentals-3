public interface IStack<T> {

    void push(T data);  // insert data at the top of the stack
    T pop();            // remove data from the top of the stack and return the item
    T peek();           // return the data at the top of the stack without removing it
    boolean isEmpty();  // return true if the stack is empty otherwise return false
    int getLength();    // return the number of elements currently on the statck

}

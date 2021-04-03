// T is the data type of the data stored in the node
public class ListNode<T> {
    private T data;
    private ListNode<T> next;
    private ListNode<T> prev;

    // Constructors
    public ListNode(T data, ListNode<T> next, ListNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public ListNode(T data, ListNode<T> next) {
        this(data, next, null);
    }

    public ListNode(T data) {
        this(data, null, null);
    }

    // Setter / getter methods for Data and Next Pointer
    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public ListNode<T> getPrev() {
        return prev;
    }
}

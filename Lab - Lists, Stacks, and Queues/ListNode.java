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

    // Setters / Getters for Data, Next, Prev
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }
}

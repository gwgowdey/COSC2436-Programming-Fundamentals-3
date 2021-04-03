public interface IBinaryTree<T extends Comparable<T>> {

    boolean insert(T data);
    T search(T data);
    T remove(T data);
    boolean contains(T data);
    boolean isEmpty();
    int size();
    int getHeight();
    void inorderTraverse(IVisit<T> visit);
}
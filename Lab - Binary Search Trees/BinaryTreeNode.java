public class BinaryTreeNode<T extends Comparable<T>> {

    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private BinaryTreeNode<T> parent;

    // Constructors
    public BinaryTreeNode() {
        this(null, null, null, null);
    }

    public BinaryTreeNode(T data) {
        this(data, null, null, null);
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this(data, left, right, null);
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T>  right, BinaryTreeNode<T> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public int getLevel() {
        int level = 0;
        BinaryTreeNode<T> curr = parent;
        while (curr != null) {
            level++;
            curr = curr.getParent();
        }
        return level;
    }
}

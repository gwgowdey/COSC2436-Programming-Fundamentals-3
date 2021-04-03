public class BST<T extends Comparable<T>> implements IBinaryTree<T> {

    private BinaryTreeNode<T> root;
    private int nodeCount;

    // Constructor
    public BST() {
        root = null;
        nodeCount = 0;
    }

    public BST(T data) {
        root = new BinaryTreeNode<>(data);
        nodeCount = 1;
    }

    @Override
    public boolean insert(T data) {
        if (root == null) {
            root = new BinaryTreeNode<>(data);
        }
        else {
            insertRecursive(root, data);
        }
        nodeCount++;
        return true;
    }

    private void insertRecursive(BinaryTreeNode<T> parentNode, T data) {

        // data value < parentNode data
        if (data.compareTo(parentNode.getData()) < 0) {
            if (parentNode.getLeft() == null) {
                parentNode.setLeft(new BinaryTreeNode<>(data, null, null, parentNode));
            }
            else {
                insertRecursive(parentNode.getLeft(), data);
            }
        }
        else {
            if (parentNode.getRight() == null) {
                parentNode.setRight(new BinaryTreeNode<>(data, null, null, parentNode));
            }
            else {
                insertRecursive(parentNode.getRight(), data);
            }
        }
    }

    @Override
    public T search(T data) {
        BinaryTreeNode<T> node = searchRecursive(root, data);
        if (node == null) {
            return null;
        }
        else {
            return node.getData();
        }
    }

    private BinaryTreeNode<T> searchRecursive(BinaryTreeNode<T> rootNode, T data) {
        if (rootNode == null) {
            return null;
        }

        if (data.compareTo(rootNode.getData()) == 0) {
            return rootNode;
        }
        else if (data.compareTo(rootNode.getData()) < 0) {
            return searchRecursive(rootNode.getLeft(), data);
        }
        else {
            return searchRecursive(rootNode.getRight(), data);
        }
    }

    @Override
    public T remove(T data) {
        BinaryTreeNode<T> node = searchRecursive(root, data);
        if (node == null) {
            return null;
        }
        BinaryTreeNode<T> parent = node.getParent();
        remove(parent, node);
        nodeCount--;
        return node.getData();
    }

    private void remove(BinaryTreeNode<T> parentNode, BinaryTreeNode<T> nodeToBeRemoved) {
        if (nodeToBeRemoved == null) {
            return;
        }
        // Case 1: Internal node with 2 children
        if (nodeToBeRemoved.getLeft() != null && nodeToBeRemoved.getRight() != null) {
            // Find the successor and successor's parent
            BinaryTreeNode<T> succNode = nodeToBeRemoved.getRight();
            BinaryTreeNode<T> succNodeParent = nodeToBeRemoved;
            while (succNode.getLeft() != null) {
                succNodeParent = succNode;
                succNode = succNode.getLeft();
            }
            // Copy the value from the successor node
            nodeToBeRemoved.setData(succNode.getData());

            // Recursively remove successor
            remove(succNodeParent, succNode);
        }

        // Case 2: Root node (with 1 or 0 children)
        else if (nodeToBeRemoved == root) {
            if (nodeToBeRemoved.getLeft() != null) {
                root = nodeToBeRemoved.getLeft();
            }
            else {
                root = nodeToBeRemoved.getRight();
            }
        }

        // Case 3: Internal node with left child only
        else if (nodeToBeRemoved.getLeft() != null) {
            // Replace node with node's left child
            if (parentNode.getLeft() == nodeToBeRemoved) {
                parentNode.setLeft(nodeToBeRemoved.getLeft());
            }
            else {
                parentNode.setRight(nodeToBeRemoved.getLeft());
            }
        }

        // Case 4: Internal node with right child only OR leaf
        else {
            // Replace node with node's right child
            if (parentNode.getLeft() == nodeToBeRemoved) {
                parentNode.setLeft(nodeToBeRemoved.getRight());
            }
            else {
                parentNode.setRight(nodeToBeRemoved.getRight());
            }
        }
    }

    @Override
    public boolean contains(T data) {
        if (search(data) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode<T> rootNode) {
        if (rootNode == null) {
            return -1;
        }

        int leftHeight = getHeight(rootNode.getLeft());
        int rightHeight = getHeight(rootNode.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public void inorderTraverse(IVisit<T> visit) {
        inorderTraverse(root, visit);
    }

    private void inorderTraverse(BinaryTreeNode<T> rootNode, IVisit<T> visit) {
        if (rootNode == null) {
            return;
        }

        inorderTraverse(rootNode.getLeft(), visit);
        visit.visit(rootNode.getData());
        inorderTraverse(rootNode.getRight(), visit);
    }

    @Override
    public String toString() {

        Queue<BinaryTreeNode<T>> queue = new Queue<>(root);
        String result = "";
        int level = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> curr = queue.dequeue();
            if (curr.getLeft() != null) {
                queue.enqueue(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.enqueue(curr.getRight());
            }
            if (level != curr.getLevel()) {
                result += "\n";
                level = curr.getLevel();
            }
            result += curr.getData() + " ";
        }

        return result;

    }
}

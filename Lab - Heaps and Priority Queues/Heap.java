import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap<T extends IKey<Integer>> {

    private List<T> heap;                   // store the data
    private ComparisonType comparisonType;  // min or max heap
    private String heapType;                // store whether the heap is max or min

    public Heap() {
        this(true);
    }

    /**
     * Constructs a heap. Determines whether the heap is max or min
     * @param isMaxHeap boolean that stores whether the heap should be a max heap
     */
    public Heap(boolean isMaxHeap) {
        if (isMaxHeap) {
            comparisonType = new MaxComparison();
            heapType = "Max";
        }
        else {
            comparisonType = new MinComparison();
            heapType = "Min";
        }

        heap = new ArrayList<>();
    }
    
    public Heap(Heap<T> h) {
        this(h.heapType.equals("Max"));
        heap = new ArrayList<>(h.heap);
    }
    
    @Override
    public Heap<T> clone() {
        Heap<T> temp = new Heap<>(heapType.equals("Max"));
        temp.heap = new ArrayList<>(heap);
        return temp;
    }

    /**
     * Add a node to the heap
     * @param node T is the data being added to the heap
     */
    public void insert(T node) {
        heap.add(node); // ArrayList.add adds the data to the end of the list
        percolateUp(heap.size() - 1);
    }

    /**
     * Remove and return the root of the heap and then re-organize the heap back to heap structure
     * @return the data at the root
     */
    public T remove() {
        // Make sure the heap is not empty
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Cannot remove element from empty heap");
        }
        T temp = heap.get(0);   // position 0 is the root
        // Copy the last leaf node to the root
        heap.set(0, heap.get(heap.size() - 1));
        // Move the new root down to the correct position
        percolateDown(0);
        // Remove the last node
        heap.remove(heap.size() - 1);
        return temp;
    }

    /**
     * Does not remove the node
     * @return the value at the root
     */
    public T get() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Cannot get an element from an empty heap");
        }
        return heap.get(0);
    }

    /**
     * Determine if the heap is empty
     * @return true if heap is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Gets the number of elements in the heap
     * @return an integer for the number of elements in the heap
     */
    public int size() {
        return heap.size();
    }

    /**
     * HeapType is either "MaxHeap" or "MinHeap"
     * @return a string with the value of the type of heap
     */
    public String getHeapType() {
        return heapType + getClass().getSimpleName();
    }

    /**
     * Move a node at index up to a position on the path to the root so the the heap will
     * have correct heap structure
     * Max heap moves node up if child is greater than parent
     * Min heap moves node up if child is less than parent
     * @param nodeIndex an integer of the index of the node to move
     */
    private void percolateUp(int nodeIndex) {
        // Continue to move node at nodeIndex as long as it is not at the root
        while (nodeIndex > 0) {
            // Get the parent of the node at nodeIndex
            int parentIndex = (nodeIndex - 1) / 2;
            // TODO: generalize to use for min or max heap
            // MaxHeap: If child <= parent then it is at correct position
            //if (heap.get(nodeIndex).key() <= heap.get(parentIndex).key()) {
            if (comparisonType.upCompare(heap.get(nodeIndex).key(), heap.get(parentIndex).key())) {
                return;
            }
            else {
                // swap child with parent
                swap(nodeIndex, parentIndex);
                nodeIndex = parentIndex;
            }
        }
    }

    /**
     * Move node at nodeIndex (parent node) down to the correct position to maintain heap structure
     * MaxHeap: node is moved down if less than the child
     * MinHeap: node is moved down if greater than the child
     * @param nodeIndex an integer that is the index of the parent node
     */
    private void percolateDown(int nodeIndex) {
        // Calculate the index of the child node
        int childIndex = 2 * nodeIndex + 1;
        T value = heap.get(nodeIndex);  // get value for later comparison

        while(childIndex < heap.size()) {
            // Find the max or min among the node and all the node's children
            // MaxHeap look for max node
            // MinHeap look for the min node
            // TODO: generalize for both max and min heap
            T maxValue = value;
            int maxIndex = -1;
            // Iterating over both children
            for (int i = 0; i < 2 && i + childIndex < heap.size(); i++) {
                //if (heap.get(i + childIndex).key() > maxValue.key()) {
                if (comparisonType.downCompare(heap.get(i + childIndex).key(), maxValue.key())) {
                    maxValue = heap.get(i + childIndex);
                    maxIndex = i + childIndex;
                }
            }

            // If the value of the child is the same as the value of the parent
            // then stop moving the node down
            if (maxValue.key() == value.key() || maxIndex == -1) return;
            else {
                swap(nodeIndex, maxIndex);
                nodeIndex = maxIndex;
                childIndex = 2 * nodeIndex + 1;
            }
        }

    }

    /**
     * Swaps the values at indices firstIndex and secondIndex
     * @param firstIndex
     * @param secondIndex
     */
    private void swap(int firstIndex, int secondIndex) {
        T temp = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temp);
    }

    // Used in percolateUp and percolateDown for comparing items to move up and down the tree
    private interface ComparisonType {
        boolean upCompare(Integer first, Integer second);
        boolean downCompare(Integer first, Integer second);
    }

    /**
     * This class is used by MaxHeap to make comparisons for percolateUp and percolateDown
     */
    private class MaxComparison implements ComparisonType {

        @Override
        public boolean upCompare(Integer first, Integer second) {
            return first <= second;
        }

        @Override
        public boolean downCompare(Integer first, Integer second) {
            return first > second;
        }
    }

    /**
     * This class is used by MinHeap to make comparisons for percolateUp and percolateDown
     */
    private class MinComparison implements ComparisonType {

        @Override
        public boolean upCompare(Integer first, Integer second) {
            return first >= second;
        }

        @Override
        public boolean downCompare(Integer first, Integer second) {
            return first < second;
        }
    }
}

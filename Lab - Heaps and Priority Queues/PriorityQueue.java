// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Heaps and Priority Queues.
// Part 2: Implement a PriorityQueue.

/*
Assignment: In the file PriorityQueue.java, you should implement a generic priority queue. 

Make sure you do each of the following (see comments within the code itself).
*/

public class PriorityQueue<T extends IPriority & IKey<Integer>> implements IPriorityQueue<T> 
{
   // 1.) Declare one instance variable of type Heap to store your data.
   private Heap<T> heap;
   
   /*
   2.) Code a one parameter constructor that takes a boolean that indicates whether a lower number should have a higher priority. 
   If the parameter is true, then the constructor should instantiate the Heap as a min heap. 
   If the parameter is false then the constructor should instantiate the heap as a max heap.
   */
   public PriorityQueue(boolean isLowValueHighPriority)
   {
      /* Original submission:
      
      if (isLowValueHighPriority)
      {
         // We need a min heap.
         heap = new Heap<>(false);
      }
     
     else
     {
        heap = new Heap<>(true);
     }
     
     */
     // New submission of the above code. More efficient.
     heap = new Heap<>(!isLowValueHighPriority);
   }
   
   /*
   3.) Code a default constructor. This constructor can simply call the one parameter constructor passing it true or you can have 
   the constructor instantiate the Heap as a min heap since that is the default behavior for a priority queue.
   */
   public PriorityQueue()
   {
      this(true);
   }
   
   // 4.) Implement the methods in IPriorityQueue. IPriorityQueue has five methods you should implement each one.
   // Place node into priority queue.
   public void push(T node)
   {
      heap.insert(node);
   }
   
   // Remove highest priority node from priority queue. 
   public T pop()
    {
       return heap.remove();
    }
    
   // Get (do not remove) highest priority node in priority queue.             
   public T peek()
    {
       return heap.get();
    }
    
    // Return true if the priority queue is empty.
    public boolean isEmpty()
    {
      return heap.isEmpty();
    }   
      
   // Return the number of nodes in the queue.
   public int getLength()
   {
      return heap.size();
   }
}
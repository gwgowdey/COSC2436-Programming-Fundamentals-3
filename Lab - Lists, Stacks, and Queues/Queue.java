// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Lists, Stacks, and Queues.
// Part 2: Implement a Queue.

/* 
Assignment: In the file Queue.java, you should implement a generic queue.
Make sure you do each of the following (see comments within the code itself).
*/

public class Queue<T> implements IQueue<T>
{
   /* 1.) Declare one instance variable of type LinkedList or type IList. */
   private IList<T> list;
   
   /* 2.) Code a default constructor that instantiates your linked list. */
   public Queue()
   {
      list = new LinkedList<>();
   }
   
   /* 
   3.) Code a one parameter constructor that takes a generic parameter for data
   and places it in your queue. 
   */
   public Queue(T data)
   {
      this();
      list.append(data);
   }
   
   /* 
   4.) Implement IQueue. IQueue has five methods you should implement each one.
   */
   public void enqueue(T data)
   {
      list.append(data);
   }  
   
   public T dequeue()
   {
      return list.removeFront();
   }
        
   public T peek()
   {
      return list.get(0);
   }

   public boolean isEmpty()
   {
      return list.isEmpty();
   }
   
   public int getLength()
   {
      return list.getLength();
   }
}  
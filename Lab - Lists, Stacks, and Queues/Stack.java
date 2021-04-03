// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Lists, Stacks, and Queues.
// Part 1: Implement a Stack.

/*
Assignment: In the file Stack.Java, you should implement a generic stack. 
Make sure you do each of the following (see comments within the code itself).
*/

import java.util.Scanner;

public class Stack<T> implements IStack<T>
{
   /* 1.) Declare one instance variable of type LinkedList or type IList. */
   private IList<T> stack;
   
   /* 2.) Code a default constructor that instantiates your linked list. */
   public Stack()
   {
      stack = new LinkedList<T>();
   }   
   
   /* 
   3.) Code a one parameter constructor that takes a generic parameter for data 
   and places it in your stack. 
   */
   public Stack(T data)
   {
      this();
      stack.prepend(data);
   }   
   
   /* 4.) Implement IStack. IStack has five methods you should implement each one. */
   public void push(T data)
   {
      stack.prepend(data);
   }
   
   public T pop()
   {
      return stack.removeFront();
   }
   
   public T peek()
   {
      return stack.get(0);
   }
   
   public boolean isEmpty()
   {
      return stack.isEmpty();    
   }
   
   public int getLength()
   {
      return stack.getLength();  
   }  
}
// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Lists, Stacks, and Queues.
// Part 3: StackQueueDriver Methods.

/*
Assignment: In the file StackQueueDriver.java write the two methods reverseString 
and separateEvenOdd.
*/

import java.util.Scanner;

public class StackQueueDriver {

    public static void main(String[] args) {

        
        Scanner in = new Scanner(System.in);
        String msg;

        System.out.println("Enter a message:");
        msg = in.nextLine();

        System.out.println("msg: " + msg);
        System.out.println("Reversed ");
        reverseString(msg);
        System.out.println();

        Queue<Integer> startQ = new Queue<>();
        Queue<Integer> evenQ = new Queue<>();
        Queue<Integer> oddQ = new Queue<>();
        System.out.println("Integers in the start queue:");

        for (int i = 0; i < 10; i++) {
            int num = in.nextInt();
            System.out.print(num + " ");
            startQ.enqueue(num);
        }
        System.out.println();
        
        separateEvenOdd(startQ, evenQ, oddQ);
        System.out.println("Even numbers:");
        while (!evenQ.isEmpty()) {
            System.out.print(evenQ.dequeue() + " ");
        }
        System.out.println();
        System.out.println("Odd numbers:");
        while (!oddQ.isEmpty()) {
            System.out.print(oddQ.dequeue() + " ");
        }
        System.out.println();
     }

    /*
    In this method you should instantiate a stack. Use the stack to output the message in reverse. 
    You must use the Stack class that you implemented in Part 1. Do not use any of Java's library functions for reversing.

    Ex: 
    Enter a message:
    I love Java
    msg: I love Java
    Reversed
    avaJ evol I

    Your method will only output the last line in the example above. The first four lines are output in main. 
    After you output the reverse message make sure you output a new line.
    */
    public static void reverseString(String message)
    {
      Stack<Character> stack = new Stack<>();
      
      for (int i = 0; i < message.length(); i++)
      {
         stack.push(message.charAt(i));
      }
      
      while (!stack.isEmpty())
      {
         System.out.print(stack.pop());
      }
    }
    
    /*
    In this method, queueStart will contain an undetermined number of integers. You should place all the even integers in 
    queueStart into queueEven and all the odd integers into queueOdd. You do not need to output anything. 
    main will output the integers placed into queueEven and queueOdd.

    Ex:
    Integers in the start queue:
    1 2 3 4 5 6 7 8 9 10
    1 2 3 4 5 6 7 8 9 10 
    Even numbers:
    2 4 6 8 10 
    Odd numbers:
    1 3 5 7 9

    All the output above will be provided by main. Your method should not do any output. Your method is responsible for making 
    certain the correct values are placed in queueEven and queueOdd so that the output will be correct.
    */
    public static void separateEvenOdd(Queue<Integer> queueStart, Queue<Integer> queueEven, Queue<Integer> queueOdd) 
    {
       while (!queueStart.isEmpty())
       {
          int num = queueStart.dequeue();
          if (num % 2 == 0)
          {
             queueEven.enqueue(num);
          }
          else 
          {
             queueOdd.enqueue(num);
          }
       }
    }
}
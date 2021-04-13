// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Heaps and Priority Queues.
// Part 1: Implement IPriority.

/*
Assignment: In the file IPriority.java, you should implement an interface that will need to be used by any data type 
that contained by the priority queue. 

Make sure you do each of the following (see comments within the code itself).

Note: This interface will only have two methods.
*/

public interface IPriority
{
   // 1.) Write a method declaration for the getter. Use the signature Integer getPriority();
   Integer getPriority();
   // 2.) Write a method declaration for the setter. Use the signature void setPriority(Integer priority);
   void setPriority (Integer priority);
}
// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Heaps and Priority Queues.
// Part 3: Implement PriorityQueueDriver Methods.

/*
Assignment: In the file PriorityQueueDriver.java implement the methods main() and heapSort().
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PriorityQueueDriver 
{
   /*
   1.) public static void main(String[] args)
   You should do the following in main (see comments within the code itself).
   */
   public static void main(String[] args) 
   {
      Scanner scnr = new Scanner(System.in);
      
      // 1.) Instantiate a PriorityQueue<Student>.
      PriorityQueue<Student> pq = new PriorityQueue<>();
      
      // 2.) Pass your PriorityQueue<Student> variable to readInData(...).
      readInData(pq);
      
      // 3.) Pass the value true to the Student class method setUsePriority(...)
      Student.setUsePriority(true);
      
      // 4.) Output all the students in priority order (see output example below).
      System.out.println("Students will register in the following order:");
      while (!pq.isEmpty())
      {
         System.out.println(pq.pop());
      }
      
      // 5.) Instantiate a max heap for Student.
      Heap<Student> studentHeap = new Heap<>();
      
      // 6.) Pass false to the Student class method setKeyIsId(...).
      Student.setKeyIsId(false);
      
      // 7.) Pass false to the Student class method setUsePriority(...).
      Student.setUsePriority(false);
      
      // 8.) Pass your Heap<Student> variable to ReadIn.
      readInData(studentHeap);
      
      // 9.) Declare an array of Student.
      Student[] students;
      
      // 10.) Pass your Heap<Student> variable to heapSort(...). Assign the return value to your Student array.
      students = heapSort(studentHeap);
      
      // 11.) Output the students sorted by GPA in descending order.
      System.out.println();
      System.out.println("Students descending sort by GPA:");
      for (Student s : students)
      {
         System.out.println(s);  
      }
   }
   
   /*
   2.) public static Student[] heapSort(Heap<Student> studentHeap)
   You should do the following in the heapSort method (see comments within the code itself).
   */
   public static Student[] heapSort(Heap<Student> studentHeap) 
   {
      // 1.) Using the clone(...) method in the class Heap, instantiate a new Heap<Student> assigning a copy of heapStudent.
      Heap<Student> tempStudents = studentHeap.clone();
      
      // 2.) Instantiate an array of Student with the same size as studentHeap.
      Student[] students = new Student[studentHeap.size()];
      
      // 3.) From your copy of studentHeap, remove each Student placing it in the array.
      int i = 0;
      while (!tempStudents.isEmpty())
      {
         students[i] = tempStudents.remove();
         i++;
      }
      // 4. Return the array.
      return students;
      
      /*
      Ex.) Assume that your students.txt file has the following three students.

      3833 3.372 FRESHMAN
      8241 3.501 SENIOR
      4492 2.698 SOPHOMORE

      The output should be the following:

      Students will register in the following order:
      8241 3.501 Senior
      4492 2.698 Sophomore
      3833 3.372 Freshman

      Students descending sort by GPA:
      8241 3.501 Senior
      3833 3.372 Freshman
      4492 2.698 Sophomore
      */
   }
   
   private static void readInData(PriorityQueue<Student> students) 
   {
        // Open text file
        int id;
        double gpa;
        String classification;

        try 
        {
            Scanner myReader = new Scanner(new File("students.txt"));
            while (myReader.hasNextLine()) 
            {
                // Each record has three lines
                // First line is id
                id = myReader.nextInt();

                // Second line should be the gpa
                gpa = myReader.nextDouble();

                // Burn the whitespace left by nextDouble
                myReader.nextLine();

                // Third line is classification
                classification = myReader.nextLine();

                // Instantiate a student and put in priority queue
                try 
                {
                    students.push(new Student(id, gpa, Student.Classification.valueOf(classification)));
                }
                
                catch(IllegalArgumentException e) 
                {
                    System.out.println(classification);
                    System.exit(0);
                }
            }
            myReader.close();
        } 
        
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void readInData(Heap<Student> students) 
    {
        // Open text file
        int id;
        double gpa;
        String classification;

        try 
        {
            Scanner myReader = new Scanner(new File("students.txt"));
            while (myReader.hasNextLine()) 
            {
                // Each record has three lines
                // First line is id
                id = myReader.nextInt();

                // Second line should be the gpa
                gpa = myReader.nextDouble();

                // Burn the whitespace left by nextDouble
                myReader.nextLine();

                // Third line is classification
                classification = myReader.nextLine();

                // Instantiate a student and put in priority queue
                try 
                {
                    students.insert(new Student(id, gpa, Student.Classification.valueOf(classification)));
                }
                
                catch(IllegalArgumentException e) 
                {
                    System.out.println(classification);
                    System.exit(0);
                }
            }
            myReader.close();
        } 
        
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
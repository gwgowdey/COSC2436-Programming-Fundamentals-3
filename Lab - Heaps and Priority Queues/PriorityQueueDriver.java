import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PriorityQueueDriver {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      /* Type your code here. */
   }
   
   public static Student[] heapSort(Heap<Student> studentHeap) {
      /* Type your code here. */
   }
   
   private static void readInData(PriorityQueue<Student> students) {
        // Open text file
        int id;
        double gpa;
        String classification;

        try {
            Scanner myReader = new Scanner(new File("students.txt"));
            while (myReader.hasNextLine()) {
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
                try {
                    students.push(new Student(id, gpa, Student.Classification.valueOf(classification)));
                }
                catch(IllegalArgumentException e) {
                    System.out.println(classification);
                    System.exit(0);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void readInData(Heap<Student> students) {
        // Open text file
        int id;
        double gpa;
        String classification;

        try {
            Scanner myReader = new Scanner(new File("students.txt"));
            while (myReader.hasNextLine()) {
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
                try {
                    students.insert(new Student(id, gpa, Student.Classification.valueOf(classification)));
                }
                catch(IllegalArgumentException e) {
                    System.out.println(classification);
                    System.exit(0);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

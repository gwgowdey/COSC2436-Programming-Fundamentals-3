// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: Binary Search Trees.

/*
Assignment:

Your program should do the following:

- Instantiate a binary search tree for type Item.
- Read the file into a single string variable. The method static String readInData(String fileName) will do this for you.* 
  *You just need to call the method, passing it the name of the file and assigning the return value to a string.
- Iterate through each word in the string variable. For each word.
    - Preprocess the word by making it all lower case and removing any punctuation.
    - Ignore the word if it is empty or blank.
    - Ignore the word if it starts with a digit.
    - Instantiate an Item object with the word.
    - If the item object is not in the binary search tree then add it to the tree.
    - If the item is already in the binary search tree then increment the item.
- Output the number of unique words in the tree (see example output).
- Output a list of the first 20 words in the tree with the number of times the word appears in the document.* 
  *The list should be in alphabetical order with one word/frequency pair per line.
- Output the most common word in the document along with its frequency.

Example Output:
"
Number of unique words: 6710
a: 1567
aaagh: 1
abandoned: 10
abandonedproperty: 1
abandoning: 1
abbott: 2
abe: 5
ability: 1
able: 22
aboard: 18
abolished: 1
about: 224
above: 41
abrades: 1
absence: 2
absolute: 5
absolutely: 4
absolutes: 1
abstinence: 1
abstracted: 1
Most frequent word: the @ 3508
"
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordFrequencyCounter 
{
   
   public static void main(String[] args) 
   {
      // Instantiate a binary search tree.
      IBinaryTree<Item> bst = new BST<>();
      
      String text = readInData("CosmicComputer.txt");
      
      // Split the document into an array of words and iterate through each word.
      for (String word : text.split("\\s+"))
      {
         // Make it lower case.
         // Strings are immutable.
         word = word.toLowerCase();
         
         // Remove punctuation.
         word = word.replaceAll( /* What you want to replace. */ "\\p{Punct}", /* What you want to replace it with. */ "");
         
         // If empty or blank or starts with digit.
         if (word.isEmpty() || word.isBlank() || Character.isDigit(word.charAt(0)))
         {
            continue;
         }
         
         // If we get here we should have a word that all lowercase, no punctuation, no digit at the start, at least once character.
         // Make an Item object from the word.
         Item item = new Item(word);
         
         
         // Check if the item is in the tree.
         if (bst.contains(item)) 
         {
             // Does contain item.
             item = bst.search(item);
             item.incrementCount();
         }   
         
         else 
         {
            // Does not contain item - add the item.
            bst.insert(item);
         }  
      }
      // We are done building the tree - it is populated.
      
      // Number of unique words: 6710.
      System.out.println("Number of unique words: " + bst.size());
      
      // Traverse the tree, passing in the Visit class.
      VisitItem visitItem = new VisitItem();
      bst.inorderTraverse(visitItem);
      
      // Output most common word with frequency.
      // Most frequent word: the @ 3508.
      System.out.println("Most frequent word: " + visitItem.getMaxWord() + " @ " + visitItem.getMaxCount());
   }
   
   static String readInData(String fileName) 
   {
        Path filePath = Path.of(fileName);
        String fileContents = "";
        try 
        {
            fileContents = Files.readString(filePath);
        }
        catch (IOException e) 
        {
            System.out.println("Exception thrown: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return fileContents;
    }
}

class Item implements Comparable<Item> 
{

    private String word;
    private int count;

    public Item(String word, int count) 
    {
        this.word = word;
        this.count = count;
    }

    public Item(String word) 
    {
        this(word, 1);
    }

    public String getWord() 
    {
        return word;
    }

    public int getCount() 
    {
        return count;
    }

    public void setCount(int count) 
    {
        this.count = count;
    }

    public void incrementCount() 
    {
        count++;
    }

    @Override
    public String toString() 
    {
        return word + ": " + count;
    }

    @Override
    public int compareTo(Item o) 
    {
        return word.compareTo(o.getWord());
    }
}

class VisitItem implements IVisit<Item> 
{
    private int count = 0;
    private String maxWord = "";
    private int maxCount = 0;
    private Item item;

    @Override
    public void visit(Item data) 
    {

        if (data.getCount() > maxCount) 
        {
            maxCount = data.getCount();
            maxWord = data.getWord();
        }

        if (count < 20) 
        {
            System.out.println(data);
            count++;
        }
    }

    public String getMaxWord() 
    {
        return maxWord;
    }

    public int getMaxCount() 
    {
        return maxCount;
    }
}
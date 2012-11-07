/*
    * Author:  Iam Student, istudent@fit.edu
    * Course:  CSE 1002, Section 0X, Spring 2008
    * Project: Lab 04, working with 2D Arrays
    */
   
   import java.io.*;

   public class Lab04 { 

      public static void main(String[] args)  throws Exception  {   

         if(args.length == 2)  {

            System.out.println("\nCSE 1002 Lab 4 - Mauricio Zepeda\n");

            int dimension = Integer.parseInt(args[1]);

            LetterArray letters = new LetterArray(args[0], dimension);

            letters.printAcross();
            
            letters.printDown();
            
            letters.printBackForth();
            
            letters.printDiagonal();

            letters.closeFile();

            System.out.println();
            
         }
      }
   }
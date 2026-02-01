// Brandon Yan
// CS 143
// HW Title and Core Topics: HW #1: Sudoku #1 (Board Setup) 2D arrays, reading from a file, creating a class (fields, constructors, toString)
//
// This program will make and print a 'board' on command
//
// pre: and post: comments before each method in your class
// ok

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SudokuBoard {
   private int[][] board; 
   
   // pre: file must exist
   public SudokuBoard(String fileName) {
      board = new int[9][9];
      
      try {
         Scanner scanner = new Scanner(new File(fileName));
         
         int row = 0;
         while (scanner.hasNextLine() && row < 9) {
             String line = scanner.nextLine();
   
             for (int col = 0; col < 9; col++) {
                 char cell = line.charAt(col);
                 
                 if (cell == '.') {
                     board[row][col] = 0; 
                 } else {
                     board[row][col] = Character.getNumericValue(cell); 
                 }
             }
             row++;
         }
      } catch (FileNotFoundException e) {
         System.out.println("Error: File '" + fileName + "' not found");
         // Make empty board if file not found
         for (int i = 0; i < 9; i++) {
             for (int j = 0; j < 9; j++) {
                 board[i][j] = 0;
             }
         }
      }
   }
   //post: data from file is loaded into the board matrix. if file does not exist, empty board
      
   //pre: n/a
   public String toString() {    
      String result =  "";
        for (int row = 0; row < 9; row++) {            
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    result += ".";
                } else {
                    result += board[row][col];
                }
            }
            
            result += "\n";
        }
        return result;      
   }
   //pos: returns constructed string of raw board
} 


/*
Paste the output from JGrasp here.
Altering output will earn you an automatic zero for the assignment.


 2..1.5..3
 .54...71.
 .1.2.3.8.
 6.28.73.4
 .........
 1.53.98.6
 .2.7.1.6.
 .81...24.
 7..4.2..1
*/
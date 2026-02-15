// Brandon Yan
// CS 143
// HW Title and Core Topics: HW #3 recursive stuff

// This program will make and print a 'board' on command
//
// pre: and post: comments before each method in your class
// ok

import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SudokuBoard {
   private int[][] board; 
   private boolean valid = true; // my patchy way of handling dirty data errors
   
   private void EmptyBoard(int[][] board) {
      for (int i = 0; i < 9; i++) {
             for (int j = 0; j < 9; j++) {
                 board[i][j] = 0;
             }
         }
   }
   
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
                     if (!Character.isDigit(cell)) {
                        EmptyBoard(board);
                        valid = false;
                        return;
                     }
                     board[row][col] = Character.getNumericValue(cell); 
                 }
             }
             row++;
         }
      } catch (FileNotFoundException e) {
         System.out.println("Error: File '" + fileName + "' not found");
         // Make empty board if file not found
         EmptyBoard(board);
      }
   }
   //post: data from file is loaded into the board matrix. if file does not exist, empty board
   
   
   //  https://tenor.com/view/brain-skull-skeleton-toaster-fork-toaster-gif-12236737752114451464 (I tried being fancy twice but it didn't work ;-;)
   // pre: board must exist & lord must be with us for it to work
   public boolean isValid(){
      
      ArrayList<Integer> subBoardValues; 
      boolean collumScanned = false;
      for (int row = 0; row < 9; row ++)  {
         ArrayList<Integer> RowValues = new ArrayList<>();
         for (int x = 0; x < 9; x++) {
            int cell = board[x][row];

           if (cell > 9 && cell < 0) {
               //System.out.println("not 0-9");
               return false; // is not 0-9. bord is int matrix, garanteed to be number
           }
            
            if (RowValues.contains(cell)) { // goes across board row, checks for dupes
               
               //System.out.println(RowValues + "row dupe" + cell);
               return false;
            } else if (cell != 0) {
               RowValues.add(cell);
            }
            
            if (x % 3 == 0 && row % 3 == 0) { //if on the top left corner of a 3x3 grid
               subBoardValues = new ArrayList<>();
               for (int x2 = 0; x2 < 3; x2++) {
                  for (int y2 = 0; y2 < 3; y2++) {
                     int subcell = board[x + x2][row + y2];
                     if (subBoardValues.contains(subcell)) {
                        //System.out.println("3x3 dupe");
                        return false;
                     } else if (subcell != 0) {
                        subBoardValues.add(subcell);
                     }
                  }
               }
            }
    
            
            if (!collumScanned) { // goes down collum for as long as we are on row 0
               ArrayList<Integer> ColValues = new ArrayList<>();
               for (int y = 0; y < 9; y++) {
                  int c = board[x][y];
                 
                  if (ColValues.contains(c)) {
                     //System.out.println(ColValues + " collum dupe " + c);
                     return false;
                  } else if (c != 0){
                     ColValues.add(c);
                  }
               }
               
               if (x == 9) {
                  collumScanned = true;
               }
            }
         }   
      }
      return valid;    
   }
   // post:
   // returns true if none of the follow are true:
   // any cell is not 0-9, no dupes of numbers in: 3x3 groups, whole rows, whole collums
  
   // pre: computer is on
   public boolean isSolved(){
      int total = 0;
      for (int x = 0; x < 9; x++) {
         for (int y = 0; y < 9; y++) {
            total += board[x][y];
         }  
      }
      return (total == 405); // if solved and valid, sum garanteed to be (1 + 2 + ... 9) * 9
      
   }
   // post: returns if valid and if board complete    
   
 
   //pre: n/a
   public boolean solve() {   
       if (isSolved()) {
           return true;
       } else if (!isValid()) {
           return false;
       }
       
       for (int x = 0; x < 9; x++) {
           for (int y = 0; y < 9; y++) {  
               if (board[x][y] == 0) {
                   for (int z = 1; z <= 9; z++) {  
                       board[x][y] = z;
                       
                       if (isValid()) {
                           if (solve()) {  
                               return true;  
                           }
                       }
                       
                       
                       board[x][y] = 0;  
                   }
                   
                   
                   return false;
               }
           }
       }
       
       return false;  
   }
   //post: returns if board can be solved, and i'm pretty sure actually solves the board.
   
   
   
   
   //pre: n/a
   public String toString() {    
      String result =  "";
        for (int row = 0; row < 9; row++) {            
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    result += "0";//".";
                } else {
                    result += board[row][col];
                }
            }
            
            result += "\n";
        }
        return result;      
   }
   //post: returns constructed string of raw board
   
   
} 



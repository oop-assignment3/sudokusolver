package sudokusolver;

/*
* This is our main class which contains the SudokuSolver(), as well as, the
* main() function. 
*/

import java.util.*;
import java.io.*;

public class SudokuSolver {
  
  //The function which solves the grid using backtracking
  static boolean SolveSudoku(Grid g)
  {
    CellPointer p = new CellPointer();
    
    if(!g.FindUnassigned(p)) //Find unnasigned value and set CellPointer to it
      return true;
    
    for(int num = 1; num <= 9; num++) // check for each value from 1 to 9
    {
      if(g.noConflicts(num, p))
      {
        g.set_val(num, p);
        if(SolveSudoku(g)) // solving the next cell
          return true;
        g.set_val(0, p); //This is done when the previous recursive call returns false
      }
    }
    return false; //This the statement that triggers backtracking
  }
  
  public static void main(String[] args) throws InvalidValException, InvalidLengthException        
  {
    int option; //choice between console input and file
    Scanner input = new Scanner(System.in);
    
    System.out.println("Enter you choice\n1. Input from keyboard\n2. Input from file");
    option = input.nextInt();
     
    int grid[][] = new int[9][9];
    Grid g = new Grid(grid);
      
    String file_name = null;
    
    switch(option)
    {
      case 1: g.getUserInput();
              break;
      case 2: System.out.println("Enter file name: ");
              file_name = input.next();
              g.getFileInput(file_name);
              break;     
      default: System.out.println("Invalid Option");return; 
    }
      
    //Calculating the run time
    double start_time = System.currentTimeMillis();
    boolean solved = SolveSudoku(g);
    double end_time = System.currentTimeMillis();
    double total_time = (end_time - start_time)/1000;
    
    //Printing the solution and runtime. Also writing the solution to a file
    if(solved)
    {
      System.out.println("\nThe solved sudoku is:\n\n" + g.toString());
      System.out.println("\nRun time: " + total_time);
      
      try(FileWriter fw = new FileWriter(file_name+".sol"))
      {
        fw.write(g.toString());
        System.out.println("\nSolution file: " + file_name + ".sol");
      }catch(IOException e)
      {
        System.out.println(e);
      }
    }
    else System.out.println("Invalid puzzle");
  }   
}

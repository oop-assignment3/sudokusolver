package sudokusolver;

/*
* This is our main class which contains the SudokuSolver(), as well as, the main()
* function. 
*/

import java.util.*;
import java.io.*;

public class SudokuSolver {
  
  //NOTE: leave the description for this empty
  static boolean SolveSudoku(Grid g)
  {
    CellPointer p = new CellPointer();
    
    if(!g.FindUnassigned(p)) //replace with find first unassigned and noConflicts
      return true;
    
    for(int num = 1; num <= 9; num++) // check for each value from 1 to 9
    {
      if(g.noConflicts(num, p))
      {
        g.set_val(num, p);
        if(SolveSudoku(g)) // solving the next cell
          return true;
        g.set_val(0, p);
      }
    }
    return false;
  }
  
  public static void main(String[] args) throws InvalidValException, InvalidLengthException        
  {
    int n;
    Scanner input = new Scanner(System.in);
    
    System.out.println("Enter you choice\n1. Input from keyboard\n2. Input from file");
    n = input.nextInt();
    String file_name = null; 
    int grid[][] = new int[9][9];
    Grid g = new Grid(grid);
      
    switch(n)
    {
      case 1: g.getUserInput();
              break;
      case 2: System.out.println("Enter file name: ");
              file_name = input.next();
              g.getFileInput(file_name);
              break;     
      default: System.out.println("Invalid Option");return; 
    }
      
    //calculating the run time
    double start_time = System.currentTimeMillis();
    boolean solved = SolveSudoku(g);
    double end_time = System.currentTimeMillis();
    double total_time = (end_time - start_time)/1000;
    
    if(solved)
    {
      if(n == 1)
      {
        System.out.println(g.toString());
        System.out.println("Run time: " + total_time);
      }
      
      if(n == 2)
      {
        try(FileWriter fw = new FileWriter(file_name+".sol"))
        {
          fw.write(g.toString());
        }catch(IOException e)
        {
          System.out.println(e);
        }
      }
      
    }
    else System.out.println("Invalid puzzle");
  }   
}

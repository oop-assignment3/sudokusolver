package sudokusolver;

/*
*The Grid class holds the 9x9 sudoku puzzle matrix and all the helper functions
*for SolveSudoku() in the SudokuSolver class
*/

import java.util.*;

public class Grid {
  
  //the 9x9 grid
  int grid[][];
	 
  Grid(int[][] input_grid)
  {
    grid = input_grid;
  }
  
  //displays the grid. Needs to be more elaborate
  void display()
  {
    for(int i = 0; i < 9; i++)
    {
      for(int j = 0; j < 9; j++)
        System.out.print(grid[i][j]);
      System.out.println("");
    }
    System.out.println("");
  }
  
  //Searches for the first unassigned cell (the value 0) and sets CellPointer p
  //to that value. Also returns a boolean indicating whether it found any unassigned 
  //value or not. This is function is therefore also used to indicate the completion
  //of the proccess
  boolean FindUnassigned(CellPointer p)
  {
    for(int i = 0; i < 9; i++)
      for(int j = 0; j < 9; j++)
        if(grid[i][j] == 0)
        {
          p.set(i, j);
          return true;
        }
    
    return false;
  }
  
  //Checks the corresponding row, column and sub grid of num for conflicts
  boolean noConflicts(int num, CellPointer p)
  {
    //row check
    for(int i = 0; i < 9; i++)
      if(grid[p.row][i] == num && i != p.col)
        return false;
          
    //col check
    for(int j = 0; j < 9; j++)
      if(grid[j][p.col] == num && j != p.row)
        return false;       
    
    //finding sub grid
    int subgrid_r = 0, subgrid_c = 0;
    
    if(checkIn(0, 2, p.row))
      subgrid_r = 0;
    else if(checkIn(3, 5, p.row))
      subgrid_r = 3;
    else if(checkIn(6, 8, p.row))
      subgrid_r = 6;
    if(checkIn(0, 2, p.col))
      subgrid_c = 0;
    else if(checkIn(3, 5, p.col))
      subgrid_c = 3;
    else if(checkIn(6, 8, p.col))
      subgrid_c = 6;
    
    //sub grid check
    for(int i = subgrid_r; i <= subgrid_r + 2; i++)
      for(int j = subgrid_c; j <= subgrid_c + 2; j++)
        if(grid[i][j] == num && i != p.row && j != p.col)
          return false;
    
    return true;
  }
  
  //Helper function for noConflicts()
  boolean checkIn(int start, int end, int val)
  {
    return (val >= start && val <= end);
  }
  
  //Sets the value of num to the cell located at (p.row, p.col)
  void set_val(int num, CellPointer p)
  {
    grid[p.row][p.col] = num;
  }

  void getUserInput() throws InvalidValException
  {
    Scanner input = new Scanner(System.in);
    for(int i = 0;i < 9; i++)
      for(int j=0;j<9;j++)
      {
        System.out.print("Enter the next number: ");
        try
        {
          grid[i][j] = input.nextInt();

          if(grid[i][j] < 0 || grid[i][j] > 9)
            throw new InvalidValException(grid[i][j]);
        }catch(InputMismatchException e1)
        {
          System.out.println("Number input is not an integer");
          return;
        }catch(InvalidValException e2)
        {
          System.out.println(e2);
          return;
        }
      } 
  }
}

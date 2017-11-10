package sudokusolver;

/*
*The Grid class holds the 9x9 sudoku puzzle matrix and all the helper functions
*for SolveSudoku() in the SudokuSolver class
*/

import java.util.*;
import java.io.*;

public class Grid {
  
  //the 9x9 grid
  int grid[][];
  Scanner input = new Scanner(System.in);
	 
  Grid(int[][] input_grid)
  {
    grid = input_grid;
  }
  
  //Returns a string representing the sudoku grid
  @Override
  public String toString()
  {
    String grid_str = new String();
    for(int i = 0; i < 9; i++)
    {
      if( i % 3 == 0 && i != 0)
        grid_str = grid_str + "------+------+-----\n";
      for(int j = 0; j < 9; j++)
      {
        if(j % 3 == 0 && j != 0)
          grid_str = grid_str + "|";
        grid_str = grid_str + grid[i][j] + " ";
      }
      grid_str = grid_str + "\n";
    }
    return grid_str;
  }
  
  //Searches for the first unassigned cell (the value 0) and sets CellPointer p
  //to that value. Also returns a boolean indicating whether it found any unassigned 
  //value or not.
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

  //Gets row-wise input from the user. It also forces the user to input valid 
  //cell values
  void getUserInput()
  {
    String num_row;
    boolean invalid_input = false;
    
    for(int i = 0; i < 9; i++)
    {  
      do
      {
        System.out.print("Enter row " + (i + 1) + ": ");
        num_row = input.nextLine();
        
        try
        {
          if(num_row.length() != 9)
            throw new InvalidLengthException();
        }catch(InvalidLengthException e)
        {
          System.out.println(e);
          invalid_input = true;
          continue;
        }
        
        invalid_input = checkRow(num_row, i);
        
      }while(invalid_input);
    }
  }
  
  //Reads the grid from file file_name
  void getFileInput(String file_name)
  {
    try(FileReader fr = new FileReader(file_name))
    {
      StringBuilder entire_text = new StringBuilder();
      int ch;
      
      while((ch = fr.read()) != -1)
        entire_text.append((char)ch);
      
      String rows[] = entire_text.toString().split("\n");
      
      for(int i = 0; i < 9; i++)
      {
        if(checkRow(rows[i], i))
          System.exit(0);
      }
      
    }catch(IOException e)
    {
      System.out.println(e);
      System.exit(0);
    }
  }
  
  //Checks a row num_row(as a String value) for length and 
  boolean checkRow(String num_row, int i)
  {
    for(int j = 0; j < 9; j++)
    {
      grid[i][j] = num_row.charAt(j) - '0';
      try
      {
        if(grid[i][j] < 0 || grid[i][j] > 9)
          throw new InvalidValException(grid[i][j]);
      }catch(InvalidValException e)
      {
        System.out.println(e);
        return true;
      }
    }
    return false;
  }
  
}

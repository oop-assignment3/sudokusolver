package sudokusolver;

/**
 *
 * @author aranya
 */
public class Grid {
  int grid[][];
  CellPointer p; // current pointer to cell
	 
  Grid(int[][] input_grid)
  {
    grid = input_grid;
    p = new CellPointer();
  }
  
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
  
  boolean FindUnassigned()
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
  
  boolean NoConflicts(int num)
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
  
  boolean checkIn(int start, int end, int val)
  {
    return (val >= start && val <= end);
  }
  void set_val(int num)
  {
    grid[p.row][p.col] = num;
    //System.out.println(p.row + " " + p.col + " Num: " + num);
  }
}
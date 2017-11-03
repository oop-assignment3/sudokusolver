package sudokusolver;
/*
* This is our main class which contains the SudokuSolver(), as well as, the main()
* function. 
*/

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
  
  public static void main(String[] args)
  {
    int grid[][] = {{5,3,0,0,7,0,0,0,0},
                    {6,0,0,1,9,5,0,0,0},
                    {0,9,8,0,0,0,0,6,0},
                    {8,0,0,0,6,0,0,0,3},
                    {4,0,0,8,0,3,0,0,1},
                    {7,0,0,0,2,0,0,0,6},
                    {0,6,0,0,0,0,2,8,0},
                    {0,0,0,4,1,9,0,0,5},
                    {0,0,0,0,8,0,0,7,9}};
    
    //this one was designed to work against or algo. It takes 7 secs.
    int grid_bad[][] = {{0,0,0,0,0,0,0,0,0},
                     {0,0,0,0,0,3,0,8,5},
                     {0,0,1,0,2,0,0,0,0},
                     {0,0,0,5,0,7,0,0,0},
                     {0,0,4,0,0,0,1,0,0},
                     {0,9,0,0,0,0,0,0,0},
                     {5,0,0,0,0,0,0,7,3},
                     {0,0,2,0,1,0,0,0,0},
                     {0,0,0,0,4,0,0,0,9}};
    
   Grid g = new Grid(grid);
   //calculating the run time
   double start_time = System.currentTimeMillis();
   SolveSudoku(g);
   double end_time = System.currentTimeMillis();
   g.display();
   double total_time = (end_time - start_time)/1000;
   System.out.println("Run time: " + total_time);
  }   
}
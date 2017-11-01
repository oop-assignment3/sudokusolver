package sudokusolver;

/**
 *
 * @author aranya
 */
public class SudokuSolver {
  static int depth = 0;
  static boolean SolveSudoku(Grid g)
  {
    System.out.println(++depth + " " + g.p.row + " " + g.p.col);
    g.display();
    if(!g.FindUnassigned()) //replace with find first unassigned and noConflicts
      return true;
    for(int num = 1; num <= 9; num++)
    {
      if(g.NoConflicts(num))
      {
        g.set_val(num);
        if(SolveSudoku(g))
          return true;
        g.set_val(0);
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
    
    int grid2[][] = {{0,0,3,0,2,0,6,0,0},
                     {9,0,0,3,0,5,0,0,1},
                     {0,0,1,8,0,6,4,0,0},
                     {0,0,8,1,0,2,9,0,0},
                     {7,0,0,0,0,0,0,0,8},
                     {0,0,6,7,0,8,2,0,0},
                     {0,0,2,6,0,9,5,0,0},
                     {8,0,0,2,0,3,0,0,9},
                     {0,0,5,0,1,0,3,0,0}};
   Grid g = new Grid(grid2);
   System.out.println(SolveSudoku(g));
   g.display();
  }   
}
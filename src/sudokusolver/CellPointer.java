package sudokusolver;

/*
* CellPointer holds the row and col value of the current cell value in use.
*/
public class CellPointer {
  int row, col;
  
  CellPointer() {}
  void set(int r, int c)
  {
    row = r; 
    col = c;
  }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

/**
 *
 * @author aranya
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

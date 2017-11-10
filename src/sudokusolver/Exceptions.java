/*
 * This file contains our two user defined exceptions.
*/
package sudokusolver;

class InvalidValException extends Exception
{
  int cell_val;
  InvalidValException(int cell_val)
  {
    this.cell_val = cell_val;  
  }
  @Override
  public String toString()
  {
    return "Invalid Cell Value: " + cell_val;
  } 
}

class InvalidLengthException extends Exception
{
  @Override
  public String toString()
  {
    return "Invalid row length.";
  }
}
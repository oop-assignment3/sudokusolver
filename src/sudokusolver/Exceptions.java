
package sudokusolver;

/**
 *
 * @author Admin
 */
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
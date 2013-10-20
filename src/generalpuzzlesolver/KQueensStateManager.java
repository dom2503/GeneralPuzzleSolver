/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

/**
 *
 * @author dominik
 */
public class KQueensStateManager implements LocalStateManager{

  private int size;
  private boolean[][] queenPositions;
  
  public KQueensStateManager(){
    this(8);
  }
  
  public KQueensStateManager(int size){
    this.size = size;
    queenPositions = new boolean[size][size];
  }
  
  public void setQueen(int x, int y){
    queenPositions[x][y] = true;
  }
  
  @Override
  public PuzzleState getNextRandomState() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public double calculateEnergy(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public PuzzleState getNeighbour(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void displayCurrentState() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}

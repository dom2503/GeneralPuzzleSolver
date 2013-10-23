/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.LocalStateManager;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author dominik
 */
public class KQueensStateManager extends LocalStateManager{

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
  public PuzzleState getRandomState() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  @Override
  public PuzzleState getRandomNeighbour(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void displayState(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void initialize(Object[] parameters) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}

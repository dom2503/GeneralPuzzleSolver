/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.LocalStateManager;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dominik
 */
public class KQueensStateManager extends LocalStateManager{

  private KQueensPuzzleState currentState;
  private int size;
  //private boolean[][] queenPositions;
    private int queens[];   
    private final Random random;

  
  public KQueensStateManager(){
    this(4);
  }
  
  public KQueensStateManager(int size){
    this.random = new Random();
    this.size = size;
    this.queens = new int[size];
    this.currentState = new KQueensPuzzleState(size);
    
  }
  
 
  
  @Override
  public PuzzleState getRandomState() {
   KQueensPuzzleState newState = new KQueensPuzzleState(this.currentState);
    
   int BoardSize = this.currentState.getSize();
   for (int row = 0; row < BoardSize; row++) {
            int n = random.nextInt(BoardSize);
             newState.setPossitionQueen(row, n);
         //     newState.queens.setQueens(row, n);//Comprobar aqui
                newState.setQueens(row,n);//[row]=n;//Comprobar aqui

 
          
             
     }
    this.currentState = newState;

    return newState;
  }

  @Override
  public PuzzleState getNeighbour() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void displayCurrentState() {
    this.currentState.display();
  }

  @Override
  public PuzzleState getCurrentState() {
    return this.currentState;
  }
  
}

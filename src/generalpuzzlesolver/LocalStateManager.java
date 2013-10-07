/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

/**
 *
 * @author dominik
 */
abstract public class LocalStateManager {
  
  abstract public PuzzleState getNextRandomState();
  
  abstract public double calculateEnergy(PuzzleState state);
  
  abstract public PuzzleState getNeighbour(PuzzleState state);
}

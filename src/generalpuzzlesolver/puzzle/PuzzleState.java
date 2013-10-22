package generalpuzzlesolver.puzzle;

import java.util.ArrayList;

/**
 * The state of some kind of constraint based puzzle.
 */
public interface PuzzleState {
  
  /**
   * Shows a useful representation of this state.
   */
  public void display();
  
  /**
   * Checks if this is a final state.
   */
  public boolean isFinal();
  
  /**
   * Returns a list of all the areas of this state where the constraints of 
   * the puzzle are broken.
   */
  public ArrayList<Conflict> getConflicts();
}

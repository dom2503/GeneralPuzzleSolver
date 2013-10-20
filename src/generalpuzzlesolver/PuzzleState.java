package generalpuzzlesolver;

import java.util.ArrayList;

/**
 *
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
  
  public ArrayList<Conflict> getConflicts();
}

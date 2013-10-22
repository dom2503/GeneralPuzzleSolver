package generalpuzzlesolver.puzzle;

/**
 * Implementing classes represent a certain kind of puzzle
 */
abstract public class LocalStateManager {
  
  abstract public PuzzleState getRandomState();
  
  /**
   * Selects a random neighbor of the current state.
   * @return 
   */
  abstract public PuzzleState getNeighbour();
  
  abstract public void reset();
  
  abstract public void displayCurrentState();
  
  abstract public PuzzleState getCurrentState();
}

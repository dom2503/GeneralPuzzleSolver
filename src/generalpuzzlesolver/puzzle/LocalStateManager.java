package generalpuzzlesolver.puzzle;

import java.util.Set;

/**
 * Implementing classes represent a certain kind of puzzle.
 */
abstract public class LocalStateManager {
  
  /**
   * Selects an appropriate random state of the puzzle.
   */
  abstract public PuzzleState getRandomState();
  
  /**
   * Selects a random neighbor of the given state.
   * @return 
   */
  abstract public PuzzleState getRandomNeighbour(PuzzleState state);
  
  /**
   * 
   */
  abstract public void reset();
  
  /**
   * Displays the given state of the puzzle in a useful way.
   */
  abstract public void displayState(PuzzleState state);
  
  /**
   * 
   */
  abstract public PuzzleState getLastUsedState();
  
  /**
   * Creates a list of all puzzle states that could be considered 
   * neighbors of the given state.
   */
  abstract public Set<PuzzleState> getAllNeighbors(PuzzleState state);
}

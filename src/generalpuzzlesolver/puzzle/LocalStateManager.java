package generalpuzzlesolver.puzzle;

/**
 * Implementing classes represent a certain kind of puzzle.
 */
abstract public class LocalStateManager {
  
  /**
   * Initializes the first state of this puzzle.
   * 
   * @param parameters Should contain all necessary parameters
   * to create an initial state. This could simply be a string
   * to parse or the name of a file that contains the information.
   */
  abstract public void initialize(Object[] parameters);
  
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
   * Resets this state manager so that it can be restarted.
   */
  abstract public void reset();
  
  /**
   * Displays the given state of the puzzle in a useful way.
   */
  abstract public void displayState(PuzzleState state);
}

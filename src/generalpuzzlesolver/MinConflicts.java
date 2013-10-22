package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;

/**
 * Uses the Min-Conflicts algorithm to solve constraint based puzzles. 
 * 
 * The puzzle should be represented by a specific implementation of a LocalStateManager.
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

  public MinConflicts(int maximumSteps) {
    super(maximumSteps);
  }

  /**
   * The implementation of this algorithm is based on pseudocode from Wikipedia:
   * 
   * https://en.wikipedia.org/w/index.php?title=Min-conflicts_algorithm&oldid=548827708
   */
  @Override
  public PuzzleState run() {
    this.resetStepCount();
    PuzzleState currentState = this.getStateManager().getRandomState();
    PuzzleState nextState;
    
    for (int i = 0; i < this.getMaximumSteps(); i++) {
      if (currentState != null && currentState.isFinal()) {
        return currentState;
      }
      nextState = this.getStateManager().getNeighbour();
      if (this.hasLowerConflicts(currentState, nextState)) {
        currentState = nextState;
      }
      this.incrementStepCount();
    }

    return currentState;
  }

  private boolean hasLowerConflicts(PuzzleState oldBest, PuzzleState evaluate) {
    return oldBest != null
            && evaluate != null
            && evaluate.getConflicts().size() < oldBest.getConflicts().size();
  }

  @Override
  public void reset() {
    this.getStateManager().reset();
  }
}

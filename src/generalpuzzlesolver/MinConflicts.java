package generalpuzzlesolver;

/**
 * Uses the Min-Conflicts algorithm for puzzle solving.
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

  @Override
  public PuzzleState run() {
    PuzzleState currentState = this.getStateManager().getNextRandomState();

    for (int i = 0; i < this.getMaximumSteps(); i++) {
      if (this.getStateManager().isFinal(currentState)) {
        return currentState;
      }
      currentState = this.getStateManager().getNeighbour(currentState);
    }

    return null;
  }

  @Override
  public void reset() {
    this.getStateManager().reset();
  }
}

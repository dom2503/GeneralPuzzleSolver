package generalpuzzlesolver;

/**
 * Uses the Min-Conflicts algorithm to solve constraint based puzzles. The puzzle should be
 * represented by a specific implementation of a LocalStateManager.
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

  public MinConflicts(int maximumSteps) {
    super(maximumSteps);
  }

  @Override
  public PuzzleState run() {
    PuzzleState currentState = this.getStateManager().getCurrentState();
    PuzzleState nextState;

    System.out.println(this.getMaximumSteps());
    
    for (int i = 0; i < this.getMaximumSteps(); i++) {
      if (currentState != null && currentState.isFinal()) {
        return currentState;
      }
      nextState = this.getStateManager().getNeighbour();
      if (this.isConflictMinimizing(currentState, nextState)) {
        currentState = nextState;
      }
    }

    return currentState;
  }

  private boolean isConflictMinimizing(PuzzleState olderState, PuzzleState evaluatingState) {
    return olderState != null
            && evaluatingState != null
            && evaluatingState.getConflicts().size() < olderState.getConflicts().size();
  }

  @Override
  public void reset() {
    this.getStateManager().reset();
  }
}

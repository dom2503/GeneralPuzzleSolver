package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.PuzzleState;
import java.util.ArrayList;

/**
 * Uses the Min-Conflicts algorithm to solve constraint based puzzles.
 *
 * The puzzle should be represented by a specific implementation of a LocalStateManager.
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

  private int restartCounter = 0;

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
    PuzzleState currentState = this.getStateManager().getRandomState();
    //currentState.display();
    PuzzleState nextState;
    int currentConflictCount;
    int nextConflictCount;

    int maximumSteps = this.getMaximumSteps();
    this.resetStepCount();
    for (int i = 0; i < maximumSteps; i++) {
      if (currentState.isFinal()) {
        return currentState;
      }
      boolean restarted = false;
      
      if (restartCounter > 3) {
        nextState = this.getStateManager().getRandomNeighbour(currentState);
        restartCounter = 0;
        restarted = true;
      } else {
        nextState = this.getStateManager().getSmartNeighbour(currentState);
      }

      currentConflictCount = currentState.getConflicts().size();
      ArrayList<Conflict> conflicts = nextState.getConflicts();
      nextConflictCount = conflicts.size();
      if (currentConflictCount > nextConflictCount || restarted) {
        this.restartCounter = 0;
        currentState = nextState;
      } else if (currentConflictCount == nextConflictCount) {
        this.restartCounter++;
      }
      this.incrementStepCount();
    }

    return currentState;
  }
}

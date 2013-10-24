package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;

/**
 * Uses the Min-Conflicts algorithm to solve constraint based puzzles. 
 * 
 * The puzzle should be represented by a specific implementation of a LocalStateManager.
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

  private int lastConflictsCount;
  private int restartCounter = 0;
  
  public MinConflicts(int maximumSteps) {
    super(maximumSteps);
  }

  /**
   * The implementation of this algorithm is based on pseudocode 
   * from Wikipedia:
   * 
   * https://en.wikipedia.org/w/index.php?title=Min-conflicts_algorithm&oldid=548827708
   */
  @Override
  public PuzzleState run() {
    this.resetStepCount();
    PuzzleState currentState = this.getStateManager().getRandomState();
    PuzzleState nextState;
    lastConflictsCount = currentState.getMaximumNumberOfConflicts();
    int currentConflictCount;
    
    int maximumSteps = this.getMaximumSteps();
    for (int i = 0; i < maximumSteps; i++) {
      if (currentState.isFinal()) {
        return currentState;
      }
      nextState = this.getStateManager().getRandomNeighbour(currentState);
      currentConflictCount = nextState.getConflicts().size();
      if (this.hasLowerConflicts(currentState, nextState)) {
        currentState = nextState;
      }
      restartCounter++;
      if(lastConflictsCount!=currentConflictCount){
        lastConflictsCount = currentConflictCount;
        this.restartCounter = 0;
      }
      
      if(restartCounter > 10000){
        return this.run();
      }
      this.incrementStepCount();
    }

    return currentState;
  }

  /**
   * Checks whether the state to evaluate has less conflicts than the 
   * other one.
   */
  private boolean hasLowerConflicts(PuzzleState oldBest, PuzzleState evaluate) {
    return oldBest != null
            && evaluate != null
            && evaluate.getConflicts().size() < oldBest.getConflicts().size();
  }
}

package kqueens;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.LocalStateManager;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class KQueensStateManager extends LocalStateManager {

  private KQueensPuzzleState currentState;
  private int size;
  private final Random random;

  public KQueensStateManager(int size) {
    this.random = new Random();
    this.size = size;
  }

  @Override
  public PuzzleState getRandomState() {
    KQueensPuzzleState newState = new KQueensPuzzleState(this.size);

    for (int row = 0; row < this.size; row++) {
      int n = random.nextInt(this.size);
      newState.setQueen(row, n);//[row]=n;//Comprobar aqui
    }
    this.currentState = newState;

    return newState;
  }

  @Override
  public void reset() {
    this.currentState = (KQueensPuzzleState) this.getRandomState();
  }

  @Override
  public PuzzleState getRandomNeighbour(PuzzleState state) {
    KQueensPuzzleState newState = new KQueensPuzzleState((KQueensPuzzleState) state);
    ArrayList<Conflict> conflicts = newState.getConflicts();

    KQueensConflict chosenConflict = (KQueensConflict) conflicts.get(random.nextInt(conflicts.size()));

    this.changeToLowestConflict(newState, chosenConflict);


    return newState;
  }

  private void changeToLowestConflict(KQueensPuzzleState newState, KQueensConflict chosenConflict) {
    int smartestColumn = -1;
    int lowestConflicts = newState.getMaximumNumberOfConflicts();

    for (int i = 0; i < this.size; i++) {
      newState.setQueen(chosenConflict.getRow(), i);

      int newConflictCount = newState.getConflicts().size();
      if (newConflictCount <= lowestConflicts) {
        //decide randomly if we want to take this change
        if(random.nextBoolean()){
        lowestConflicts = newConflictCount;
        smartestColumn = i;
        }
      }
    }
    newState.setQueen(chosenConflict.getRow(), smartestColumn);
  }

  public void displayCurrentState() {
    this.currentState.display();
  }

  public PuzzleState getCurrentState() {
    return this.currentState;
  }

  @Override
  public void displayState(PuzzleState state) {
    state.display();
  }

  @Override
  public void initialize(Object[] parameters) {
    this.currentState = new KQueensPuzzleState(size);
  }

  @Override
  public PuzzleState getSmartNeighbour(PuzzleState state) {
    return this.getRandomNeighbour(state);
  }
}

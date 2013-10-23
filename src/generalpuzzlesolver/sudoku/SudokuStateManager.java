/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver.sudoku;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.LocalStateManager;
import generalpuzzlesolver.puzzle.PuzzleState;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class SudokuStateManager extends LocalStateManager {

  private SudokuPuzzleState startingState, currentState;
  private Random random;

  public SudokuStateManager() {
    random = new Random();
  }

  /**
   * Puzzles are under creative commons and taken from:
   * http://www.sudokucollection.com/sp/E001x.html
   *
   * @param parameters
   */
  @Override
  public void initialize(Object[] parameters) {
    int difficulty = (Integer) parameters[0];
    SudokuPuzzleState state = null;

    switch (difficulty) {
      case 1:
        int[] easy = {
          0, 0, 6, 2, 4, 0, 0, 0, 0,
          2, 7, 0, 0, 0, 0, 0, 0, 0,
          8, 4, 0, 0, 7, 5, 0, 3, 0,
          0, 8, 0, 0, 0, 1, 0, 0, 0,
          0, 1, 0, 4, 0, 9, 0, 5, 0,
          0, 0, 0, 3, 0, 0, 0, 1, 0,
          0, 3, 0, 1, 9, 0, 0, 4, 8,
          0, 0, 0, 0, 0, 0, 0, 6, 1,
          0, 0, 0, 0, 5, 8, 7, 0, 0
        };
        state = new SudokuPuzzleState(easy);
        break;
      case 2:
        int[] medium = {
          7, 0, 4, 0, 1, 0, 0, 0, 0,
          0, 2, 0, 0, 0, 0, 0, 0, 0,
          9, 1, 6, 0, 0, 7, 0, 0, 2,
          8, 0, 9, 1, 0, 0, 0, 0, 0,
          2, 0, 0, 5, 0, 6, 0, 0, 7,
          0, 0, 0, 0, 0, 9, 8, 0, 5,
          4, 0, 0, 2, 0, 0, 7, 1, 6,
          0, 0, 0, 0, 0, 0, 0, 2, 0,
          0, 0, 0, 0, 6, 0, 5, 0, 4
        };
        state = new SudokuPuzzleState(medium);
        break;
      case 3:
        int[] hard = {
          1, 0, 0, 0, 0, 4, 0, 3, 0,
          0, 0, 0, 3, 0, 0, 9, 8, 4,
          9, 0, 0, 6, 0, 0, 0, 7, 0,
          0, 0, 0, 0, 0, 9, 7, 5, 0,
          0, 0, 6, 0, 0, 0, 8, 0, 0,
          0, 1, 7, 2, 0, 0, 0, 0, 0,
          0, 5, 0, 0, 0, 6, 0, 0, 2,
          6, 7, 9, 0, 0, 3, 0, 0, 0,
          0, 4, 0, 5, 0, 0, 0, 0, 7
        };
        state = new SudokuPuzzleState(hard);
        break;
    }
    this.startingState = state;
    this.currentState = state;
  }

  @Override
  public PuzzleState getRandomState() {
    SudokuPuzzleState newState = new SudokuPuzzleState(this.startingState);

    for (int row = 0; row < SudokuPuzzleState.PUZZLE_SIZE; row++) {
      for (int column = 0; column < SudokuPuzzleState.PUZZLE_SIZE; column++) {
        if (newState.getNumberAt(row, column) == 0) {
          newState.setNumberAt(row, column, random.nextInt(9) + 1);
        }
      }
    }
    this.currentState = newState;

    return newState;
  }

  @Override
  public void reset() {
    this.currentState = this.startingState;
  }

  @Override
  public void displayState(PuzzleState state) {
    state.display();
  }

  @Override
  public PuzzleState getRandomNeighbour(PuzzleState state) {
    ArrayList<Conflict> conflicts = state.getConflicts();
    SudokuConflict currentConflict = null;
    int counter = 0;

    //select a random conflict
    while (currentConflict == null && counter < conflicts.size()) {
      int randomIndex = random.nextInt(conflicts.size());
      currentConflict = (SudokuConflict) conflicts.get(randomIndex);
      if (this.startingState.getNumberAt(currentConflict.getRow(), currentConflict.getColumn()) != 0) {
        currentConflict = null;
      }
      counter++;
    }

    SudokuPuzzleState newState = new SudokuPuzzleState((SudokuPuzzleState) state);
    if (currentConflict != null) {
      int bestNumber = -1;
      int bestConflicts = 10;
      for(int i = 1; i<10;i++){
        newState.setNumberAt(currentConflict.getRow(), currentConflict.getColumn(), i);
        int currentConflictCount = newState.getConflicts().size();
        if(bestNumber < 0 || bestConflicts > currentConflictCount){
          bestNumber = i;
          bestConflicts = currentConflictCount;
        }
      }
      newState.setNumberAt(currentConflict.getRow(), currentConflict.getColumn(), bestNumber);
      return newState;
    }
    return null;
  }
}

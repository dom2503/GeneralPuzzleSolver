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

  public int calculateConflictCount(ArrayList<Conflict> conflicts) {
    int conflictCount = 0;

    for (Conflict conflict : conflicts) {
      SudokuConflict currentConflict = (SudokuConflict) conflict;
      if (this.startingState.getNumberAt(currentConflict.getRow(), currentConflict.getColumn()) != 0) {
        conflictCount += 30;
      } else {
        conflictCount++;
      }
    }

    return conflictCount;
  }

  @Override
  public PuzzleState getRandomNeighbour(PuzzleState state) {
    boolean changed = false;
    SudokuPuzzleState newState = new SudokuPuzzleState((SudokuPuzzleState) state);

    while (!changed) {
      int randomRow = random.nextInt(9);
      int randomColumn = random.nextInt(9);
      
      if (this.startingState.getNumberAt(randomRow, randomColumn) == 0) {
        newState.setNumberAt(randomRow, randomColumn, random.nextInt(9) + 1);    
        changed = true;
      }
    }

    return newState;
  }

  /**
   * Select a random conflict and selects the smartes change.
   *
   * Based on hints and pseudocode from:
   * https://amoon.netfirms.com/Portfolio/Applied%20AI%20-%20Sudoku%20Solver.pdf
   *
   * @param state
   * @return
   */
  @Override
  public PuzzleState getSmartNeighbour(PuzzleState state) {
    ArrayList<Conflict> conflicts = state.getConflicts();
    ArrayList<Conflict> selectionList = conflicts;
    SudokuPuzzleState newState = new SudokuPuzzleState((SudokuPuzzleState) state);
    if (conflicts.size() > 0) {
      SudokuConflict currentConflict = null;
      int counter = 0;

      //prefer conflicts that have a problem with an initial number
      ArrayList<Conflict> conflictWithStart = new ArrayList<Conflict>();
      for (Conflict conflict : conflicts) {
        SudokuConflict selectedConflict = (SudokuConflict) conflict;
        if (this.startingState.getNumberAt(selectedConflict.getViolatesRow(), selectedConflict.getViolatesColumn()) != 0) {
          conflictWithStart.add(conflict);
        }
      }

      if (conflictWithStart.size() > 0) {
        selectionList = conflictWithStart;
      }

      //select a random conflict
      while (currentConflict == null && counter < selectionList.size()) {
        int randomIndex = random.nextInt(selectionList.size());
        currentConflict = (SudokuConflict) selectionList.get(randomIndex);
        //if it was already in the starting state, it's not changeable so reset to null
        if (this.startingState.getNumberAt(currentConflict.getRow(), currentConflict.getColumn()) != 0) {
          currentConflict = null;
        }
        counter++;
      }

      if (currentConflict != null) {
        int lowestConflict = this.calculateConflictCount(conflicts);
        int bestValue = newState.getNumberAt(currentConflict.getRow(), currentConflict.getColumn());
        int newConflictCount;
        for (int i = 1; i <= 9; i++) {
          newState.setNumberAt(currentConflict.getRow(), currentConflict.getColumn(), i);
          newConflictCount = this.calculateConflictCount(newState.getConflicts());
          if (newConflictCount <= lowestConflict) {
            lowestConflict = newConflictCount;
            bestValue = i;
          }
        }
        newState.setNumberAt(currentConflict.getRow(), currentConflict.getColumn(), bestValue);
      }
    }
    return newState;
  }
}

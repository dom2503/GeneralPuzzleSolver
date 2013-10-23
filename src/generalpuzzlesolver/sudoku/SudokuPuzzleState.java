package generalpuzzlesolver.sudoku;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.PuzzleState;
import java.util.ArrayList;

/**
 *
 */
public class SudokuPuzzleState implements PuzzleState {

  public final static int PUZZLE_SIZE = 9;
  public int[][] puzzle;

  public SudokuPuzzleState() {
    puzzle = new int[PUZZLE_SIZE][PUZZLE_SIZE];
    for (int i = 0; i < PUZZLE_SIZE; i++) {
      for (int j = 0; j < PUZZLE_SIZE; j++) {
        puzzle[i][j] = 0;
      }
    }
  }

  public SudokuPuzzleState(SudokuPuzzleState blueprint) {
    this();
    for (int i = 0; i < PUZZLE_SIZE; i++) {
      System.arraycopy(blueprint.puzzle[i], 0, this.puzzle[i], 0, PUZZLE_SIZE);
    }
  }

  /**
   * Initializes this puzzle with the given data.
   *
   * The data should be an array of integers of the sudoku starting top left and ending bottom
   * right. Empty cells should be marked with a 0.
   *
   * @param data
   */
  public SudokuPuzzleState(int[] data) {
    this();
    int counter = 0;
    for (int row = 0; row < PUZZLE_SIZE; row++) {
      for (int column = 0; column < PUZZLE_SIZE; column++) {
        puzzle[row][column] = data[counter];
        counter++;
      }
    }
  }

  public int getNumberAt(int row, int column) {
    return puzzle[row][column];
  }

  public void setNumberAt(int row, int column, int number) {
    if (number >= 0 && number <= 9) {
      this.puzzle[row][column] = number;
    }
  }

  @Override
  public void display() {
    for (int row = 0; row < PUZZLE_SIZE; row++) {
      for (int column = 0; column < PUZZLE_SIZE; column++) {
        System.out.print(puzzle[row][column] + "  ");
      }
      System.out.println();
    }
  }

  @Override
  public boolean isFinal() {
    ArrayList<Conflict> conflicts = this.getConflicts();
    return conflicts.isEmpty();
  }

  @Override
  public ArrayList<Conflict> getConflicts() {
    ArrayList<Conflict> conflicts = new ArrayList<Conflict>();
    for (int row = 0; row < PUZZLE_SIZE; row++) {
      for (int column = 0; column < PUZZLE_SIZE; column++) {
        int currentNumber = puzzle[row][column];
        this.findConflictsInRow(currentNumber, row, column, conflicts);
        this.findConflictsInColumn(currentNumber, row, column, conflicts);
        this.findConflictsInBlock(currentNumber, row, column, conflicts);
      }
    }
    return conflicts;
  }

  private void findConflictsInRow(int number, int row, int originalColumn, ArrayList<Conflict> conflicts) {
    for (int checkColumn = 0; checkColumn < PUZZLE_SIZE; checkColumn++) {
      if (originalColumn != checkColumn && this.puzzle[row][checkColumn] == number) {
        conflicts.add(new SudokuConflict(row, originalColumn, row, checkColumn));
      }
    }
  }

  private void findConflictsInColumn(int number, int originalRow, int column, ArrayList<Conflict> conflicts) {
    for (int checkRow = 0; checkRow < PUZZLE_SIZE; checkRow++) {
      if (originalRow != checkRow && this.puzzle[checkRow][column] == number) {
        conflicts.add(new SudokuConflict(originalRow, column, checkRow, column));
      }
    }
  }

  private void findConflictsInBlock(int number, int row, int column, ArrayList<Conflict> conflicts) {
    int startRow = row - (row % 3);
    int startColumn = column - (column % 3);

    for (int rowIndex = startRow; rowIndex < startRow + 3; rowIndex++) {
      for (int columnIndex = startColumn; columnIndex < startColumn + 3; columnIndex++) {
        if (rowIndex != row && columnIndex != column && this.puzzle[rowIndex][columnIndex] == number) {
          conflicts.add(new SudokuConflict(row, column, rowIndex, columnIndex));
        }
      }
    }
  }

  @Override
  public int getMaximumNumberOfConflicts() {
    //every number can have a conflict with it's 8 neighbors
    //in the same row, column and block => 8*3=24
    return 81 * 24;
  }
}

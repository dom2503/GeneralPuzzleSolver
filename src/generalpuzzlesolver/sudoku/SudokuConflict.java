package generalpuzzlesolver.sudoku;

import generalpuzzlesolver.puzzle.Conflict;

/**
 * This considers a conflict in Sudoku to be just one of the set numbers that somehow breaks the
 * constraints.
 */
public class SudokuConflict implements Conflict {

  private int row;
  private int column;
  private int violatesColumn;
  private int violatesRow;

  public SudokuConflict(int row, int column, int violatesRow, int violatesColumn) {
    this.row = row;
    this.column = column;
    this.violatesRow = violatesRow;
    this.column = violatesColumn;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof SudokuConflict) {
      SudokuConflict otherConflict = (SudokuConflict) other;
      if (this.row == otherConflict.row && this.column == otherConflict.column && this.violatesColumn == otherConflict.violatesColumn && this.violatesRow == otherConflict.violatesRow) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + this.row;
    hash = 37 * hash + this.column;
    hash = 37 * hash + this.violatesColumn;
    hash = 37 * hash + this.violatesRow;
    return hash;
  }

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }

  public int getViolatesRow() {
    return this.violatesRow;
  }

  public int getViolatesColumn() {
    return this.violatesColumn;
  }
}

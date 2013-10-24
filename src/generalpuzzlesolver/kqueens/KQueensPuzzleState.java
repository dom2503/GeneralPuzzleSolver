package generalpuzzlesolver.kqueens;

import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.Conflict;
import java.util.ArrayList;

/**
 *
 */
public class KQueensPuzzleState implements PuzzleState {

  public final int queens[];

  public KQueensPuzzleState(int size) {
    this.queens = new int[size];
    for (int row = 0; row < size; row++) {
      this.queens[row] = 0;
    }
  }

  public KQueensPuzzleState(KQueensPuzzleState copyState) {
    this(copyState.queens.length);
    System.arraycopy(copyState.queens, 0, this.queens, 0, copyState.queens.length);
  }

  @Override
  public void display() {
    int size = this.queens.length;
    System.out.print("    ");
    for (int column = 0; column < size; column++) {
      System.out.print("  " + String.format("%1d", column) + "");
    }
    System.out.println();

    for (int row = 0; row < size; row++) {
      System.out.print(" " + String.format("%3d", row) + " ");
      for (int column = 0; column < size; column++) {
        if (queens[row] == column) {
          System.out.print(" Q ");
        } else {
          System.out.print(" _ ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public int[] getQueens() {
    return this.queens;
  }

  public void setQueen(int row, int y) {
    this.queens[row] = y;
  }

  @Override
  public boolean isFinal() {
    return this.getConflicts().isEmpty();
  }

  @Override
  public ArrayList<Conflict> getConflicts() {
    ArrayList<Conflict> conflicts = new ArrayList<Conflict>();

    for (int queenIndex = 0; queenIndex < this.queens.length; queenIndex++) {
      for (int compareQueen = 0; compareQueen < this.queens.length; compareQueen++) {
        if (queenIndex != compareQueen) {
          if (queens[queenIndex] == queens[compareQueen] || queens[queenIndex] + Math.abs(queenIndex - compareQueen) == queens[compareQueen] || queens[queenIndex] - Math.abs(queenIndex - compareQueen) == queens[compareQueen]) {
            conflicts.add(new KQueensConflict(queenIndex, queens[queenIndex]));
          }
        }
      }
    }

    return conflicts;
  }

  @Override
  public int getMaximumNumberOfConflicts() {
    return this.queens.length * this.queens.length - 1;
  }
}

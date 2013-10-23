package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.Conflict;
import java.util.ArrayList;

/**
 *
 */
public class KQueensPuzzleState implements PuzzleState {

    private final boolean[][] queenPossition;
    public final int queens[];

    public KQueensPuzzleState(int size) {

        this.queenPossition = new boolean[size][size];
        this.queens = new int[size];
        this.initQueenPossition();
    }

    public KQueensPuzzleState(KQueensPuzzleState copyState) {

        this(copyState.getSize());
        int Boardsize = copyState.getSize();

        for (int row = 0; row < Boardsize; row++) {
            /*for(int column=0;column<Boardsize; column++){
            copyState.queenPossition[row][column]= this.queenPossition[row][column];
            }*/
            System.arraycopy(copyState.queenPossition[row], 0, this.queenPossition[row], 0, Boardsize );

        }
        System.arraycopy(copyState.queens, 0, this.queens, 0, Boardsize);
    }

    public int getSize() {
        return queenPossition.length;
    }

    /**
     * Initializes the board.
     * 
     * 
     */
    private void initQueenPossition() {
        int sizeBoard = this.queenPossition.length;
        for (int row = 0; row < sizeBoard; row++) {
            this.queens[row] = 0;
            for (int column = 0; column < sizeBoard; column++) {
                this.queenPossition[row][column] = false;
            }
        }
    }

    @Override
    public void display() {
        int size = queenPossition.length;

        System.out.print("   ");
        for (int column = 0; column < size; column++) {
            System.out.print("    " + String.format("%3d", column) + "");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print(" " + String.format("%3d", row) + " ");
            for (int column = 0; column < size; column++) {
                if (this.queenPossition[row][column]) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" ____  ");
                }
            }
            System.out.println();
        }
        System.out.println();


    }

    public int[] getQueens() {
        return this.queens;
    }

    public void setPossitionQueen(int x, int y) {
        queenPossition[x][y] = true;
    
        
    }
    
    public void removeQueen(int x, int y){
        queenPossition[x][y] = false;
    }

    public boolean getQueenPossition(KQueensPuzzleState possition, int x, int y) {
        return possition.queenPossition[x][y];
    }

    public void setQueens(int row, int y) {
        this.queens[row] = y;
    }

    @Override
    public boolean isFinal() {
        return this.getConflicts().size()==0;
    }

    @Override
    public ArrayList<Conflict> getConflicts() {
        ArrayList<Conflict> conflicts = new ArrayList<Conflict>();

        for(int queenIndex=0; queenIndex<this.queens.length; queenIndex++)
        {
          for(int compareQueen=0; compareQueen<this.queens.length;compareQueen++){
              if(queenIndex!=compareQueen){
                  if(queens[queenIndex]==queens[compareQueen] || queens[queenIndex]+Math.abs(queenIndex - compareQueen)==queens[compareQueen] || queens[queenIndex]-Math.abs(queenIndex - compareQueen)==queens[compareQueen]){
                      conflicts.add(new KQueensConflict(queenIndex, queens[queenIndex]));
                  }
              }
          }
        }
        
        return conflicts;
     }


    @Override
    public int getMaximumNumberOfConflicts() {
      return this.queens.length * this.queens.length -1;  
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.PuzzleState;
import generalpuzzlesolver.puzzle.LocalStateManager;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dominik
 */
public class KQueensStateManager extends LocalStateManager {

    private KQueensPuzzleState currentState;
    private int size;
    //private boolean[][] queenPositions;
    private int queens[];
    private final Random random;

    public KQueensStateManager(int size) {
        this.random = new Random();
        this.size = size;
        this.queens = new int[size];
        this.currentState = new KQueensPuzzleState(size);
        this.currentState.display();
    }

    @Override
    public PuzzleState getRandomState() {
        KQueensPuzzleState newState = new KQueensPuzzleState(this.size);

        int BoardSize = newState.getSize();
        for (int row = 0; row < BoardSize; row++) {
            int n = random.nextInt(BoardSize);
            newState.setPossitionQueen(row, n);
            //     newState.queens.setQueens(row, n);//Comprobar aqui
            newState.setQueens(row, n);//[row]=n;//Comprobar aqui




        }
        this.currentState = newState;

        return newState;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            newState.removeQueen(chosenConflict.getRow(), chosenConflict.getColumn());
        for (int i = 0; i < this.size; i++) {
            newState.setPossitionQueen(chosenConflict.getRow(), i);
            newState.setQueens(chosenConflict.getRow(), i);
            
            int newConflictCount = newState.getConflicts().size();
            if(newConflictCount<lowestConflicts){
                lowestConflicts = newConflictCount;
                smartestColumn = i;
            }
            newState.removeQueen(chosenConflict.getRow(), i);
        }
        newState.setPossitionQueen(chosenConflict.getRow(), smartestColumn);
        newState.setQueens(chosenConflict.getRow(), smartestColumn);
    }

    public void displayCurrentState() {
        this.currentState.display();
    }

    public PuzzleState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void displayState(PuzzleState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(Object[] parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

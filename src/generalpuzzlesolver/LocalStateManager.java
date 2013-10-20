package generalpuzzlesolver;

/**
 *
 */
abstract public class LocalStateManager {
  
  abstract public PuzzleState getNextRandomState();
  
  abstract public double calculateEnergy(PuzzleState state);
  
  abstract public PuzzleState getNeighbour(PuzzleState state);
  
  abstract public boolean isFinal(PuzzleState state);
  
  abstract public void reset();
}

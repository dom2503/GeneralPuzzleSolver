package generalpuzzlesolver;

/**
 *
 */
public interface LocalStateManager {
  
  public PuzzleState getNextRandomState();
  
  public double calculateEnergy(PuzzleState state);
  
  public PuzzleState getNeighbour(PuzzleState state);
  
  abstract public void reset();
  
  public void displayCurrentState();
}

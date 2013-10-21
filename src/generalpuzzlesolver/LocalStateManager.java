package generalpuzzlesolver;

/**
 *
 */
public interface LocalStateManager {
  
  public PuzzleState getNextRandomState();
  
  public double calculateEnergy(PuzzleState state);
  
  public PuzzleState getNeighbour();
  
  abstract public void reset();
  
  public void displayCurrentState();
  
  public PuzzleState getCurrentState();
}

package generalpuzzlesolver;

/**
 *
 */
abstract public class SimulatedAnnealing extends ConstraintBasedLocalSearch{
  
  private static final int MAXIMUM_TRIES = 1000;
  private static final double MAXIMUM_ENERGY = 1.0;
  
  /**
   * Based on pseudocode from Wikipedia:
   * https://en.wikipedia.org/wiki/Simulated_annealing
   */
  public void search(){
    int evaluationCount = 0;
    double temperature = -1.0;
    
    PuzzleState currentState = this.getStateManager().getNextRandomState();
    double energy = this.calculateEnergy(currentState);
    
    PuzzleState nextState;
    double nextEnergy;
    
    PuzzleState bestState = currentState;
    double bestEnergy;
    
    while(evaluationCount < MAXIMUM_TRIES && energy > MAXIMUM_ENERGY){
      temperature = this.calculateTemperature(currentState);
      nextState = this.getStateManager().getNeighbour(currentState);
      
      
      if()
      
      evaluationCount++;
    }
  }
  
  abstract protected double calculateTemperature(PuzzleState state);
  abstract protected double calculateEnergy(PuzzleState state);
}

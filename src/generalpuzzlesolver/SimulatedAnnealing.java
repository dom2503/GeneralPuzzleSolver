package generalpuzzlesolver;

import generalpuzzlesolver.puzzle.PuzzleState;

/**
 * Uses the Simulated Annealing algorithm to solve constraint based puzzles. 
 * 
 * The puzzle should be represented by a specific implementation of a LocalStateManager.
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch{
  
  private static final double MAXIMUM_ENERGY = 1.0;
  
  public SimulatedAnnealing(int maximumSteps){
    super(maximumSteps);
  }
  
  /**
   * Based on pseudocode from Wikipedia:
   * https://en.wikipedia.org/wiki/Simulated_annealing
   */
  @Override
  public PuzzleState run(){
    int evaluationCounter = 0;
    double temperature = -1.0;
    
    PuzzleState currentState = this.getStateManager().getRandomState();
    double energy = this.calculateEnergy(currentState);
    
    PuzzleState nextState;
    double nextEnergy;
    
    PuzzleState bestState = currentState;
    double bestEnergy = -1.0;
    
    while(evaluationCounter < this.getMaximumSteps() && energy > MAXIMUM_ENERGY){
      temperature = this.calculateTemperature(evaluationCounter/this.getMaximumSteps());
      
      nextState = this.getStateManager().getNeighbour();
      nextEnergy = this.calculateEnergy(nextState);
      
      //missing if here
      
      if(nextEnergy < bestEnergy){
        bestEnergy = nextEnergy;
        bestState = nextState;
      }
      
      evaluationCounter++;
    }
    
    return bestState;
  }
  
  @Override
  public void reset(){
    this.getStateManager().reset();
  }
  
  protected double calculateTemperature(double input){
    return 1.0;
  }
  protected double calculateEnergy(PuzzleState state){
    return 1.0;
  }
}

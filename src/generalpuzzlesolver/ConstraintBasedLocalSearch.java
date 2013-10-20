package generalpuzzlesolver;

/**
 * Implementing classes should provide a generally usable constraint based search 
 * algorithm within their run method. 
 * 
 * All specific information regarding the puzzle to solve, needs to be distributed
 * to the state manager or a subclass.
 */
abstract public class ConstraintBasedLocalSearch {
  private LocalStateManager stateManager;
  private int maximumSteps;
  
  public ConstraintBasedLocalSearch(int maximumSteps){
    this.maximumSteps =  maximumSteps;
  }
  
  public void setStateManager(LocalStateManager stateManager){
    this.stateManager = stateManager;
  }
  
  protected LocalStateManager getStateManager(){
    return this.stateManager;
  }
  
  protected int getMaximumSteps(){
    return this.maximumSteps;
  }
  
  abstract public PuzzleState run();
  
  abstract public void reset();
}

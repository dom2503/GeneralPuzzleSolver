package generalpuzzlesolver;

/**
 *
 */
abstract public class ConstraintBasedLocalSearch {
  private LocalStateManager stateManager;
  
  public void setStateManager(LocalStateManager stateManager){
    this.stateManager = stateManager;
  }
  
  public LocalStateManager getStateManager(){
    return this.stateManager;
  }
}

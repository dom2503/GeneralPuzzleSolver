/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generalpuzzlesolver;

import java.util.Scanner;

/**
 *
 * @author dominik
 */
public class GeneralPuzzleSolver {

  private Scanner scanner;
  private ConstraintBasedLocalSearch searcher;
  private int numberOfRuns;
  private LocalStateManager puzzle;
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    GeneralPuzzleSolver gps = new GeneralPuzzleSolver();
    gps.run();
  }
  
  public GeneralPuzzleSolver(){
    this.scanner = new Scanner(System.in);
  }
  
  public void run(){
    System.out.println("---------- Welcome to the General Puzzle Solver ----------");
    
    this.puzzle = this.choosePuzzle();
    this.searcher = this.chooseSearchAlgorithm();
    this.searcher.setStateManager(this.puzzle);
    this.numberOfRuns = this.chooseNumberOfRuns();
  }
  
  /**
   * Both higher and lower bound are inclusive.
   * @param low
   * @param high
   * @return 
   */
  private int readIntInRange(int low, int high){
    int readInt = low - 1;
    while(readInt < low || readInt > high){
      readInt = this.scanner.nextInt();
    }
    return readInt;
  }
  
  private LocalStateManager choosePuzzle(){
    System.out.println("Which puzzle would you like to use?");
    System.out.println("1. k-Queens");
    System.out.println("2. Graph Colouring");
    
    int puzzleIndex = this.readIntInRange(1, 2);
    
    LocalStateManager selectedPuzzle;
    
    switch(puzzleIndex){
      case 1:
        selectedPuzzle = new KQueensStateManager();
        break;
      case 2:
        selectedPuzzle = new GraphColouringStateManager();
        break;
      default:
        //wrong selection, so start again
        System.out.println("Your selection was invalid, please try again.");
        selectedPuzzle = this.choosePuzzle();
    }
    
    return selectedPuzzle;
  }
  
  private int chooseNumberOfRuns(){
    int selectedRuns = 0;
    System.out.println("Please specify the number of runs:");
    while(selectedRuns < 1){
      selectedRuns = this.scanner.nextInt();
    }
    return selectedRuns;
  }
  
  private ConstraintBasedLocalSearch chooseSearchAlgorithm(){
    System.out.println("Which algorithm would you like to use?");
    System.out.println("1. Simulated Annealing");
    System.out.println("2. Min-Conflicts");
    
    int algorithmIndex = this.readIntInRange(1, 2);
    
    ConstraintBasedLocalSearch selectedSearcher;
    
    switch(algorithmIndex){
      case 1:
        selectedSearcher = new SimulatedAnnealing();
        break;
      case 2:
        selectedSearcher = new MinConflicts();
        break;
      default:
        //wrong selection, so start again
        System.out.println("Your selection was invalid, please try again.");
        selectedSearcher = this.chooseSearchAlgorithm();
    }
    
    return selectedSearcher;
  }
}

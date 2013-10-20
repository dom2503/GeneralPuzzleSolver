package generalpuzzlesolver;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Provides methods
 */
public class GraphColoringStateManager implements LocalStateManager {

  private GraphColoringPuzzleState currentState;
  private final Color[] choosableColors;
  private static final Color[] DEFAULT_COLORS = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
  private final Random random;
  private ArrayList<GraphColoringPuzzleState> consideredStates;

  /**
   * Initializes this graph coloring problem with a default number of vertices and random edges and
   * colors.
   */
  public GraphColoringStateManager() {
    this(10, DEFAULT_COLORS);
  }

  /**
   * Initializes this graph coloring problem with the given number of vertices.
   *
   * @param numberOfNodes
   */
  public GraphColoringStateManager(int numberOfNodes, Color[] choosableColors) {
    this.random = new Random();
    this.choosableColors = choosableColors;
    this.currentState = new GraphColoringPuzzleState(numberOfNodes);
    this.consideredStates = new ArrayList<GraphColoringPuzzleState>();
  }

  /**
   * This method is mainly based on:
   *
   * http://www3.math.tu-berlin.de/Vorlesungen/WS03/CoMa.1/programs/GraphReader.java
   *
   * @param fileName
   */
  public void generateStateFromFile(String fileName) {
    try {
      Scanner scanner = new Scanner(new File(fileName));
      int numberOfNodes = scanner.nextInt();
      int numberOfEdges = scanner.nextInt();

      GraphColoringPuzzleState puzzleState = new GraphColoringPuzzleState(numberOfNodes);

      //read positions
      //just dummy reading for now, change if we really want to plot the graph
      for (int nodePositions = 0; nodePositions < numberOfNodes; nodePositions++) {
        scanner.nextInt();
        scanner.nextDouble();
        scanner.nextDouble();
      }

      //read in edges
      for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        puzzleState.addBorder(row, column);
      }
      
      this.currentState = puzzleState;
    } catch (FileNotFoundException exception) {
    }

  }

  @Override
  public PuzzleState getNextRandomState() {
    int numberOfNodes = this.currentState.getNumberOfNodes();
    GraphColoringPuzzleState newState = new GraphColoringPuzzleState(this.currentState);
    for (int currentNodeIndex = 0; currentNodeIndex < numberOfNodes; currentNodeIndex++) {
      this.currentState.setVertexColor(currentNodeIndex, this.choosableColors[this.random.nextInt(this.choosableColors.length)]);
    }
    this.consideredStates.add(this.currentState);
    this.currentState = newState;

    return newState;
  }

  @Override
  public void displayCurrentState() {
    this.currentState.display();
  }

  @Override
  public double calculateEnergy(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public PuzzleState getNeighbour(PuzzleState state) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}

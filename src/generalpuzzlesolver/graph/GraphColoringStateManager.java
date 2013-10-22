package generalpuzzlesolver.graph;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.LocalStateManager;
import generalpuzzlesolver.puzzle.PuzzleState;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the graph coloring problem.
 */
public class GraphColoringStateManager extends LocalStateManager {

  private GraphColoringPuzzleState startingState, currentState;
  private ArrayList<Color> choosableColors;
  private final Random random;
  private ArrayList<GraphColoringPuzzleState> consideredStates;
  public static final ArrayList<Color> DEFAULT_COLORS;

  static {
    DEFAULT_COLORS = new ArrayList();
    DEFAULT_COLORS.add(Color.BLUE);
    DEFAULT_COLORS.add(Color.RED);
    DEFAULT_COLORS.add(Color.GREEN);
    DEFAULT_COLORS.add(Color.YELLOW);
    DEFAULT_COLORS.add(Color.PINK);
    DEFAULT_COLORS.add(Color.ORANGE);
    DEFAULT_COLORS.add(Color.CYAN);
    DEFAULT_COLORS.add(Color.DARK_GRAY);
    DEFAULT_COLORS.add(Color.BLACK);
    DEFAULT_COLORS.add(Color.LIGHT_GRAY);
    DEFAULT_COLORS.add(Color.MAGENTA);
    DEFAULT_COLORS.add(Color.GRAY);
  }

  public GraphColoringStateManager() {
    this.random = new Random();
    this.consideredStates = new ArrayList<GraphColoringPuzzleState>();
    this.choosableColors = DEFAULT_COLORS;
  }

  /**
   * Initializes this graph coloring problem with the given number of vertices.
   *
   * @param numberOfNodes
   */
  public GraphColoringStateManager(ArrayList<Color> choosableColors) {
    this();
    this.choosableColors = choosableColors;
  }

  /**
   * Initializes the graph with the data from the file.
   * 
   * @param fileName
   */
  public void generateInitialStateFromFile(String fileName, int scale) {
    try {  
      InputStream in = getClass().getResourceAsStream(fileName);
      Scanner scanner = new Scanner(in);
      
      int numberOfNodes = scanner.nextInt();
      int numberOfEdges = scanner.nextInt();

      GraphColoringPuzzleState puzzleState = new GraphColoringPuzzleState(numberOfNodes);
      puzzleState.setScale(scale);
      
      this.readVertices(scanner, numberOfNodes, puzzleState);
      this.readEdges(scanner, numberOfEdges, puzzleState);
      
      this.currentState = puzzleState;
      this.startingState = puzzleState;
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }

  }
  
  /**
   * Parses the specified number of vertices.
   */
  private void readVertices(Scanner scanner, int numberOfNodes, GraphColoringPuzzleState puzzleState){
    for (int nodeIndex = 0; nodeIndex < numberOfNodes; nodeIndex++) {
        int nodeNumber = scanner.nextInt();
        Point2D.Double position = new Point2D.Double(scanner.nextDouble(), scanner.nextDouble());
        puzzleState.setVertex(nodeNumber, new Vertex(null, position));
      }
  }
  
  /**
   * Parses the specified number of edges from the scanner to the puzzle state.
   */
  private void readEdges(Scanner scanner, int numberOfEdges, GraphColoringPuzzleState puzzleState){
      for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
        puzzleState.addBorder(scanner.nextInt(), scanner.nextInt());
      }
  }

  @Override
  public PuzzleState getRandomState() {
    GraphColoringPuzzleState newState = new GraphColoringPuzzleState(this.currentState);
    
    int numberOfNodes = this.currentState.getNumberOfVertices();
    for (int currentNodeIndex = 0; currentNodeIndex < numberOfNodes; currentNodeIndex++) {
      newState.getVertexAt(currentNodeIndex).setColor(this.choosableColors.get(random.nextInt(this.choosableColors.size())));
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
  public PuzzleState getNeighbour() {
    ArrayList<Conflict> conflicts = this.currentState.getConflicts();

    GraphColoringConflict selectedConflict = (GraphColoringConflict) conflicts.get(random.nextInt(conflicts.size()));
    GraphColoringPuzzleState nextState = new GraphColoringPuzzleState((GraphColoringPuzzleState) this.currentState);
    Color randomColor = this.choosableColors.get(this.random.nextInt(this.choosableColors.size()));
    if (random.nextBoolean()) {
      nextState.getVertexAt(selectedConflict.getFirstVertex()).setColor(randomColor);
    } else {
      nextState.getVertexAt(selectedConflict.getSecondVertex()).setColor(randomColor);
    }

    this.consideredStates.add(this.currentState);
    this.currentState = nextState;
    return nextState;
  }

  @Override
  public void reset() {
  }

  @Override
  public PuzzleState getCurrentState() {
    return this.currentState;
  }
}

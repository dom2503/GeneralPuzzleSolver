package generalpuzzlesolver;

import java.awt.Color;
import java.awt.geom.Point2D;
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
  private ArrayList<Color> choosableColors;
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
  ;
  private final Random random;
  private ArrayList<GraphColoringPuzzleState> consideredStates;

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
      for (int nodeIndex = 0; nodeIndex < numberOfNodes; nodeIndex++) {
        Color pointColor = this.choosableColors.get(this.random.nextInt(this.choosableColors.size()));
        int nodeNumber = scanner.nextInt();
        Point2D.Double position = new Point2D.Double(scanner.nextDouble(), scanner.nextDouble());
        puzzleState.setVertex(nodeNumber, new Vertex(pointColor, position));
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
    int numberOfNodes = this.currentState.getNumberOfVertices();
    GraphColoringPuzzleState newState = new GraphColoringPuzzleState(this.currentState);
    for (int currentNodeIndex = 0; currentNodeIndex < numberOfNodes; currentNodeIndex++) {
      this.currentState.setVertexColor(currentNodeIndex, this.choosableColors.get(random.nextInt(this.choosableColors.size())));
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
  public PuzzleState getNeighbour() {
    ArrayList<Conflict> conflicts = this.currentState.getConflicts();

    Conflict selectedConflict = conflicts.get(random.nextInt(conflicts.size()));
    GraphColoringPuzzleState nextState = new GraphColoringPuzzleState((GraphColoringPuzzleState) this.currentState);
    Color randomColor = this.choosableColors.get(this.random.nextInt(this.choosableColors.size()));
    if (random.nextBoolean()) {
      nextState.setVertexColor(selectedConflict.getFirstVertex(), randomColor);
    } else {
      nextState.setVertexColor(selectedConflict.getSecondVertex(), randomColor);
    }

    this.consideredStates.add(this.currentState);
    this.currentState = nextState;
    return nextState;
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public PuzzleState getCurrentState() {
    return this.currentState;
  }
}

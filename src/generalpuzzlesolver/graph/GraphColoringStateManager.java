package generalpuzzlesolver.graph;

import generalpuzzlesolver.puzzle.Conflict;
import generalpuzzlesolver.puzzle.LocalStateManager;
import generalpuzzlesolver.puzzle.PuzzleState;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents the graph coloring problem.
 */
public class GraphColoringStateManager extends LocalStateManager {

  private GraphColoringPuzzleState startingState, currentState;
  private ArrayList<Color> choosableColors;
  private final Random random;
  private Set<GraphColoringPuzzleState> consideredStates;
  public static final ArrayList<Color> DEFAULT_COLORS;

  static {
    DEFAULT_COLORS = new ArrayList();
    DEFAULT_COLORS.add(Color.BLUE);
    DEFAULT_COLORS.add(Color.RED);
    DEFAULT_COLORS.add(Color.GREEN);
    DEFAULT_COLORS.add(Color.YELLOW);
    DEFAULT_COLORS.add(Color.PINK);
    DEFAULT_COLORS.add(Color.ORANGE);
  }

  public GraphColoringStateManager() {
    this.random = new Random();
    this.consideredStates = new HashSet<GraphColoringPuzzleState>();
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
   * @param parameters Expects to be parameter one a String of
   * the filename and the second and int for the scale to point
   * the graph.
   */
  @Override
  public void initialize(Object[] parameters) {
    try {
      InputStream in = getClass().getResourceAsStream((String) parameters[0]);
      Scanner scanner = new Scanner(in);

      int numberOfNodes = scanner.nextInt();
      int numberOfEdges = scanner.nextInt();

      GraphColoringPuzzleState puzzleState = new GraphColoringPuzzleState(numberOfNodes, (Integer) parameters[1]);

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
  private void readVertices(Scanner scanner, int numberOfNodes, GraphColoringPuzzleState puzzleState) {
    for (int nodeIndex = 0; nodeIndex < numberOfNodes; nodeIndex++) {
      int nodeNumber = scanner.nextInt();
      Point2D.Double position = new Point2D.Double(scanner.nextDouble(), scanner.nextDouble());
      puzzleState.setVertex(nodeNumber, new Vertex(null, position));
    }
  }

  /**
   * Parses the specified number of edges from the scanner to the puzzle state.
   */
  private void readEdges(Scanner scanner, int numberOfEdges, GraphColoringPuzzleState puzzleState) {
    for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
      puzzleState.addBorder(scanner.nextInt(), scanner.nextInt());
    }
  }

  @Override
  public PuzzleState getRandomState() {
    GraphColoringPuzzleState newState = new GraphColoringPuzzleState(this.currentState);

    int numberOfNodes = newState.getNumberOfVertices();

    for (int currentNodeIndex = 0; currentNodeIndex < numberOfNodes; currentNodeIndex++) {
      newState.getVertexAt(currentNodeIndex).setColor(this.getRandomAllowedColor());
    }
    this.consideredStates.add(this.currentState);
    this.currentState = newState;

    return newState;
  }

  private Color getRandomAllowedColor() {
    return this.choosableColors.get(random.nextInt(this.choosableColors.size()));
  }

  @Override
  public void displayState(PuzzleState state) {
    state.display();
  }

  @Override
  public PuzzleState getRandomNeighbour(PuzzleState state) {
    ArrayList<Conflict> conflicts = state.getConflicts();
    int numberOfConflicts = conflicts.size();
    GraphColoringPuzzleState nextState = new GraphColoringPuzzleState((GraphColoringPuzzleState) state);

    if (numberOfConflicts > 0) {
      GraphColoringConflict selectedConflict = (GraphColoringConflict) conflicts.get(random.nextInt(numberOfConflicts));
      if (random.nextBoolean()) {
        nextState.getVertexAt(selectedConflict.getFirstVertex()).setColor(this.getRandomAllowedColor());
      } else {
        nextState.getVertexAt(selectedConflict.getSecondVertex()).setColor(this.getRandomAllowedColor());
      }
      return nextState;
    }
    return null;
  }

  @Override
  public void reset() {
    this.currentState = startingState;
  }
}

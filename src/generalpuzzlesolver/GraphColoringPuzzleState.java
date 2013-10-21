package generalpuzzlesolver;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a state of the graph coloring problem.
 *
 * It keeps track of the borders between vertices and the colors that are assigned to the vertices.
 */
public class GraphColoringPuzzleState implements PuzzleState {

  private final boolean[][] adjacencyMatrix;
  private final Vertex[] vertices;

  public GraphColoringPuzzleState(int numberOfNodes) {
    this.vertices = new Vertex[numberOfNodes];

    this.adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
    this.initAdjacencyMatrix();
  }

  /**
   * Copy constructor.
   */
  public GraphColoringPuzzleState(GraphColoringPuzzleState blueprint) {
    this(blueprint.getNumberOfVertices());
    int numberOfNodes = blueprint.getNumberOfVertices();

    for (int nodeIndex = 0; nodeIndex < numberOfNodes; nodeIndex++) {
      this.vertices[nodeIndex] = new Vertex(blueprint.vertices[nodeIndex]);
      System.arraycopy(blueprint.adjacencyMatrix[nodeIndex], 0,
              this.adjacencyMatrix[nodeIndex], 0, numberOfNodes);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof GraphColoringPuzzleState) {
      GraphColoringPuzzleState otherState = (GraphColoringPuzzleState) other;
      for (int i = 0; i < vertices.length; i++) {
        if (!otherState.vertices[i].equals(this.vertices[i])) {
          return false;
        }
      }

      if (this.vertices.length == otherState.vertices.length) {
        for (int row = 0; row < vertices.length; row++) {
          for (int column = 0; column < vertices.length; column++) {
            if(this.vertices[row]==otherState.vertices[column]){
              return false;
            }
          }
        }
      }

      return true;
    }

    return false;
  }

  public int getNumberOfVertices() {
    return adjacencyMatrix.length;
  }

  public Vertex[] getVertices() {
    return this.vertices;
  }

  public boolean[][] getEdges() {
    return this.adjacencyMatrix;
  }

  /**
   * Initializes this graph to have no edges.
   *
   * @param numberOfNodes
   */
  private void initAdjacencyMatrix() {
    int numberOfNodes = this.adjacencyMatrix.length;
    for (int row = 0; row < numberOfNodes; row++) {
      for (int column = 0; column < numberOfNodes; column++) {
        this.adjacencyMatrix[row][column] = false;
      }
    }
  }

  /**
   * Creates a border between the two given indexes of vertices. The vertices can be thought of as
   * countries for easier understanding.
   *
   * @param startVertex
   * @param endVertex
   */
  public void addBorder(int firstVertex, int secondVertex) {
    adjacencyMatrix[firstVertex][secondVertex] = true;
    adjacencyMatrix[secondVertex][firstVertex] = true;
  }

  /**
   * Sets the color of the vertex at the given index.
   *
   * @param index
   * @param color
   */
  public void setVertexColor(int index, Color color) {
    this.vertices[index].setColor(color);
  }

  public void setVertex(int index, Vertex vertex) {
    this.vertices[index] = vertex;
  }

  /**
   * Shows the graph in a frame.
   */
  @Override
  public void display() {
    GraphPainter frame = new GraphPainter(this);
    frame.setVisible(true);
  }

  /**
   * Checks that there are no borders with matching colors.
   */
  @Override
  public boolean isFinal() {
    int numberOfNodes = this.getNumberOfVertices();

    for (int row = 0; row < numberOfNodes; row++) {
      for (int column = 0; column < numberOfNodes; column++) {
        if (this.haveEdgeAndSameColor(row, column)) {
          return false;
        }
      }
    }

    return true;
  }

  public Vertex getVertexAt(int index) {
    return this.vertices[index];
  }

  /**
   * Finds all borders where the colors are the same.
   */
  @Override
  public ArrayList<Conflict> getConflicts() {
    ArrayList<Conflict> conflicts = new ArrayList<Conflict>();
    int numberOfNodes = this.getNumberOfVertices();

    for (int row = 0; row < numberOfNodes; row++) {
      for (int column = 0; column < numberOfNodes; column++) {
        if (this.haveEdgeAndSameColor(row, column)) {
          conflicts.add(new Conflict(row, column));
        }
      }
    }

    return conflicts;
  }
  
  /**
   * Checks if the vertices at the given indices have an edge and the same color.
   */
  private boolean haveEdgeAndSameColor(int row, int column){
    return this.adjacencyMatrix[row][column] && this.vertices[row].getColor().equals(this.vertices[column].getColor());
  }
}

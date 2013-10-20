package generalpuzzlesolver;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a state of the graph coloring problem. 
 * 
 * It keeps track of the borders between vertices and the colors that
 * are assigned to the vertices.
 */
public class GraphColoringPuzzleState implements PuzzleState {
  private final boolean[][] adjacencyMatrix;
  private final Color[] vertexColors;
  
  public GraphColoringPuzzleState(int numberOfNodes){
    this.vertexColors = new Color[numberOfNodes];
    this.adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
    this.initAdjacencyMatrix(); 
  }
  
  /**
   * Copy constructor.
   * @param copy 
   */
  public GraphColoringPuzzleState(GraphColoringPuzzleState copy){
    this(copy.adjacencyMatrix.length);
    int numberOfNodes = copy.adjacencyMatrix.length;
    
    System.arraycopy(copy.vertexColors, 0, this.vertexColors, 0, numberOfNodes);
    
    //copy adjacency matrix
    for(int row=0; row<numberOfNodes; row++){
      System.arraycopy(copy.adjacencyMatrix[row], 0, this.adjacencyMatrix[row], 0, numberOfNodes);
    }
  }
  
  public int getNumberOfNodes(){
    return adjacencyMatrix.length;
  }
  
  /**
   * Initializes this graph to have no edges.
   * 
   * @param numberOfNodes
   */
  private void initAdjacencyMatrix(){
    int numberOfNodes = this.adjacencyMatrix.length;
    for(int row = 0; row<numberOfNodes;row++){
      for(int column = 0; column < numberOfNodes; column++){
        this.adjacencyMatrix[row][column] = false;
      }
    }
  }
  
  /**
   * Creates a border between the two given indexes of vertices. The vertices can be 
   * thought of as countries for easier understanding.
   * 
   * @param startVertex
   * @param endVertex 
   */
  public void addBorder(int firstVertex, int secondVertex){
    adjacencyMatrix[firstVertex][secondVertex] = true;
    adjacencyMatrix[secondVertex][firstVertex] = true;
  }
  
  /**
   * Sets the color of the vertex at the given index.
   * 
   * @param index
   * @param color 
   */
  public void setVertexColor(int index, Color color){
    this.vertexColors[index] = color;
  }

  @Override
  public void display() {
    int numberOfNodes = adjacencyMatrix.length;
    
    System.out.print("   ");
    for(int column=0; column<numberOfNodes; column++){
      System.out.print("    " + String.format("%3d", column) + "");
    }
    System.out.println();
    
    for(int row=0; row<numberOfNodes; row++){
      System.out.print(" " + String.format("%3d", row) + " ");
      for(int column = 0; column < numberOfNodes; column++){
        if(this.adjacencyMatrix[row][column]){
          System.out.print(" Edge  ");
        } else {
          System.out.print(" ____  ");
        }
      }
      System.out.println();
    }
    System.out.println();
    
    int colorCount = this.vertexColors.length;
    for(int currentColor=0; currentColor<colorCount;currentColor++){
      System.out.print(currentColor + ": ");
      if(this.vertexColors[currentColor]!=null){
      System.out.print(this.vertexColors[currentColor].toString() + ", ");
      }else{
        System.out.print("None, ");
      }
    }
    System.out.println();
  }

  @Override
  public boolean isFinal() {
    int numberOfNodes = this.getNumberOfNodes();
    
    for(int row = 0; row<numberOfNodes;row++){
      for(int column = 0; column < numberOfNodes; column++){
        if(this.vertexColors[row].equals(this.vertexColors[column])){
          return false;
        }
      }
    }
    
    return true;
  }
  
  /**
   * 
   * @return 
   */
  public ArrayList<Conflict> getConflicts(){
    ArrayList<Conflict> conflicts = new ArrayList<Conflict>();
    int numberOfNodes = this.getNumberOfNodes();
    
    for(int row = 0; row<numberOfNodes;row++){
      for(int column = 0; column < numberOfNodes; column++){
        if(this.vertexColors[row].equals(this.vertexColors[column])){
          conflicts.add(new Conflict(row, column));
        }
      }
    }
    
    return conflicts;
  }
}

package generalpuzzlesolver.graph;

import generalpuzzlesolver.puzzle.Conflict;

/**
 *
 */
public class GraphColoringConflict implements Conflict {

  private int firstVertex;
  private int secondVertex;

  public GraphColoringConflict(int firstVertex, int secondVertex) {
    this.firstVertex = firstVertex;
    this.secondVertex = secondVertex;
  }

  public int getFirstVertex() {
    return firstVertex;
  }

  public int getSecondVertex() {
    return secondVertex;
  }
}

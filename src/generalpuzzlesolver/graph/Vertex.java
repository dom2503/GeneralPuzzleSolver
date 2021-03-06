package generalpuzzlesolver.graph;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents a colored vertex in a Graph.
 */
public class Vertex {

  private Color color;
  private Point2D position;

  public Vertex(Color color, Point2D position) {
    this.color = color;
    this.position = position;
  }

  public Vertex(Vertex blueprint) {
    this.color = blueprint.color;
    this.position = blueprint.position;
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
      this.color = color;
  }

  public Point2D getPosition() {
    return this.position;
  }
  
  @Override
  public boolean equals(Object other){
    if(other instanceof Vertex){
      Vertex otherVertex = (Vertex) other;
      if(!this.color.equals(otherVertex.color) || !otherVertex.getPosition().equals(this.position)){
        return false;
      }
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + (this.color != null ? this.color.hashCode() : 0);
    hash = 97 * hash + (this.position != null ? this.position.hashCode() : 0);
    return hash;
  }
}
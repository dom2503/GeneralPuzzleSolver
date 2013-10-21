package generalpuzzlesolver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;

/**
 * Paints the given graph.
 */
public class GraphPainter extends JFrame {

  private GraphColoringPuzzleState graph;
  private Point2D maxPoint;
  private final static int SCALE= 30;

  public GraphPainter(GraphColoringPuzzleState graph) {
    super();
    this.graph = graph;
    this.maxPoint = this.getMax();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize((int) maxPoint.getX() * SCALE + 20, (int) maxPoint.getY() * SCALE + 20);
    this.setLocation(200, 200);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

    boolean[][] edges = this.graph.getEdges();
    int numberOfNodes = this.graph.getNumberOfVertices();
    for (int row = 0; row < numberOfNodes; row++) {
      for (int column = 0; column < numberOfNodes; column++) {
        if (edges[row][column]) {
          Vertex start = this.graph.getVertexAt(row);
          Vertex end = this.graph.getVertexAt(column);
          g2.draw(new Line2D.Double(
                  start.getPosition().getX() * SCALE,
                  start.getPosition().getY()* SCALE,
                  end.getPosition().getX()* SCALE,
                  end.getPosition().getY()* SCALE));
          
          Ellipse2D.Double startNode = new Ellipse2D.Double(start.getPosition().getX() * SCALE-5, start.getPosition().getY() * SCALE-5, 10, 10);
          g2.setColor(start.getColor());
          g2.fill(startNode);
          
          Ellipse2D.Double endNode = new Ellipse2D.Double(end.getPosition().getX() * SCALE-5, end.getPosition().getY() * SCALE-5, 10, 10);
          g2.setColor(end.getColor());
          g2.fill(endNode);
          
          g2.setColor(Color.black);
        }
      }
    }
  }

  private Point2D getMax() {
    double xMax = -Integer.MAX_VALUE;
    double yMax = -Integer.MAX_VALUE;
    Vertex[] vertices = graph.getVertices();

    for (int nodeIndex = 0; nodeIndex < graph.getNumberOfVertices(); nodeIndex++) {
      if (vertices[nodeIndex].getPosition().getX() > xMax) {
        xMax = vertices[nodeIndex].getPosition().getX();
      }
      if (vertices[nodeIndex].getPosition().getY() > yMax) {
        yMax = vertices[nodeIndex].getPosition().getY();
      }
    }
    return new Point2D.Double(xMax, yMax);
  }

  public void setGraphData(GraphColoringPuzzleState graph) {
    this.graph = graph;
  }
}

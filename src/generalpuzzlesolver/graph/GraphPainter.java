package generalpuzzlesolver.graph;

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
  private Point2D sizePoint, shiftPoint;
  private int scale = 1;

  public GraphPainter(GraphColoringPuzzleState graph, int scale) {
    super();
    this.graph = graph;
    this.calculateSizePoints();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setAlwaysOnTop(true);
    this.scale = scale;
    this.setSize((int) sizePoint.getX() * this.scale + 80, (int) sizePoint.getY() * this.scale + 80);
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
                  start.getPosition().getX() * this.scale + this.shiftPoint.getX() * this.scale+60,
                  start.getPosition().getY() * this.scale + this.shiftPoint.getY() * this.scale+60,
                  end.getPosition().getX() * this.scale + this.shiftPoint.getX() * this.scale +60,
                  end.getPosition().getY() * this.scale + this.shiftPoint.getY() * this.scale+60));

          Ellipse2D.Double startNode = new Ellipse2D.Double(start.getPosition().getX() * this.scale - 5 + this.shiftPoint.getX() * this.scale + 60, start.getPosition().getY() * this.scale - 5 + this.shiftPoint.getY() * this.scale + 60, 10, 10);
          g2.setColor(start.getColor());
          g2.fill(startNode);

          Ellipse2D.Double endNode = new Ellipse2D.Double(end.getPosition().getX() * this.scale - 5 + this.shiftPoint.getX() + 60, end.getPosition().getY() * this.scale - 5 + this.shiftPoint.getY() + 60, 10, 10);
          g2.setColor(end.getColor());
          g2.fill(endNode);

          g2.setColor(Color.black);
        }
      }
    }
  }

  private void calculateSizePoints() {
    double xMax = 0, yMax = 0, xMin = 0, yMin = 0;
    Vertex[] vertices = graph.getVertices();

    for (int nodeIndex = 0; nodeIndex < graph.getNumberOfVertices(); nodeIndex++) {
      xMax = Math.max(vertices[nodeIndex].getPosition().getX(), xMax);
      yMax = Math.max(vertices[nodeIndex].getPosition().getY(), yMax);
      xMin = Math.min(vertices[nodeIndex].getPosition().getX(), xMin);
      yMin = Math.min(vertices[nodeIndex].getPosition().getY(), yMin);
    }
    this.sizePoint = new Point2D.Double(Math.abs(xMin) + xMax, Math.abs(yMin) + yMax);
    this.shiftPoint = new Point2D.Double(Math.abs(xMin), Math.abs(yMin));
  }

  public void setGraphData(GraphColoringPuzzleState graph) {
    this.graph = graph;
  }
}

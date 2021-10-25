package SortingPolygons;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class PolygonFrame extends JFrame
{
    // Build a method to compare points by their y values, breaking ties with
    // the x value. As this type of comparison does not need any data other
    // than the two points being tested, there is no need to build a
    // Comparator class, a method is sufficient.
    public static int comparePointByY(Point2D.Double o1, Point2D.Double o2)
    {
        double result = (o2.getY() - o1.getY());
        
        if(result > 0) return -1;
        if(result < 0) return 1;
        if(result == 0){
            double resultX = (o2.getX() - o1.getX());
            if(resultX > 0) return -1;
            if(resultX < 0) return 1;
        }
        return 0;
    }

    // Build a Comparator class that compares points by their the polar angle
    // (look for helpful functions in java.Math and Point2D) to a given point p
    // breaking ties by the distance to p. We can then use this Comparator to
    // sort collections or search for minimums and maximums. As this type of
    // comparison depends on additional data (the point p) making a class for
    // it makes sense. Care must be taken to make this a total ordering.
    public static class CompareByPolarAngleToPoint implements Comparator<Point2D.Double>
    {
        private final Point2D.Double p;

        public CompareByPolarAngleToPoint(Point2D.Double p)
        {
           this.p = p;
        }

        @Override
        public int compare(Point2D.Double o1, Point2D.Double o2)
        {
            double theta = Math.atan2(o1.y - p.y, o1.x - p.x);
            double theta2 = Math.atan2(o2.y - p.y, o2.x - p.x);
            double result = (theta2 - theta);
        

            if(result > 0) return -1;
            if(result < 0) return 1;
            if(result == 0){
                double resultD = p.distance(o2) - p.distance(o1);
                if(resultD > 0) return -1;
                if(resultD < 0) return 1;
            }
            return 0;

        }
    }

    // Do not edit the remaining code. However, you should read it to see how your
    // comparators are being used.
    
    // Reads in points and draws them
    public static void main(String[] args)
    {
        // Read in all of our points
        List<Point2D.Double> pointList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextDouble())
        {
            pointList.add(new Point2D.Double(scanner.nextDouble(), scanner.nextDouble()));
        }

        // Find the point with the smallest y-coordinate. Here we will use the
        // Method reference syntax to pick the comparisons function.
        Point2D.Double p = Collections.min(pointList, PolygonFrame::comparePointByY);

        // Sort the points using p.
        pointList.sort(new CompareByPolarAngleToPoint(p));

        // Print the points
        System.out.println(pointList);

        // Display the points if we can
        if (!GraphicsEnvironment.isHeadless())
        {
            new PolygonFrame(pointList).setVisible(true);
        }
    }

    // A list to hold all of the points
    private final List<Point2D.Double> pointList;

    // Some constants to control how things look.
    public static final String WINDOW_TITLE = "Polygon";
    public static final int POINT_SIZE = 8;
    public static final int STARTING_WIDTH = 800;
    public static final int STARTING_HEIGHT = 800;
    public static final Color BACKGROUND = Color.BLACK;
    public static final Color FOREGROUND = Color.GREEN;
    private static final long serialVersionUID=1L;

    // An inner class to deal with all of the drawing
    private class DrawingPanel extends JPanel
    {
        private static final long serialVersionUID=1L;

        // Build a panel with the colors that have been picked
        public DrawingPanel()
        {
            setBackground(BACKGROUND);
            setForeground(FOREGROUND);
        }

        // Scale the given points to fit the panel
        private List<Point2D.Double> scalePoints()
        {
            // Find the bounding box for the points
            double minX  = Double.MAX_VALUE;
            double minY  = Double.MAX_VALUE;
            double maxX  = Double.MIN_VALUE;
            double maxY  = Double.MIN_VALUE;
            for(Point2D.Double point : pointList)
            {
                if(point.x < minX) minX = point.x;
                if(point.x > maxX) maxX = point.x;
                if(point.y < minY) minY = point.y;
                if(point.y > maxY) maxY = point.y;
            }

            // Add a 5% margin to all sides
            double boxWidth = maxX - minX;
            double boxHeight = maxY - minY;
            minX -= 0.05 * boxWidth;
            minY -= 0.05 * boxHeight;
            maxX += 0.05 * boxWidth;
            maxY += 0.05 * boxHeight;
            boxWidth = maxX - minX;
            boxHeight = maxY - minY;

            // Scale all of the points to fill the view
            List<Point2D.Double> result = new ArrayList<>(pointList.size());

            // Create the linear transformation to scale the x and y values by
            // mapping (minX, maxY) -> (0,0) and (maxX, minY) -> (width, height).
            double aX = getWidth() / boxWidth;
            double bX = -minX * getWidth() / boxWidth;
            double aY = -getHeight() / boxHeight;
            double bY = maxY * getHeight() / boxHeight;

            // Apply the mapping to each point
            for(Point2D.Double p : pointList)
            {
                result.add(new Point2D.Double(aX * p.x + bX, aY * p.y + bY));
            }

            return result;
        }

        // Draw all of our points in the given order
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Get the points in our view
            List<Point2D.Double> scaledPoints = scalePoints();

            // Draw all of the points.
            for(Point2D.Double p : scaledPoints)
            {
                g2d.fill(new Ellipse2D.Double(p.x - POINT_SIZE / 2.0, p.y - POINT_SIZE / 2.0, POINT_SIZE, POINT_SIZE));
            }

            // Draw all of the lines.
            for(int i = 0; i < scaledPoints.size() - 1; i++)
            {
                Point2D.Double start = scaledPoints.get(i);
                Point2D.Double end = scaledPoints.get(i + 1);
                g2d.draw(new Line2D.Double(start, end));
            }
            g2d.draw(new Line2D.Double(scaledPoints.get(scaledPoints.size() - 1), scaledPoints.get(0)));
        }
    }

    // Creat a window with the given settings and a drawing panel for our polygon.
    public PolygonFrame(List<Point2D.Double> pointList)
    {
        super(WINDOW_TITLE);
        this.pointList = pointList;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(STARTING_WIDTH, STARTING_HEIGHT);
        add(new DrawingPanel());
    }

}
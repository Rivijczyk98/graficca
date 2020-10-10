package pl.edu.pb.wi.grafika;

import java.awt.*;

public class Circle extends Drawable {
    public Circle(Point[] points) {
        super(points);

        shape = Shape.CIRCLE;
    }

    @Override
    public void Draw(Graphics g) {
        int maxX = Math.max(points[0].getX(), points[1].getX());
        int minX = Math.min(points[0].getX(), points[1].getX());

        int maxY = Math.max(points[0].getY(), points[1].getY());
        int minY = Math.min(points[0].getY(), points[1].getY());

        int max = Math.max(maxX - minX, maxY - minY);

        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(minX, minY, max, max);

        g.setColor(Color.BLACK);
        g.drawOval(minX, minY, max, max);

        points[0] = new Point(minX, minY);
        points[1] = new Point(minX + max, minY + max);
    }

    @Override
    public boolean isVerticle(Point src, Point point1, Point point2) {
        return super.isVerticle(src, point1, point2);
    }

    @Override
    public boolean Contains(Point point) {
        return Between(point, points[0], points[1]);
    }

    @Override
    public boolean Contains(int x, int y) {
        return Contains(new Point(x,y));
    }

    @Override
    public boolean Between(Point src, Point point1, Point point2) {

        double radius =  Math.abs( point1.getX() - point2.getX() ) / 2f;

        double centerX = point1.getX() + radius ;
        double centerY = point1.getY() + radius ;

        double toCenter = Math.sqrt(Math.pow(src.getX() - centerX, 2) + Math.pow(src.getY() - centerY, 2));

        return Math.abs(toCenter - radius) < 1.2f;
    }

    @Override
    public Point[] RecalculatePoints(Point startPoint, Point finalPoint) {
        return super.RecalculatePoints(startPoint, finalPoint);
    }
}

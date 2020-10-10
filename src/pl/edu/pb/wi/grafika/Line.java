package pl.edu.pb.wi.grafika;

import java.awt.*;

public class Line extends Drawable {

    public Line(Point[] points) {
        super(points);

        shape = Shape.LINE;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawLine(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
    }

    @Override
    public boolean isVerticle(Point src, Point point1, Point point2) {
        return
                (
                        Math.abs(src.getY() - point1.getY()) < error
                            && Math.abs(src.getX() - point1.getX()) < error
                ) || (
                        Math.abs(src.getX() - point2.getX()) < error
                            && Math.abs(src.getY() - point2.getY()) < error
                );
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
        double AC = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(),2));
        double AB = Math.sqrt(Math.pow(point1.getX() - src.getX(),2) + Math.pow(point1.getY() - src.getY(),2));
        double BC = Math.sqrt(Math.pow(point2.getX() - src.getX(),2) + Math.pow(point2.getY() - src.getY(),2));

        return Math.abs(AB + BC - AC) < 0.5f;
    }

    @Override
    public Point getClosest(Point src) {

        double[] distances = new double[2];

        distances[0] =
                Math.sqrt(
                        Math.pow(src.getX() - points[0].getX(), 2)
                                + Math.pow(src.getY() - points[0].getY(), 2)
                );

        distances[1] =
                Math.sqrt(
                        Math.pow(src.getX() - points[1].getX(), 2)
                                + Math.pow(src.getY() - points[1].getY(), 2)
                );

        double min = Math.min(distances[0], distances[1]);

        if(min == distances[0]){
            return new Point(points[0].getX(), points[0].getY());
        } else {
            return new Point(points[1].getX(), points[1].getY());
        } 
    }

    @Override
    public Point[] RecalculatePoints(Point startPoint, Point finalPoint) {
        Point[] tempPoints = new Point[]{
                new Point(points[0].getX(), points[0].getY()),
                new Point(points[1].getX(), points[1].getY())
        };

        if(startPoint.Equals(points[0])) {
            tempPoints[0].setPoints(finalPoint.getX(), finalPoint.getY());
        } else {
            tempPoints[1].setPoints(finalPoint.getX(), finalPoint.getY());
        }

        return tempPoints;
    }
}

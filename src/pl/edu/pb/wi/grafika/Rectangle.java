package pl.edu.pb.wi.grafika;

import java.awt.*;

public class Rectangle extends Drawable {

    public Rectangle(Point[] points) {
        super(points);

        shape = Shape.RECTANGLE;
    }

    @Override
    public void setPoints(int x1, int y1, int x2, int y2) {
        super.setPoints(x1, y1, x2, y2);
    }

    @Override
    public void Draw(Graphics g) {
        int maxX = Math.max(points[0].getX(), points[1].getX());
        int minX = Math.min(points[0].getX(), points[1].getX());

        int maxY = Math.max(points[0].getY(), points[1].getY());
        int minY = Math.min(points[0].getY(), points[1].getY());

        g.drawRect( minX, minY, maxX - minX,maxY - minY);
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
        return ((Math.abs(src.getY() - point1.getY()) < error
                || Math.abs(src.getY() - point2.getY()) < error )
                && src.getX() <= Math.max(point1.getX(), point2.getX())
                && src.getX() >= Math.min(point1.getX(), point2.getX())) ||
                ((Math.abs(src.getX() - point1.getX()) < error
                        || Math.abs(src.getX() - point2.getX()) < error)
                        && src.getY() <= Math.max(point1.getY(), point2.getY())
                        && src.getY() >= Math.min(point1.getY(), point2.getY()));
    }

}

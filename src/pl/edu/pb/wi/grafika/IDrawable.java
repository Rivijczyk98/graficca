package pl.edu.pb.wi.grafika;

import java.awt.*;

public interface IDrawable {

    void Draw(Graphics g);
    boolean isVerticle(Point src, Point point1, Point point2);
    boolean Between(Point src, Point point1, Point point2);
    boolean Contains(int x, int y);
    boolean Contains(Point point);
    Point getClosest(Point src);
    Point[] RecalculatePoints(Point startPoint, Point finalPoint);

}

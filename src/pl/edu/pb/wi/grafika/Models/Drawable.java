package pl.edu.pb.wi.grafika.Models;

import pl.edu.pb.wi.grafika.Models.Interfaces.IDrawable;

import java.awt.*;

public abstract class Drawable implements IDrawable {

    protected Shape shape;
    protected Point[] points;

    public Drawable(Point[] _points) {
        super();
        this.points = new Point[]{
                new Point(_points[0]),
                new Point(_points[1])
        };

        shape = Shape.LINE;
    }

    public Shape getShape() {
        return shape;
    }

    public Point[] getPoints() {
        return points;
    }
}

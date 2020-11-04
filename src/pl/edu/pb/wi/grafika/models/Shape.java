package pl.edu.pb.wi.grafika.models;

import java.awt.*;
import java.util.LinkedList;

public class Shape {

    public LinkedList<Point> verticles = new LinkedList<>();

    public Shape(Point p) {
        super();

        verticles.add(p);
    }

    public Shape(LinkedList<Point> points){
        super();

        verticles = new LinkedList<>(points);
    }
}

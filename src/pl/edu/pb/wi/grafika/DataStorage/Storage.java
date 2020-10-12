package pl.edu.pb.wi.grafika.DataStorage;

import pl.edu.pb.wi.grafika.Models.Drawable;
import pl.edu.pb.wi.grafika.Models.Shape;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Storage {

    private static Storage instance;

    private static List<Drawable> graphics;
    private static Drawable selectedObject;
    private static Shape selectedShape;
    public static Point mousePosition;

    public static Storage getInstance(){
        return instance;
    }

    public Storage() {
        instance = this;
        graphics = new LinkedList<>();
        mousePosition = new Point();
        selectedShape = Shape.LINE;
    }

    public static List<Drawable> getGraphics() {
        return graphics;
    }

    public static Point getMousePosition() {
        return mousePosition;
    }

    public static void setMousePosition(Point mousePosition) {
        Storage.mousePosition = mousePosition;
    }

    public static Shape getSelectedShape() {
        return selectedShape;
    }

    public static void setSelectedShape(Shape selectedShape) {
        Storage.selectedShape = selectedShape;
    }
}

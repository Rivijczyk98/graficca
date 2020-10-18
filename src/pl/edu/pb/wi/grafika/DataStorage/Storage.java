package pl.edu.pb.wi.grafika.DataStorage;

import pl.edu.pb.wi.grafika.UI.Elements.PaintPanel;
import pl.edu.pb.wi.grafika.UI.MainWindow;

import java.awt.*;

public class Storage {

    private static Storage instance;

    public static MainWindow mainWindow;
    public static Point mousePosition;

    public static Storage getInstance(){
        return instance;
    }

    public Storage() {
        instance = this;
        mousePosition = new Point();
    }

    public static Point getMousePosition() {
        return mousePosition;
    }

    public static void setMousePosition(Point mousePosition) {
        Storage.mousePosition = mousePosition;
    }

    public static void setErrorMessage(String errorMessage){
        mainWindow.setErrorText(errorMessage);
    }
}

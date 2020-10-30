package pl.edu.pb.wi.grafika.DataStorage;

import pl.edu.pb.wi.grafika.UI.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Storage {

    private static Storage instance;
    private static BufferedImage originalImage;
    private static BufferedImage editedImage;
    private static int[][] histograms = null;

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

    public static String getErrorMessage(){
        return mainWindow.getErrorMessage();
    }

    public static void setErrorMessage(String errorMessage){
        mainWindow.setErrorText(errorMessage);
    }

    public static void newImage(BufferedImage _original){
        originalImage = _original;
        editedImage = _original;
        resetImage();
    }

    public static BufferedImage getEditedImage() {
        return editedImage;
    }

    public static void setEditedImage(BufferedImage img){
        editedImage = img;
        mainWindow.setImage(editedImage);
    }

    public static boolean isOriginalImagePresent(){
        return originalImage != null;
    }

    public static void resetImage(){
        editedImage = originalImage;
        mainWindow.setImage(originalImage);
    }

    public static int[][] getHistograms() {
        return histograms;
    }

    public static void setHistograms(int[][] histograms) {
        Storage.histograms = histograms;
    }
}

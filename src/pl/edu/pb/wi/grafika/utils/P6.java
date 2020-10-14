package pl.edu.pb.wi.grafika.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class P6 extends PPM {

    /**
     * @param file - file selected by user in dialog
     * @return BufferedImage image read from P6 format using recursive methods
     * @throws IOException trowed on file or read error
     */
    public static BufferedImage loadRecursive(File file) throws IOException {
        int columns = 0;
        int rows = 0;
        int maxSize = 255;

        if(!file.exists()) return null;

        BufferedReader reader = new BufferedReader(new FileReader(file));

//        Ignore first uncommented line - P6 or throw when it's different file system
        if(!Objects.equals(nextIgnoreComments(reader), "P6")) return null;

        String[] line = Objects.requireNonNull(nextIgnoreComments(reader)).split(" ");
        columns = Integer.parseInt(line[0]);
        rows = Integer.parseInt(line[1]);

        maxSize = nextInt(reader);

        BufferedImage image = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_RGB);

        int posX = 0, posY = 0;



        return image;
    }

}

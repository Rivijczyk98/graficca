package pl.edu.pb.wi.grafika.utils;

import pl.edu.pb.wi.grafika.DataStorage.Storage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class HistogramUtils {

    private static int[] createLUT(int[] values){
        int size = 0;
        for (int v : values)
        {
            size += v;
        }

        double minValue = 0;
        for (int i = 0; i < 256; i++)
        {
            if (values[i] != 0)
            {
                minValue = values[i];
                break;
            }
        }

        int[] result = new int[256];
        double sum = 0;
        for (int i = 0; i < 256; i++)
        {
            sum += values[i];
            result[i] = (int)(((sum - minValue) / (size - minValue)) * 255.0);
        }

        return result;
    }

    private static int[] lutToStretch(int[] values){
        int minValue = 0;
        int maxValue = 255;

        for(int i = 0; i < 256; i++)
        {
            if(values[i] != 0)
            {
                minValue = i;
                break;
            }
        }

        for(int i = 255; i >= 0; i--)
        {
            if(values[i] != 0)
            {
                maxValue = i;
                break;
            }
        }

        int[] result = new int[256];
        double a = 255f / (maxValue - minValue);
        for(int i = 0; i < 256; i++)
        {
            result[i] = (int)(a * (i - minValue));
        }

        return result;
    }

    public static BufferedImage stretch(BufferedImage bufferedImage, int[][] histogram)
    {
        int[] lutRed = lutToStretch(histogram[0]);
        int[] lutGreen = lutToStretch(histogram[1]);
        int[] lutBlue = lutToStretch(histogram[2]);

        histogram = new int[4][256];

        int WIDTH = bufferedImage.getWidth();
        int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                Color pixel = new Color(bufferedImage.getRGB(x, y));
                Color newPixel = new Color(
                        lutRed[pixel.getRed()],
                        lutGreen[pixel.getGreen()],
                        lutBlue[pixel.getBlue()]
                );
                img.setRGB(x, y, newPixel.getRGB());
                histogram[0][newPixel.getRed()]++;
                histogram[1][newPixel.getGreen()]++;
                histogram[2][newPixel.getBlue()]++;
                histogram[3][(newPixel.getRed() + newPixel.getGreen() + newPixel.getBlue())/3]++;
            }
        }

        Storage.setHistograms(histogram);

        return img;
    }

    public static BufferedImage equalize(BufferedImage bufferedImage, int[][] histogram){
        int[] lutRed = createLUT(histogram[0]);
        int[] lutGreen = createLUT(histogram[1]);
        int[] lutBlue = createLUT(histogram[2]);

        histogram = new int[4][256];

        int WIDTH = bufferedImage.getWidth();
        int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                Color pixel = new Color(bufferedImage.getRGB(x, y));
                Color newPixel = new Color(
                        lutRed[pixel.getRed()],
                        lutGreen[pixel.getGreen()],
                        lutBlue[pixel.getBlue()]
                );
                img.setRGB(x, y, newPixel.getRGB());
                histogram[0][newPixel.getRed()]++;
                histogram[1][newPixel.getGreen()]++;
                histogram[2][newPixel.getBlue()]++;
                histogram[3][(newPixel.getRed() + newPixel.getGreen() + newPixel.getBlue())/3]++;
            }
        }

        Storage.setHistograms(histogram);

        return img;
    }

    public static int[][] calculateHistograms(BufferedImage bufferedImage){
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[][] histograms = new int[4][256];

        for (int i = 0; width > i; i++)
        {
            for (int j = 0; height > j; j++)
            {
                Color c = new Color(bufferedImage.getRGB(i,j));
                histograms[0][c.getRed()]++;
                histograms[1][c.getGreen()]++;
                histograms[2][c.getBlue()]++;
                histograms[3][(c.getRed() + c.getGreen() + c.getBlue()) / 3]++;
            }
        }

        return histograms;
    }
}

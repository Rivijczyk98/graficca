package pl.edu.pb.wi.grafika.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinarisationUtils {

    public static BufferedImage otsu(BufferedImage image){
        int WIDTH = image.getWidth();
        int HEIGHT = image.getHeight();
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        BufferedImage bufferedImage = ImageBasicsUtils.convertToGrayscale(image);

        int[] h = getHistogram(bufferedImage);

        float p1, p2, p12;
        float max = -1, threshold = 0;

        for(int i = 0; i < 256; i++)
        {
            p1 = px(0, i, h);
            p2 = px(i + 1, 255, h);

            p12 = p1 * p2;

            if (p12 == 0) p12 = 1;

            float diff = (mx(0, i, h) * p2) - (mx(i + 1, 255, h) * p1);

            float v = (float)diff * diff / p12;
            if(v > max)
            {
                max = v;
                threshold = i;
            }
        }

        for (int x = 0; x < WIDTH; x ++)
        {
            for(int y = 0; y < HEIGHT; y++)
            {
                int pixel = new Color(bufferedImage.getRGB(x,y)).getRed();
                if ( pixel > threshold )
                {
                    img.setRGB(x,y,Color.WHITE.getRGB());
                }
            }
        }

        return img;
    }

    private static float px(int start, int end, int[] histogram)
    {
        int sum = 0;

        for(int i = start; i < end; i++)
        {
            sum += histogram[i];
        }

        return (float)sum;
    }

    private static float mx(int start, int end, int[] histogram)
    {
        int sum = 0;
        int i;

        for (i = start; i < end; i++)
            sum += i * histogram[i];

        return (float)sum;
    }

    private static int[] getHistogram(BufferedImage image){
        int[] histogram = new int[256];

        for(int x = 0; x < image.getWidth(); x++ ){
            for(int y = 0; y < image.getHeight(); y++ ){
                histogram[new Color(image.getRGB(x,y)).getRed()]++;
            }
        }

        return histogram;
    }

    public static BufferedImage percentage(BufferedImage image, float percentage){
        int WIDTH = image.getWidth();
        int HEIGHT = image.getHeight();
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        BufferedImage bufferedImage = ImageBasicsUtils.convertToGrayscale(image);

        int[] lut = new int[256];

        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                Color c = new Color(image.getRGB(x,y));
                lut[c.getRed()]++;
            }
        }

        int[] lut2 = new int[256];

        int pixels = WIDTH * HEIGHT;
        double times = ((double)percentage/100) * ((double)pixels);
        int sum = 0;

        for(int i = 0; i < 256; i++){
            sum += lut[i];
            if(sum > times){
                lut2[i] = 1;
            }
        }

        for(int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y ++){
                int c = new Color(bufferedImage.getRGB(x,y)).getRed();

                if(lut2[c] == 1){
                    img.setRGB(x,y, Color.WHITE.getRGB());
                }
            }
        }

        return img;
    }

    public static BufferedImage manual(BufferedImage image, int amount){
        int WIDTH = image.getWidth();
        int HEIGHT = image.getHeight();
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        BufferedImage bufferedImage = ImageBasicsUtils.convertToGrayscale(image);

        for(int x = 0; x < WIDTH; x ++){
            for(int y = 0; y < HEIGHT; y++){
                if(new Color(bufferedImage.getRGB(x,y)).getRed() > amount){
                    img.setRGB(x,y, Color.WHITE.getRGB());
                }
            }
        }

        return img;
    }

}

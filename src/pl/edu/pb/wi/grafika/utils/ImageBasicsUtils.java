package pl.edu.pb.wi.grafika.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ImageBasicsUtils {

    public static BufferedImage add(BufferedImage bufferedImage, int amount){

        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ) {
                img.setRGB(x, y, addToPixel(bufferedImage.getRGB(x,y), amount));
            }
        }

        return img;
    }

    private static int addToPixel(int rgb, int amount){

        Color c = new Color(rgb);

        return new Color(
                        (c.getRed() + amount)%256,
                        (c.getGreen() + amount)%256,
                        (c.getBlue() + amount)%256
                ).getRGB();

    }

    public static BufferedImage subtract(BufferedImage bufferedImage, int amount){

        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ) {
                img.setRGB(x, y, subtractFromPixel(bufferedImage.getRGB(x,y), amount));
            }
        }

        return img;
    }

    private static int subtractFromPixel(int rgb, int amount){
        Color c = new Color(rgb);

        return new Color(
                 c.getRed() < amount ? 0 : (c.getRed() - amount),
                 c.getGreen() < amount ? 0 : (c.getGreen() - amount),
                 c.getBlue() < amount ? 0 : (c.getBlue() - amount)
        ).getRGB();
    }

    public static BufferedImage multiply(BufferedImage bufferedImage, int amount){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ) {
                img.setRGB(x, y, multiplyPixel(bufferedImage.getRGB(x,y), amount));
            }
        }

        return img;
    }

    private static int multiplyPixel(int rgb, int amount){
        Color c = new Color(rgb);

        return new Color(
                (c.getRed() * amount)%256,
                (c.getGreen() * amount)%256,
                (c.getBlue() * amount)%256
        ).getRGB();
    }

    public static BufferedImage divide(BufferedImage bufferedImage, int amount){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ) {
                img.setRGB(x, y, dividePixel(bufferedImage.getRGB(x,y), amount));
            }
        }

        return img;
    }

    private static int dividePixel(int rgb, int amount){
        Color c = new Color(rgb);

        return new Color(
                0 >= amount ? c.getRed() : (c.getRed() / amount),
                0 >= amount ? c.getGreen() : (c.getGreen() / amount),
                0 >= amount ? c.getBlue() : (c.getBlue() / amount)
        ).getRGB();
    }

    public static double log(int number, int base){
        return (Math.log(number) / Math.log(base));
    }

    public static BufferedImage brighten(BufferedImage bufferedImage){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < WIDTH; x ++){
            for(int y = 0; y < HEIGHT; y++){
                img.setRGB(x, y, brightenPixel(bufferedImage.getRGB(x,y)));
            }
        }

        return img;
    }

    private static int brightenPixel(int value){
        Color c = new Color(value);

        int r = (int)(c.getRed() * log(c.getRed(), 50));
        int g = (int)(c.getGreen() * log(c.getGreen(), 50));
        int b = (int)(c.getBlue() * log(c.getBlue(), 50));

        return new Color(
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
        ).getRGB();

    }

    public static BufferedImage darken(BufferedImage bufferedImage){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < WIDTH; x ++){
            for(int y = 0; y < HEIGHT; y++){
                img.setRGB(x, y, darkenPixel(bufferedImage.getRGB(x,y)));
            }
        }

        return img;
    }

    private static int darkenPixel(int rgb){
        Color c = new Color(rgb);

        int red = (int)(log(c.getRed(), 20) / log(c.getRed(), 10) * c.getRed());
        int green = (int)(log(c.getGreen(), 20) / log(c.getGreen(), 10) * c.getGreen());
        int blue = (int)(log(c.getBlue(), 20) / log(c.getBlue(), 10) * c.getBlue());

        return new Color(red, green, blue).getRGB();
    }

    public static BufferedImage convertToGrayscale(BufferedImage bufferedImage){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < WIDTH; x ++){
            for(int y = 0; y < HEIGHT; y++){
                var pixel = new Color(bufferedImage.getRGB(x, y));
                int R = pixel.getRed();
                int G = pixel.getGreen();
                int B = pixel.getBlue();
                int val = (R + G + B) / 3;
                img.setRGB(x,y, new Color(val, val, val).getRGB());
            }
        }

        return img;
    }


}

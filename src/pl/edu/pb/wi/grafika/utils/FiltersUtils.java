package pl.edu.pb.wi.grafika.utils;

import javafx.scene.effect.Blend;
import pl.edu.pb.wi.grafika.Handlers.InvalidMaskException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public class FiltersUtils {

    private static BufferedImage convolution(BufferedImage bufferedImage, float[][] mask){
        int WIDTH = bufferedImage.getWidth();
        int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int ignore = (int) (Math.ceil(mask.length / 2f) - 1);

        for(int x = 0; x < WIDTH; x++)
        {
            for(int y = 0; y < HEIGHT; y++)
            {
                if(x < ignore || x >= (WIDTH - ignore) || y < ignore || y >= (HEIGHT - ignore)){
                    img.setRGB(x,y, bufferedImage.getRGB(x,y));
                    continue;
                }

                img.setRGB(x, y, getPixel(bufferedImage, x, y, mask));
            }
        }

        return img;
    }

    private static int getPixel(BufferedImage b, int x, int y, float[][] mask){
        double sumR = 0, sumG = 0, sumB = 0;
        int beginX = x - mask.length / 2, beginY = y - mask.length / 2;
        int end = mask.length;

        for(int i = 0; i < end; i++)
        {
            for(int j = 0; j < end; j++)
            {
                Color c = new Color(b.getRGB(beginX + i, beginY + j));
                sumR += c.getRed() * mask[i][j];
                sumG += c.getGreen() * mask[i][j];
                sumB += c.getBlue() * mask[i][j];
            }
        }

        int r = (int)(sumR);
        int g = (int)(sumG);
        int bl = (int)(sumB);

        return new Color(
                r >= 0 ? (Math.min(r, 255)) : 0,
                g >= 0 ? (Math.min(g, 255)) : 0,
                bl >= 0 ? (Math.min(bl, 255)) : 0
        ).getRGB();
    }

    public static BufferedImage gaussianBlur(BufferedImage bufferedImage){
        float[][] mask = {
                {1f/16f,2f/16f,1f/16f},
                {2f/16f,4f/16f,2f/16f},
                {1f/16f,2f/16f,1f/16f}
        };

        return convolution(bufferedImage, mask);
    }

    public static BufferedImage sharpen(BufferedImage bufferedImage){
        float[][] mask = {
                {-1f/9f,-1f/9f,-1f/9f},
                {-1f/9f,2f,-1f/9f},
                {-1f/9f,-1f/9f,-1f/9f}
        };

        return convolution(bufferedImage, mask);
    }

    public static BufferedImage smooth(BufferedImage bufferedImage){
        float[][] mask = {
                {1f/9f,1f/9f,1f/9f},
                {1f/9f,1f/9f,1f/9f},
                {1f/9f,1f/9f,1f/9f}
        };

        return convolution(bufferedImage, mask);
    }

    public static BufferedImage medianFilter(BufferedImage bufferedImage){
        int WIDTH = bufferedImage.getWidth();
        int HEIGHT = bufferedImage.getHeight();

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        int[] maskR = new int[9];
        int[] maskG = new int[9];
        int[] maskB = new int[9];

        for (int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){
                if(x == 0 || x == WIDTH - 1 || y == 0 || y == HEIGHT - 1){
                    img.setRGB(x,y, bufferedImage.getRGB(x,y));
                    continue;
                }

                int counter = 0;

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        Color c = new Color(bufferedImage.getRGB(x - 1 + i, y - 1 + j));
                        maskR[counter] = c.getRed();
                        maskG[counter] = c.getGreen();
                        maskB[counter] = c.getBlue();

                        counter++;
                    }
                }

                Arrays.sort(maskR);
                Arrays.sort(maskG);
                Arrays.sort(maskB);

                img.setRGB(x,y, new Color(maskR[4], maskG[4], maskB[4]).getRGB());
            }
        }

        return img;
    }

    public static BufferedImage sobel(BufferedImage bufferedImage){
        Random r = new Random();
        float[][] mask;
        switch (r.nextInt(8)) {
            case 1:
                mask = new float[][]{
                        {0, -1, -2},
                        {1, 0, -1},
                        {2, 1, 0}
                };
                break;
            case 2:
                mask = new float[][]{
                        {1, 0, -1},
                        {2, 0, -2},
                        {1, 0, -1}
                };
                break;
            case 3:
                mask = new float[][]{
                        {2, 1, 0},
                        {1, 0, -1},
                        {0, -1, -2}
                };
                break;
            case 4:
                mask = new float[][]{
                        {1, 2, 1},
                        {0, 0, 0},
                        {-1, -2, -1}
                };
                break;
            case 5:
                mask = new float[][]{
                        {0, 1, 2},
                        {-1, 0, 1},
                        {-2, -1, 0}
                };
                break;
            case 6:
                mask = new float[][]{
                        {-1, 0, 1},
                        {-2, 0, 2},
                        {-1, 0, 1}
                };
                break;
            case 7:
                mask = new float[][]{
                        {-2, -1, 0},
                        {-1, 0, 1},
                        {0, 1, 2}
                };
                break;
            default:
                mask = new float[][]{
                        {-1, -2, -1},
                        {0, 0, 0},
                        {1, 2, 1}
                };
                break;
        }

        return convolution(bufferedImage, mask);
    }

    public static BufferedImage mask(BufferedImage bufferedImage, String maskText) throws InvalidMaskException {
        String[] lines = maskText.split("\n");
        int HEIGHT = lines.length;
        int WIDTH = lines[0].split(",").length;

        System.out.println("H: " + HEIGHT);
        System.out.println("W: " + WIDTH);

        float[][] mask = new float[HEIGHT][WIDTH];

        if(WIDTH != HEIGHT)
            throw new InvalidMaskException("Width and height of mask doesn't match");
        else if(WIDTH % 2 != 1){
            throw new InvalidMaskException("Size isn't odd number");
        }

        for(int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                mask[i][j] = Float.parseFloat(lines[i].split(",")[j]);
            }
        }

        return convolution(bufferedImage, mask);
    }

}

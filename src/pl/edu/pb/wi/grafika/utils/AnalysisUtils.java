package pl.edu.pb.wi.grafika.utils;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.models.HsvColor;
import pl.edu.pb.wi.grafika.models.Method;
import pl.edu.pb.wi.grafika.models.RgbColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.CookieHandler;

public class AnalysisUtils {

    public static BufferedImage binarise(BufferedImage bufferedImage, Color color1, Color color2, Color color){
        BufferedImage image = ImageBasicsUtils.copy(bufferedImage);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(isInRange(new Color(image.getRGB(x,y)), color1, color2)){
                    image.setRGB(x,y, color.getRGB());
                }
            }
        }

        return image;
    }

    public static BufferedImage morphology(BufferedImage bufferedImage, Method method, Color color){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage original = Storage.getEditedImage();

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ){
                Color c = null;
                switch (method){
                    case DILATION:
                        c = dilation(x,y,bufferedImage, color, new Color(original.getRGB(x,y)));
                        break;
                    case EROSION:
                        c = erosion(x,y,bufferedImage, color, original);
                        break;
                }

                c = c == null ? color : c;
                image.setRGB(x,y, c.getRGB());
            }
        }

        return image;
    }

    private static Color dilation(int x, int y, BufferedImage image, Color color, Color original){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if(x + i < 0 || x + i >= image.getWidth() || y + j < 0 || y + j >= image.getHeight())
                    continue;

                Color c = new Color(image.getRGB(x + i,y + j));
                if(c.equals(color)){
                    return color;
                }
            }
        }

        return original;
    }

    private static Color erosion(int x, int y, BufferedImage bufferedImage, Color color, BufferedImage original){
        if(!new Color(bufferedImage.getRGB(x,y)).equals(color)) return new Color(original.getRGB(x,y));

        for(int i = -1; i < 1; i++){
            for(int j = -1; j < 1; j++){
                if(x + i < 0 || x + i >= bufferedImage.getWidth() || y + j < 0 || y + j >= bufferedImage.getHeight())
                    continue;

                Color c = new Color(bufferedImage.getRGB(x + i, y + j));
                if(!c.equals(color)){
                    return new Color(original.getRGB(x, y));
                }
            }
        }

        return color;
    }

    private static boolean isInRange(Color c, Color s1, Color s2){

        int range = (int) Math.sqrt(
                Math.pow(s1.getRed() - s2.getRed(), 2) +
                Math.pow(s1.getGreen() - s2.getGreen(), 2) +
                Math.pow(s1.getBlue() - s2.getBlue(), 2)
        );

        int cs1 = (int) Math.sqrt(
                Math.pow(s1.getRed() - c.getRed(), 2) +
                        Math.pow(s1.getGreen() - c.getGreen(), 2) +
                        Math.pow(s1.getBlue() - c.getBlue(), 2)
        );

        int cs2 = (int) Math.sqrt(
                Math.pow(c.getRed() - s2.getRed(), 2) +
                        Math.pow(c.getGreen() - s2.getGreen(), 2) +
                        Math.pow(c.getBlue() - s2.getBlue(), 2)
        );

        return (
                cs1 <= range && cs2 <= range
                );
    }

    private static boolean between(float x, float i, float j){
        return Math.min(i,j) <= x && Math.max(i,j) >= x;
    }

    public static double result(BufferedImage bufferedImage, Color color){
        int greens = 0;

        for(int i = 0; i < bufferedImage.getWidth(); i++){
            for(int j = 0; j < bufferedImage.getHeight(); j++){
                if(new Color(bufferedImage.getRGB(i,j)).equals(color)){
                    greens++;
                }
            }
        }

        return greens / (float)(bufferedImage.getHeight() * bufferedImage.getWidth()) * 100f;
    }

}

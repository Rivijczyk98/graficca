package pl.edu.pb.wi.grafika.utils;

import pl.edu.pb.wi.grafika.models.Method;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MathematicalMorphology {

    public static BufferedImage opening(BufferedImage bufferedImage){
        return morphology(
                morphology(bufferedImage, Method.EROSION)
                , Method.DILATION
        );
    }

    public static BufferedImage closing(BufferedImage bufferedImage){
        return morphology(
                morphology(bufferedImage, Method.DILATION)
                , Method.EROSION
        );
    }

    public static BufferedImage hitOrMiss(BufferedImage bufferedImage, Method method){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage prev;
        BufferedImage next = ImageBasicsUtils.copy(bufferedImage);

        int[][][] masks;
        if(method == Method.HIT_OR_MISS_THINNING) {
            masks = new int[][][]{
                    {{0,0,0},{-1,1,-1},{1,1,1}},
                    {{1,-1,0},{1,1,0},{1,-1,0}},
                    {{1,1,1},{-1,1,-1},{0,0,0}},
                    {{0,-1,1},{0,1,1},{0,-1,1}},
                    {{-1,0,0},{1,1,0},{-1,1,-1}},
                    {{-1,1,-1},{1,1,0},{-1,0,0}},
                    {{-1,1,-1},{0,1,1},{0,0,-1}},
                    {{0,0,-1},{0,1,1},{-1,1,-1}},
            };
        } else {
            masks = new int[][][]{
                    {{1,1,-1},{1,0,-1},{1,-1,0}},
                    {{1,1,1},{-1,0,1},{0,-1,-1}},
                    {{0,-1,1},{-1,0,1},{-1,1,1}},
                    {{-1,-1,0},{1,0,-1},{1,1,1}},
                    {{-1,1,1},{-1,0,1},{1,-1,0}},
                    {{1,-1,-1},{-1,0,1},{0,1,1}},
                    {{0,-1,1},{1,0,-1},{1,1,-1}},
                    {{1,1,0},{1,0,-1},{-1,-1,1}},
            };
        }

        prev = ImageBasicsUtils.copy(next);

        for (int[][] mask : masks) {
            for(int x = 0; x < WIDTH; x++){
                for(int y = 0; y < HEIGHT; y++){

                    boolean matches = matchesMask(prev, x, y, mask);
//                    Color c = prev.getRGB(x,y) == Color.BLACK.getRGB() ? Color.WHITE : Color.BLACK ;
                    switch (method) {
                        case HIT_OR_MISS_THINNING:
                            if(matches)
                                next.setRGB(
                                        x, y, Color.BLACK.getRGB()
                                );
                            break;
                        case HIT_OR_MISS_THICKENING:
                            if(matches)
                                next.setRGB(
                                        x, y, Color.WHITE.getRGB()
                                );
                            break;
                    }
                }
            }
        }

        return next;
    }

    private static boolean matchesAnyMask(BufferedImage bufferedImage, int x, int y, int[][][] masks){
        for (int[][] mask: masks) {
            if(matchesMask(bufferedImage,x,y,mask)) return true;
        }

        return false;
    }

    private static boolean matchesMask(BufferedImage bufferedImage, int x, int y, int[][] mask){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(mask[i + 1][j + 1] == -1) continue;
                if(x + i < 0 || x + i >= bufferedImage.getWidth() || y + j < 0 || y + j >= bufferedImage.getHeight())
                    continue;

                int val = new Color(bufferedImage.getRGB(x + i,y + j)).equals(Color.WHITE) ? 1 : 0;
                if(val != mask[i + 1][j + 1]) return false;
            }
        }

        return true;
    }

    private static boolean isDifferentImage(BufferedImage prev, BufferedImage next){
        final int WIDTH = prev.getWidth();
        final int HEIGHT = next.getHeight();

        for(int x = 0; x < WIDTH; x++ ) {
            for (int y = 0; y < HEIGHT; y++) {
                 if(prev.getRGB(x,y) != next.getRGB(x,y)) return true;
            }
        }

        return false;
    }

    public static BufferedImage morphology(BufferedImage bufferedImage, Method method){
        final int WIDTH = bufferedImage.getWidth();
        final int HEIGHT = bufferedImage.getHeight();

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < WIDTH; x++ ){
            for(int y = 0; y < HEIGHT; y++ ){
                Color c = null;
                switch (method){
                    case DILATION:
                        c = dilation(x,y,bufferedImage);
                        break;
                    case EROSION:
                        c = erosion(x,y,bufferedImage);
                        break;
                }

                c = c == null ? Color.WHITE : c;
                image.setRGB(x,y, c.getRGB());
            }
        }

        return image;
    }

    private static Color dilation(int x, int y, BufferedImage image){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if(x + i < 0 || x + i >= image.getWidth() || y + j < 0 || y + j >= image.getHeight())
                    continue;

                Color c = new Color(image.getRGB(x + i,y + j));
                if(c.equals(Color.BLACK)){
                    return Color.BLACK;
                }
            }
        }

        return Color.WHITE;
    }

    private static Color erosion(int x, int y, BufferedImage bufferedImage){
        if(new Color(bufferedImage.getRGB(x,y)).equals(Color.WHITE)) return Color.WHITE;

        for(int i = -1; i < 1; i++){
            for(int j = -1; j < 1; j++){
                if(x + i < 0 || x + i >= bufferedImage.getWidth() || y + j < 0 || y + j >= bufferedImage.getHeight())
                    continue;

                Color c = new Color(bufferedImage.getRGB(x + i, y + j));
                if(c.equals(Color.WHITE)){
                    return Color.WHITE;
                }
            }
        }

        return Color.BLACK;
    }

}


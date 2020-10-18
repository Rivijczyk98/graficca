package pl.edu.pb.wi.grafika.utils;

import pl.edu.pb.wi.grafika.DataStorage.Storage;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;

public class P6 extends PPM {

    private static byte prevByte;

    /**
     * @param file - file selected by user in dialog
     * @return BufferedImage image read from P6 format using recursive methods
     * @throws IOException trowed on file or read error
     */
    public static BufferedImage load(File file) throws IOException {

        int columns = 0;
        int rows = 0;
        int maxSize = 0;

        byte[] buff = new byte[3];

        if(!file.exists()){
            Storage.setErrorMessage("File doesn't exist!");
            return null;
        }

        FileInputStream fs = new FileInputStream(file);

        fs.read(buff);

        String s = new String(buff, StandardCharsets.UTF_8);
        if(!s.trim().equals("P6")){
            Storage.setErrorMessage("Invalid PPM format!");
            return null;
        }

        prevByte = buff[2];

        try {
            columns = readIntsToNextNonNumberCharacter(fs);

            rows = readIntsToNextNonNumberCharacter(fs);

            maxSize = readIntsToNextNonNumberCharacter(fs);
        } catch (Exception e){
            Storage.setErrorMessage("Error while reading sizes");
            return null;
        }

        int readFlag;
        int posX = 0, posY = 0;

        BufferedImage image = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_RGB);

        while ( (readFlag = fs.read(buff)) != -1){

            if(readFlag != 3 && posY == rows)
                break;
            else if(readFlag != 3){
                Storage.setErrorMessage("Not enough bytes to cover entire planned space.");
                return null;
            } else if (posY == rows){
                Storage.setErrorMessage("Too much bytes in file. Ending creating image.");
                break;
            }

            try {
                image.setRGB(
                        posX,
                        posY,
                        ColorUtil.convertColor(
                                Byte.toUnsignedInt(buff[0]),
                                Byte.toUnsignedInt(buff[1]),
                                Byte.toUnsignedInt(buff[2]),
                                maxSize
                        ).getRGB()
                );
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
                break;
            }

            if(posX < columns - 1) posX++;
            else {
                posX = 0;
                posY++;
            }
        }

        return image;
    }

    private static int bytesToInt(byte[] buff) throws Exception {
        try {
            return Integer.parseInt(new String(buff, StandardCharsets.UTF_8).trim());
        } catch (Exception e ){
            throw new Exception();
        }
    }

    private static int readIntsToNextNonNumberCharacter(FileInputStream fs) throws Exception {
        ArrayList<Byte> bytes = new ArrayList<>();

        byte nextByte;
        while((nextByte = (byte) fs.read()) != -1){

            if(prevByte == 10 && nextByte == '#') {
                ignoreLine(fs);
                continue;
            }

            if(nextByte >= 48 && nextByte <= 57 ){
                bytes.add(nextByte);
            } else {
                break;
            }
        }

        byte[] b = new byte[bytes.size()];

        for(int i = 0; i < b.length; i++) {
            b[i] = bytes.get(i);
        }

        return bytesToInt(b) ;
    }

    private static void ignoreLine(FileInputStream fs) throws Exception {
        byte x;
        while( (x = (byte) fs.read()) != 10 ){
            prevByte = x;
        }

        prevByte = x;
    }

}

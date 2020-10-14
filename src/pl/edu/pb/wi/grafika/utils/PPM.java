package pl.edu.pb.wi.grafika.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PPM {

    static ArrayList<Integer> getInts(BufferedReader reader) throws IOException {

        ArrayList<Integer> integers = new ArrayList<>();

        String l;
        while((l = reader.readLine()) != null){
            if(l.charAt(0) == '#') continue;

            String[] line = l.split(" ");

            for (String s: line) {
                integers.add(Integer.parseInt(s));
            }
        }

        return integers;
    }

    static Integer getNext(ArrayList<Integer> buffer){
        if(buffer.size() <= 0) return -1;

        Integer i = buffer.get(0);
        buffer.remove(0);

        return i;
    }

    static int nextInt(BufferedReader reader) throws IOException {
        String l = reader.readLine();
        if(l == null) return -1;
        return l.charAt(0) != '#' ? Integer.parseInt(l) : nextInt(reader);
    }

    static String[] nextStringTable(BufferedReader reader) throws IOException {
        String l = reader.readLine();
        if(l == null) return null;
        return l.charAt(0) != '#' ? l.split(" ") : nextStringTable(reader);
    }

    static String nextIgnoreComments(BufferedReader reader) throws IOException {
        String l = reader.readLine();
        if(l == null) return null;
        return l.charAt(0) != '#' ? l : nextIgnoreComments(reader);
    }

}

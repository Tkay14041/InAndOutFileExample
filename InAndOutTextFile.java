package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InAndOutTextFile {

    public static void main(String args[]) {
        String input = "input.txt";
        String output = "output";
        int size = 3;
        outputFiles(splitList(getLinesfromText(input), size), output);
    }

    public static List<String> getLinesfromText(String path) {
        List<String> list = new ArrayList<>();
        Path source = Paths.get(path);
        try (BufferedReader in = Files.newBufferedReader(source);) {
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void outputFiles(List<List<String>> devidedList, String path) {
        String targetPath = path;
        int number = 1;
        for (List<String> lines : devidedList) {
            Path target = Paths.get(targetPath + number + ".txt");
            try (BufferedWriter out = Files.newBufferedWriter(target, StandardOpenOption.CREATE, StandardOpenOption.APPEND);) {
                for (String line : lines) {
                    out.write(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            number++;
        }
    }

    public static List<List<String>> splitList(List<String> originalList, int size) {
        if (originalList == null || originalList.isEmpty() || size <= 0) {
            return Collections.emptyList();
        }
        int block = originalList.size() / size + (originalList.size() % size);
        List<List<String>> devidedList = new ArrayList<>();
        for (int i = 0; i < block; i++) {
            int start = i * size;
            int end = Math.min(start + size, originalList.size());
            devidedList.add(new ArrayList<>(originalList.subList(start, end)));
        }
        return devidedList;
    }
}

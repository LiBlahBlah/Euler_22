package com.ykyahwa.enler22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Enler22 {


    public String readFile(String fileName) {
        Path path = Paths.get(fileName);
//        System.out.println(path.toAbsolutePath());
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String[] split(String text) {
        return text.split(",");
    }

    public List<String> sortNames(String[] names) {
        List<String> arrNames = Arrays.asList(names);
        Collections.sort(arrNames);
        return arrNames;
    }

    public int calcNames(List<String> listNames) {

        int index = 0;
        int result = 0;
        for (String name: listNames) {
            index++;

            int nameScore = 0;
            for (char c: name.toCharArray()) {
                nameScore +=  getScore(c);
            }

            int calcName = nameScore * index;
//            System.out.println("index = " + index);
//            System.out.println("nameScore = " + nameScore);
//            System.out.println("calcName = " + calcName);
            result += calcName;
//            System.out.println("result = " + result);
        }
        return result;
    }

    private int getScore(char c) {
        return c -'A' +1;
    }

}

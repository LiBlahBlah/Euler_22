package com.ykyahwa.enler22;

import java.util.List;

/**
 * Created by ehlee on 2017. 6. 26..
 */

public class Main {
    public static void main(String[] args) {
        long st = System.currentTimeMillis();

        Enler22 enler22 = new Enler22();
        final String FILE_NAME = "./ykyahwa/names.txt";

        String readText = enler22.readFile(FILE_NAME);
        readText = readText.replace("\"", "");

//        System.out.println(readText);

        String[] names = enler22.split(readText);
        List<String> listNames = enler22.sortNames(names);


        int result = enler22.calcNames(listNames);

        long et = System.currentTimeMillis();
        System.out.println((et - st)+" ms");
        System.out.println("result = " + result);

    }
}

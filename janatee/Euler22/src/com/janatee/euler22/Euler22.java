package com.janatee.euler22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by jaesunlee on 2017. 6. 26..
 */
public class Euler22 {
    public static void main(String[] args)
    {
        // 시작
        long start = System.currentTimeMillis();
        System.out.println("Start");

        // 파일 읽기
        String names = readFile();
        String[] nameList = names.split(",");
        // 정렬
        Arrays.sort(nameList);
        // 결과 계산
        long result = score(nameList);

        // 끝
        long end = System.currentTimeMillis();
        System.out.println("Result = " + result);
        System.out.println("End : " + (end - start)+"ms");
    }

    // 총 점수 구하기
    private static long score(String[] nameList)
    {
        int standard = "A".charAt(0) - 1;
        int nameListSize = nameList.length;
        long result = 0;
        for (int i = 0; i < nameListSize; i++) {
            char[] nameChar = nameList[i].toCharArray();
            int nameCharSize = nameChar.length;
            int sum = 0;
            for (int j = 1; j < nameCharSize-1; j++) {
                sum += (nameChar[j] - standard);
            }
            result += (sum * (i+1));
        }
        return result;
    }

    // 파일 읽기
    private static String readFile()
    {
        StringBuilder result = new StringBuilder();
        File file = new File("names.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }
}

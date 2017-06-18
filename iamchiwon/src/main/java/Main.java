import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by iamchiwon on 2017. 6. 18..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("START");
        System.out.println("--------------------");
        long start = System.currentTimeMillis();

        AtomicInteger counter = new AtomicInteger(1);
        String nameAll = readFile("names.txt");
        String[] names = nameAll.split("\",\"|\"");
        int sum = Arrays.stream(names)
                .filter(name -> !name.isEmpty())
                .sorted()
                .mapToInt(name -> {
                    int score = name.chars().map(ch -> ch - 'A' + 1).sum();
                    int count = counter.getAndIncrement();
                    return score * count;
                })
                .sum();

        long end = System.currentTimeMillis();
        System.out.println("SUM : " + sum);
        System.out.println("--------------------");
        System.out.println("END : " + (end - start) + " ms");
    }

    private static String readFile(String filepath) {
        File f = new File(filepath);
        if (f.exists() == false) return "";

        BufferedInputStream bin = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(f));
            int size = bin.available();
            byte[] data = new byte[size];
            bin.read(data);
            bin.close();
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            if (bin != null) {
                try {
                    bin.close();
                } catch (Exception e2) {
                }
            }
        }
        return "";
    }
}

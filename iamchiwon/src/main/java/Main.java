import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Created by iamchiwon on 2017. 6. 18..
 */
public class Main {
    public static void main(String[] args) throws IOException {

        final String INPUT_FILE = "names.txt";

        //file read function
        final Function<String, String> readFile = filename -> {
            try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
                return br.readLine();
            } catch (IOException e) {
            }
            return "";
        };

        //prepare
        System.out.println("START");
        System.out.println("--------------------");
        final long start = System.currentTimeMillis();

        //parse
        final String[] names = readFile.apply(INPUT_FILE).split("\",\"|\"");

        //calc
        final AtomicInteger indexer = new AtomicInteger();
        final int sum = Arrays.stream(names)
                .filter(name -> !name.isEmpty())
                .sorted()
                .mapToInt(name -> {
                    int score = name.chars().map(ch -> ch - 'A' + 1).sum();
                    int index = indexer.incrementAndGet();
                    return score * index;
                })
                .sum();

        //ending
        final long end = System.currentTimeMillis();
        System.out.println("SUM : " + sum);
        System.out.println("--------------------");
        System.out.println("END : " + (end - start) + " ms");
    }
}

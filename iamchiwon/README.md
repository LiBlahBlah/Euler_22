# Euler_22 
## iamchiwon

### 풀이
- 파일 읽는 부분은 stream을 사용한 function으로 만들었습니다
- 파일 내용은 new line 이 없는 데이터이기 때문에 readLine() 으로만 읽어도 모두 읽을 수 있습니다 
- regex 를 사용해서 split 하였습니다.
- 단어의 점수를 계산하는 부분은 character[] 의 스트림을 사용했습니다

### Source
```java
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
```

### Result
```
START
--------------------
SUM : 871198282
--------------------
END : 52 ms
```
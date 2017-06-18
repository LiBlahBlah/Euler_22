# Euler_22 
## iamchiwon

### 풀이
- 파일에서 먼저 String으로 다 읽어온 후에 사용했습니다.
- regex 를 사용해서 split 하였습니다.
- java 8 의 스트림 기능만을 사용했습니다.

### Source
```java
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
```

### Result
```
START
--------------------
SUM : 871198282
--------------------
END : 80 ms
```
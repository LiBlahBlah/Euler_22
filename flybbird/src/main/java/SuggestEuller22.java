import java.io.*;
import java.util.*;

/**
 * Created by SuyoungKang on 2017. 6. 17..
 */
public class SuggestEuller22 {

    public static void main(String[] args) {
        System.out.println(">>> Resolve Euller Start");
        long startMillis = System.currentTimeMillis();

        SuggestEuller22 euller22 = new SuggestEuller22();

        // 1. Resoures 를 읽어들인다..
        String readData = euller22.fileRead("names.txt");

        // 2. 메모리에 올아왔으면 파싱한다..
        //Map<String, List<String>> parseData = euller22.spliteContents(readData);
        Map<String, List<String>> parseData = euller22.spliteContents2(readData);

        // 3. MAP에 들어온 Key Value 되로 Sort 처리 및 Index를 부여한다.
        int resolveValue = 0;
        int keyIndex = 1;
        
        for ( String key : parseData.keySet()){
            List<String> keyGroupList = parseData.get(key);
            Collections.sort(keyGroupList);

            // SORT AFTER
            for (String sortValue : keyGroupList){
                //int temp = euller22.alphabetContent(sortValue) * keyIndex;
                int temp = euller22.alphabetContent2(sortValue) * keyIndex;
                resolveValue +=temp;
                keyIndex++;
            }
        }

        System.out.println("Value="+resolveValue);

        String message = String.format("** Process Time = %d", System.currentTimeMillis() - startMillis);
        System.out.println(message);
        System.out.println("Resolve Euller End <<<");
    }

    private int alphabetContent(String item) {
        int alphabetTotal = 0;
        for(char itemChar: item.toCharArray() ){
            alphabetTotal += (itemChar - 'A' + 1);
        }

        return alphabetTotal;
    }

    private int alphabetContent2(String item) {
        int alphabetTotal = 0;

        for (int index = 1; index <item.length() -1 ;index++){
            alphabetTotal +=(item.charAt(index) -'A' +1);
        }
        return alphabetTotal;
    }


    private String fileRead(String fileName) {
        BufferedReader br = null;
        String readData = null;
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try {
            br = new BufferedReader(new FileReader(file));
            readData = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br != null) try {br.close(); } catch (IOException e) {}
        }

        return readData;
    }

    private Map<String,List<String>>  spliteContents(String data){
        final String COMMA =",";
        String[] parseArray = data.split(COMMA);
        Map<String,List<String>> dataMap = new HashMap<>();

        for ( String item : parseArray) {
            String removeDDaomPyo = item.substring(1, item.length()-1);
            String key = String.valueOf(removeDDaomPyo.charAt(0));

            List dataList = dataMap.get(key);

            if ( dataList != null ){
                dataList.add(removeDDaomPyo);
                dataMap.put(key,dataList);
            } else {
                List<String> param = new ArrayList<>();
                param.add(removeDDaomPyo);
                dataMap.put(key, param);
            }
        }

        return dataMap;
    }

    private Map<String,List<String>>  spliteContents2(String data){
        final String COMMA =",";
        String[] parseArray = data.split(COMMA);
        Map<String,List<String>> dataMap = new HashMap<>();

        for ( String item : parseArray) {
//            String removeDDaomPyo = item.substring(1, item.length()-1);
//            String key = String.valueOf(removeDDaomPyo.charAt(0));

            String key = String.valueOf(item.charAt(1));
            List dataList = dataMap.get(key);

            if ( dataList != null ){
                dataList.add(item);
                dataMap.put(key,dataList);
            } else {
                List<String> param = new ArrayList<>();
                param.add(item);
                dataMap.put(key, param);
            }
        }

        return dataMap;
    }





}

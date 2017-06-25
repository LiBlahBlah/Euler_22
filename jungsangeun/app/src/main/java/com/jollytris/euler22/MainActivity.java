package com.jollytris.euler22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        euler22();
    }

    private void euler22() {
        long start = System.currentTimeMillis();
        String names = null;
        try {
            names = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        names = names.replace("\"", "");

        String[] ayNames = names.split(",");
        List<String> listNames = Arrays.asList(ayNames);
        listNames.sort((s, t1) -> s.compareToIgnoreCase(t1));

        Observable.range(0, listNames.size())
                .map(index -> nameToNum(listNames.get(index)) * (index+1))
                .reduce((sum1, sum2) -> sum1 + sum2)
                .subscribe(result -> {
                    Log.d("TEST", "result = " + result + " time : " + (System.currentTimeMillis() - start) + "ms");
                });
    }

    private String readFile() throws IOException {
        InputStream is = getAssets().open("names.txt");

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer);
    }

    private int nameToNum(String name) {
        int sum = 0;
        int size = name.length();
        for (int i=0; i<size; i++) {
            sum += name.charAt(i) - 64;
        }
        return sum;
    }
}

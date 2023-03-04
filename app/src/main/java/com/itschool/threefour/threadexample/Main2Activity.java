package com.itschool.threefour.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Button button_1, button_2;
    TextView tw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button_1 = findViewById(R.id.bt_one);
        button_2 = findViewById(R.id.bt_two);
        tw = findViewById(R.id.tw_indicator);

        button_1.setOnClickListener((x) -> {
            // incorrect
//            MyRunnable target = new MyRunnable();
//            Thread th = new Thread(target);
//            th.start();
            // incorrect
//            runOnUiThread(new MyRunnable());
            @SuppressLint("SetTextI18n") Runnable r = () -> {
                for (int i = 0; i < 100; i++) {
                    int temp = i;
                    try {
                        Thread.sleep(100);
                        tw.post(() -> tw.setText("Текущее число " + String.valueOf(temp)));
                        // postDelayed
                        // incorrect
//                        tw.setText("Текущее число " + String.valueOf(x));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };

            new Thread(r).start();

        });

        button_2.setOnClickListener((x) -> {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        });

    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int x = 0; x < 100; x++) {
                try {

                    Thread.sleep(100);
                    setTwText("Текущее число " + String.valueOf(x));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public void setTwText(String text) {
        tw.setText(text);
    }
}

package com.itschool.threefour.threadexample;

import androidx.appcompat.app.AppCompatActivity;

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

        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        tw = findViewById(R.id.tw_indicator);

        button_1.setOnClickListener( (x) -> {
            /*MyRunnable target = new MyRunnable();
            Thread th = new Thread(target);
            th.start();*/
            runOnUiThread(new MyRunnable());
        });

        button_2.setOnClickListener((x) -> {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        });

    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int x = 0; x< 100; x++){
                try{

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

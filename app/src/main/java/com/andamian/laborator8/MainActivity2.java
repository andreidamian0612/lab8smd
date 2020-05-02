package com.andamian.laborator8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import javax.crypto.Mac;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.editText2);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("extra");
        textView.setText(extra);

        byte[] receivedMacData = intent.getByteArrayExtra("hmac");

        try {
            Mac mac = Mac.getInstance("HmacSha256");
            mac.init(MainActivity.cheie);

            byte[] computedMacData = mac.doFinal(extra.getBytes());

            Boolean var = true;

            if (receivedMacData.length == computedMacData.length){
                for(int i=0;i<receivedMacData.length;i++){
                    if (receivedMacData[i] != computedMacData[i]){
                        var = false;
                    }
                }
            }

            if (var) {
                Toast.makeText(MainActivity2.this,"Data is unmodified", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

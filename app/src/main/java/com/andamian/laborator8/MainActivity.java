package com.andamian.laborator8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {
    public static SecretKey cheie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        try {
            cheie = KeyGenerator.getInstance("HmacSha256").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                String someText = editText.getText().toString();

                intent.putExtra("extra", someText);


                try {
                    Mac mac = Mac.getInstance("HmacSha256");
                    mac.init(cheie);

                    byte[] macData = mac.doFinal(someText.getBytes());

                    intent.putExtra("hmac", macData);

                    startActivity(intent);
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

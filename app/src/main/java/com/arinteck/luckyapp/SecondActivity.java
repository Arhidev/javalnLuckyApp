package com.arinteck.luckyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView  welcomeText, luckyNumberText;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        welcomeText = findViewById(R.id.welcomeLuckyId);
        luckyNumberText = findViewById(R.id.luckyResultId);
        btn = findViewById(R.id.shareBtnId);

        //Receiving  the data or Main Activity

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        int rdNum = generateRandomNumber();
        luckyNumberText.setText(""+ rdNum);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, rdNum);
            }
        });


        //Generate Random number
    }

    public  int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;
        return random.nextInt(upper_limit);
    }

    //Implicit Intent
    public  void  shareData(String username, int random){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");


        //Additional Info

        i.putExtra(Intent.EXTRA_SUBJECT,username + " go lucky today! ");
        i.putExtra(Intent.EXTRA_TEXT,username + ", His lucky number is  "+random);


        //create chooser method is a utility provided by the
        //intent class that allows you to create a dialog that
        //displays a list of applications that can handle a specific intent.
        startActivity(Intent.createChooser(i, "Choose your app to share"));
    }
}
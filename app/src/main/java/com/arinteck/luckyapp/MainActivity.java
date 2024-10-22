package com.arinteck.luckyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editText = findViewById(R.id.edit_text_id);
        button = findViewById(R.id.showLuckyBtnId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editText.getText().toString();

                if(userName.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Yous must enter your name", Toast.LENGTH_SHORT).show();
                }else{
                    //Explicit Intent
                    //Intent intent = new Intent(this, SecondActivity.class); // But here we have an error because this is not referred to a context.

                    //So i need to pass a context package, context application context : which returns a context

                    Intent intent = new Intent(
                            getApplicationContext(),
                            SecondActivity.class);

                    //The put extra method is used to include additional data within an intent object.
                    intent.putExtra("name", userName);

                    startActivity(intent);
                }


            }
        });


    }
}
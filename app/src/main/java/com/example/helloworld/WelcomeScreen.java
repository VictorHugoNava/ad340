package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        welcomeText = findViewById(R.id.welcomeText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            if(bundle.containsKey(Constants.USERNAME_KEY)){
                welcomeText.setText(getString(R.string.Thanks) + bundle.getString(Constants.USERNAME_KEY));
            }
        }
    }

    public void onBackClick(View view) {
        finish();
    }
}
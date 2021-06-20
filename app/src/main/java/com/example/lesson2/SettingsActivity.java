package com.example.lesson2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private static final int ThemeLesson2CodeStyle = 0;
    private static final int SwapStyleCodeStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SwapStyle);
        setContentView(R.layout.activity_main);

        //SwapStyleCodeStyle = findViewById(R.id.SWAP);
    }
}
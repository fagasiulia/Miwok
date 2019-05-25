package com.example.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create an array of Strings to store the English words for numbers
        String[] numbersArray = {"one" , "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        // The following code will use the array above to print the words on the screen
        LinearLayout roodtView = (LinearLayout)findViewById(R.id.rootView);

        for(int i = 0; i < 10; i++){
            TextView textView = new TextView(this);
            textView.setText(numbersArray[i]);
            roodtView.addView(textView);
        }

    }
}

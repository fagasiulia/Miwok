package com.example.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        // Create an array of Strings to store the English words for numbers
        ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("red", "weṭeṭṭi", R.drawable.color_red));
        word.add(new Word("green", "chokokki", R.drawable.color_green));
        word.add(new Word("brown", "ṭakaakki", R.drawable.color_brown));
        word.add(new Word("gray", "ṭopoppi", R.drawable.color_gray));
        word.add(new Word("black", "kululli", R.drawable.color_black));
        word.add(new Word("white", "kelelli", R.drawable.color_white));
        word.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));
        word.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(this, word);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_model.xml layout file.

        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(itemsAdapter);

    }
}

package com.example.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        // Create an array of Strings to store the English words for numbers
        ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("father", "әpә"));
        word.add(new Word("mother", "әṭa"));
        word.add(new Word("son", "angsi"));
        word.add(new Word("daughter", "tune"));
        word.add(new Word("older brother", "taachi"));
        word.add(new Word("younger brother", "chalitti"));
        word.add(new Word("older sister", "teṭe"));
        word.add(new Word("younger sister", "kolliti"));
        word.add(new Word("grandmother", "ama"));
        word.add(new Word("grandfather", "paapa"));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, word);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_modelml layout file.

        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(itemsAdapter);

    }
}

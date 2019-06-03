package com.example.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    // Create a MediaPlayer object
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        // Create an array of Strings to store the English words for numbers
        final ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("Where are you going?", "minto wuksus?", R.raw.phrase_where_are_you_going));
        word.add(new Word("What is your name?", "tinnә oyaase'nә?", R.raw.phrase_what_is_your_name));
        word.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        word.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        word.add(new Word("I'm feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        word.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        word.add(new Word("Yes, I'm coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        word.add(new Word("I'm coming.", "әәnәm", R.raw.phrase_im_coming));
        word.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        word.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, word, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_modelml layout file.

        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(itemsAdapter);

        // Associate the word with its right audio file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to play
                // a different sound file
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.get(position).getMediaPlayerID());
                mediaPlayer.start();

                // Get notified when the song came to an end and release the resource
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    @Override
                    public void onCompletion(MediaPlayer arg0){
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    // Clean up the media player by releasing its resources.
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}

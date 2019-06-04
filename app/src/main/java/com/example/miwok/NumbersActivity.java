package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    // Handles playback of all the sound files
    private MediaPlayer mediaPlayer;

    // Handles Audio Focus when playing a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChnageListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        // Pause playback
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0); // Zero means start from the beginning
                        // because our audio files are short it won't make sense to resume
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // Resume playback
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                       // If Audio Focus is lost stop playback and clean up resources
                        releaseMediaPlayer();
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        // Create and setup {@link AudioManager} to request Audio Focus
        mAudioManager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        // Create an array of Strings to store the English words for numbers
        final ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        word.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        word.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        word.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        word.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        word.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        word.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        word.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        word.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        word.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, word, R.color.category_numbers);

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

                // Request Audio Focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChnageListener,
                        // Use the stream type
                        AudioManager.STREAM_MUSIC,
                        // Give duration hint
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // We have Audio Focus now

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.get(position).getMediaPlayerID());

                    mediaPlayer.start();

                    // Get notified when the song came to an end and release the resource
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        @Override
                        public void onCompletion(MediaPlayer arg0){
                            releaseMediaPlayer();
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onStop() {
        // When the activity is stopped release the Media Player resources because
        // we won't need them anymore;
        super.onStop();
        releaseMediaPlayer();

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

            // Abandon Audio Focus when playback is completed
            mAudioManager.abandonAudioFocus(mOnAudioFocusChnageListener);
        }
    }
}

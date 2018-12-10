package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */


public class NumbersFragment extends Fragment {

    ArrayList<Word> words = new ArrayList<>(
            Arrays.asList(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one),
                    new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two),
                    new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three),
                    new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four),
                    new Word("five", "massokka", R.drawable.number_five, R.raw.number_five),
                    new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six),
                    new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven),
                    new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight),
                    new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine),
                    new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten)));

    MediaPlayer mediaPlayer;

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView = rootView.findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("NumbersActivity", "onItemClick: current state of word object " + words.get(position).toString());

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getActivity(),
                        words.get(position).getmSoundSource());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });


        return rootView;
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

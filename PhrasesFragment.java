package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    ArrayList<Word> words = new ArrayList<>(
            Arrays.asList(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going),
                    new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name),
                    new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is),
                    new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling),
                    new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good),
                    new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming),
                    new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming),
                    new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming),
                    new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go),
                    new Word("Come here.", "әnni'nem", R.raw.phrase_come_here)));
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

        ListView listView = rootView.findViewById(R.id.listview);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("PhrasesActivity", "onItemClick: current state of word object " + words.get(position).toString());

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getActivity(),
                        words.get(position).getmSoundSource());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });


        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
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
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}

package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyMembersFragment extends Fragment {

    ArrayList<Word> words = new ArrayList<>(
            Arrays.asList(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father),
                    new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother),
                    new Word("son", "angsi", R.drawable.family_son, R.raw.family_son),
                    new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter),
                    new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother),
                    new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother),
                    new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister),
                    new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister),
                    new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother),
                    new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather)));
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public FamilyMembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */





        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);

        ListView listView = rootView.findViewById(R.id.listview);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("FamilyMembers", "onItemClick: current state of word object " + words.get(position).toString());

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

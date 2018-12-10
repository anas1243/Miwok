package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> objects, int color) {
        super(context, 0, objects);
        mColorResourceId = color;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }
        Word word = getItem(position);

        TextView miowkTranslationTV = listItemView.findViewById(R.id.miwok_word);
        miowkTranslationTV.setText(word.getmMiwokTranslation());


        TextView mDefualtTranslationTV = listItemView.findViewById(R.id.english_word);
        mDefualtTranslationTV.setText(word.getmDefaultTranslation());


        ImageView mImageIV = listItemView.findViewById(R.id.descriptive_image);
        if (word.getmImageSource() == 0)
            mImageIV.setVisibility(View.GONE);
        else {
            mImageIV.setImageResource(word.getmImageSource());
            mImageIV.setVisibility(View.VISIBLE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }


}
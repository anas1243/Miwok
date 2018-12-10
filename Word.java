package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageSource;
    private int mSoundSource;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int mSoundSource) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mSoundSource = mSoundSource;
    }
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageSource, int mSoundSource) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mImageSource = mImageSource;
        this.mSoundSource = mSoundSource;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageSource() {
        return mImageSource;
    }

    public int getmSoundSource() {
        return mSoundSource;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImageSource=" + mImageSource +
                ", mSoundSource=" + mSoundSource +
                '}';
    }
}


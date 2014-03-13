package com.greendog.androidstudio.example.easteregglistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends Activity {

    private ImageView mImageView;
    private TextView mQuote;
    private int mPosition;
    private DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_detail);

        Intent i = getIntent();
        mPosition = i.getIntExtra("position", 0);

        mDataSource = new DataSource();
        mImageView = (ImageView) findViewById(R.id.image);
        mQuote = (TextView) findViewById(R.id.quote);

        mImageView.setImageResource(mDataSource.getmPhotoHdPool().get(mPosition));
        mQuote.setText(getResources().getString(mDataSource.getmQuotePool().get(mPosition)));
    }
}

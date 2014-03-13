package com.greendog.androidstudio.example.easteregglistview;

import java.util.ArrayList;

public class DataSource {
    private ArrayList<Integer> mPhotoPool;
    private ArrayList<Integer> mQuotePool;
    private ArrayList<Integer> mPhotoHdPool;

    public ArrayList<Integer> getmPhotoHdPool() {
        return mPhotoHdPool;
    }

    public ArrayList<Integer> getmPhotoPool() {
        return mPhotoPool;
    }

    public ArrayList<Integer> getmQuotePool() {
        return mQuotePool;
    }

    private void setupPhotoPool() {
        mPhotoPool.add(R.drawable.cupcake);
        mPhotoPool.add(R.drawable.donut);
        mPhotoPool.add(R.drawable.eclair);
        mPhotoPool.add(R.drawable.froyo);
        mPhotoPool.add(R.drawable.gingerbread);
        mPhotoPool.add(R.drawable.honeycomb);
        mPhotoPool.add(R.drawable.icecreamsandwich);
        mPhotoPool.add(R.drawable.jellybean);
        mPhotoPool.add(R.drawable.kitkat);
        mPhotoPool.add(R.drawable.keylimepie);
    }

    private void setupQuotePool() {
        mQuotePool.add(R.string.quote_1);
        mQuotePool.add(R.string.quote_2);
        mQuotePool.add(R.string.quote_3);
        mQuotePool.add(R.string.quote_4);
        mQuotePool.add(R.string.quote_5);
        mQuotePool.add(R.string.quote_6);
        mQuotePool.add(R.string.quote_7);
        mQuotePool.add(R.string.quote_8);
        mQuotePool.add(R.string.quote_9);
        mQuotePool.add(R.string.quote_10);
    }

    private void setupPhotoHDPool() {
        mPhotoHdPool.add(R.drawable.cupcake_ee);
        mPhotoHdPool.add(R.drawable.donut_ee);
        mPhotoHdPool.add(R.drawable.eclair_ee);
        mPhotoHdPool.add(R.drawable.froyo_ee);
        mPhotoHdPool.add(R.drawable.gingerbread);
        mPhotoHdPool.add(R.drawable.honeycomb_ee);
        mPhotoHdPool.add(R.drawable.icecreamsandwich_ee);
        mPhotoHdPool.add(R.drawable.jellybean_ee);
        mPhotoHdPool.add(R.drawable.kitkat_ee);
        mPhotoHdPool.add(R.drawable.keylimepie_ee);
    }

    public int getDataSourceLength() {
        return mPhotoPool.size();
    }

    public DataSource() {
        mPhotoPool = new ArrayList();
        mQuotePool = new ArrayList();
        mPhotoHdPool = new ArrayList();
        setupPhotoPool();
        setupQuotePool();
        setupPhotoHDPool();
    }
}

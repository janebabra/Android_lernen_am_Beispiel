package com.greendog.androidstudio.example.easteregggridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	// Images Array for GridView
	public Integer[] mThumbIds = {
			R.drawable.cupcake, R.drawable.donut,
			R.drawable.eclair, R.drawable.froyo,
			R.drawable.gingerbread, R.drawable.honeycomb,
			R.drawable.icecreamsandwich, R.drawable.jellybean,
			R.drawable.kitkat, R.drawable.keylimepie
	};

    // Images Array for FullImageActivity
    public Integer[] mFullImageIds = {
            R.drawable.cupcake_ee, R.drawable.donut_ee,
            R.drawable.eclair_ee, R.drawable.froyo_ee,
            R.drawable.gingerbread_ee, R.drawable.honeycomb_ee,
            R.drawable.icecreamsandwich_ee, R.drawable.jellybean_ee,
            R.drawable.kitkat_ee, R.drawable.keylimepie_ee
    };


    // Constructor
	public ImageAdapter(Context c){
		mContext = c;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {			
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
        return imageView;
	}

}

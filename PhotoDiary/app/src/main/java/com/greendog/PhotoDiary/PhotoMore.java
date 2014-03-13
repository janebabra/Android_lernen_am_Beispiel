package com.greendog.PhotoDiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class PhotoMore extends Activity{
    /** Called when the activity is first created. */

    private String path=null;
    private String priv_title=null;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photomore);
        setRequestedOrientation(1);
        ArrayList newList=Stuff.getSeeingPic();
        TextView txt_titel= (TextView)findViewById(R.id.title);
        txt_titel.setText(newList.get(0)+"");
        priv_title=newList.get(0)+"";

        ImageView img=(ImageView)findViewById(R.id.picture);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bm = BitmapFactory.decodeFile(newList.get(5)+"", options);
        img.setImageBitmap(bm);


        Animation anim;
        img.setVisibility(ImageView.VISIBLE);
        anim = AnimationUtils.makeInAnimation(this, false);
        img.startAnimation(anim);


        TextView txt_wo= (TextView)findViewById(R.id.wo);
        txt_wo.setText(newList.get(1)+"");
        TextView txt_wer= (TextView)findViewById(R.id.wer);
        txt_wer.setText(newList.get(2)+"");
        TextView txt_was= (TextView)findViewById(R.id.was);
        txt_was.setText(newList.get(3)+"");
        TextView txt_wann= (TextView)findViewById(R.id.wann);
        txt_wann.setText(newList.get(4)+"");


        path=newList.get(6)+"";


    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menu auswerten
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {


            case R.id.opt_delete:
                 AlertDialog.Builder deleteDialog=new AlertDialog.Builder(this);
                deleteDialog.setTitle(priv_title+ " " + (getResources().getString(
                        R.string.loeschen)));
                
                deleteDialog.setPositiveButton(getResources().getString(
                        R.string.Ja),new DialogInterface.OnClickListener() {
                public void onClick (DialogInterface dialog, int buttonID) {
                DatenbankZugriff datenbankZugriff=new DatenbankZugriff(getApplicationContext());
                datenbankZugriff.deletePhoto(path);
                finish();
            }
        });
        deleteDialog.setNegativeButton(getResources().getString(
                R.string.Nein), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonID) {

            }
        });
        deleteDialog.setCancelable(true);
        deleteDialog.show();

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
        //return false;               
    }

    public void show (View view) {
          //Intent fuer Bild Aufruf
            File file = new File(path);
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "image/jpg");
            startActivity(intent);
    }

}

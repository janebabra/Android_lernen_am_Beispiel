package com.greendog.PhotoDiary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class PhotoDetail extends Activity
{


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_new_text);
        setRequestedOrientation(1);

    }

    public void onClickNew(final View view) {
        //new
        String pfad=Stuff.getPath();
       // Stuff.sendToast("@string/Bildgespeichert "+pfad+"",getApplicationContext());
        EditText etxt_titel=(EditText)findViewById(R.id.txt_Titel);
        String titel=etxt_titel.getText().toString();
        EditText etxt_wo=(EditText)findViewById(R.id.txt_wo);
        String wo=etxt_wo.getText().toString();

        EditText etxt_wer=(EditText)findViewById(R.id.txt_wer);
        String wer=etxt_wer.getText().toString();


        EditText etxt_sonstiges=(EditText)findViewById(R.id.txt_was);
        String was=etxt_sonstiges.getText().toString();

        DatenbankZugriff daten = new DatenbankZugriff(getApplicationContext());  //TODO Datenbank anlegen an die richtige stelle. Über daten kommt dann der Zugriff
        Boolean photoSave=daten.insertNewPhoto(pfad,titel,wo,wer,was,null);     //T: Daten speichern
        //Test ob Daten abgefasst werden
        if(photoSave==true) {
                //Stuff.sendToast("@string/Bildgespeichert",getApplicationContext());
        } else {
                 Stuff.sendToast("Fehler",getApplicationContext());
        }
        finish();
     }
}

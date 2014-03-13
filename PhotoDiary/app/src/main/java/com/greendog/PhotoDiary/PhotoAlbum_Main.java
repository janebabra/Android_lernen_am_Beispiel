package com.greendog.PhotoDiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PhotoAlbum_Main extends Activity {
    //Intent Camera
    int TAKE_PICTURE = 2;
    private Uri imageUri;

    private ArrayList<HashMap<String, Object>> myList = null;


    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_view);

        setRequestedOrientation(1);                     //nur Potrait

        Stuff.setAppContext(getApplicationContext());

        //Create Directory
        File sddir = new File("/sdcard/photobook/");
        if (sddir.exists() == false) {        //?vorhanden
            sddir.mkdir();
        }

        File sddir2 = new File("/sdcard/photobook/tmp/");
        if (sddir2.exists() == false && sddir.exists() == true) { //?vorhanden
            sddir2.mkdir();
        }


        // Create Hash Map
        myList = new ArrayList<HashMap<String, Object>>();
        addDataToList();   //Daten aus der Datenbank holen
        createList();       //Daten in der Liste darstellen


    }

    public void onResume() {
        super.onResume();    //neu laden wenn der Bildschrim wieder in Vordergrund kommt
        myList = new ArrayList<HashMap<String, Object>>();
        addDataToList();
        createList();


    }


    private void addDataToList() {
        DatenbankZugriff daten = new DatenbankZugriff(Stuff.getAppContext());
        Cursor cursor = daten.getAllData();       //Cursor bestücken
        myList.clear();
        cursor.moveToFirst();
        int i = 0;
        while (cursor.isAfterLast() == false) {
            //pictureList.add(cursor.getString(1)+"");


            HashMap<String, Object> item = new HashMap<String, Object>();

            //Cache erstellen - Wenn Datei noch nicht im Cache -->thumbnail erstellen
            File file = new File(cursor.getString(1));
            String filename = cursor.getString(1);
            int filenameLength = filename.length();
            String nameFile = "c_" + filename.substring(filenameLength - 19, filenameLength - 4);
            File sdImageMainDirectory = new File("/sdcard/photobook/tmp/");
            File cachedPicture = new File(sdImageMainDirectory.toString() + "/" + nameFile + ".jpg");

            String path_pic = null;
            int quality = 50;
            if (cachedPicture.exists() == true) {
                path_pic = cachedPicture.toString();
            } else {
                FileOutputStream fileOutputStream = null;

                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 10;
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
                    path_pic = sdImageMainDirectory.toString() + "/" + nameFile + ".jpg";
                    fileOutputStream = new FileOutputStream(path_pic);

                    BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);


                    bos.flush();
                    bos.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            //Daten in die HashMap schreiben
            item.put("src", cursor.getString(1));
            item.put("titel", cursor.getString(2));
            item.put("wo", cursor.getString(3));
            item.put("wer", cursor.getString(4));
            item.put("was", cursor.getString(5));
            item.put("wann", cursor.getString(6));
            item.put("pic", path_pic);
            myList.add(item);
            cursor.moveToNext();
            i++;
        }
        cursor.close();


    }

    private void createList() {
        // View
        ListView mainListView = (ListView) findViewById(R.id.main_listview);

        // Adapter
        SimpleAdapter aa = new SimpleAdapter(this, myList, R.layout.row,
                new String[]{"titel", "wann", "wo", "src", "pic"},
                new int[]{R.id.txt_titel, R.id.txt_wann, R.id.txt_wo, R.id.txt_src, R.id.picture});
        mainListView.setAdapter(aa);

        // Listener
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the HashMap of the clicked item
                HashMap<String, Object> user = myList.get(position);

                ArrayList list = new ArrayList();
                list.add((String) user.get("titel"));  //0
                list.add((String) user.get("wo"));     //1
                list.add((String) user.get("wer"));    //2
                list.add((String) user.get("was"));    //3
                list.add((String) user.get("wann"));   //4
                list.add((String) user.get("pic"));    //5
                list.add((String) user.get("src"));    //6

                Stuff.setSeeingPic(list);

                Intent PhotoMoreIntent = new Intent(PhotoAlbum_Main.this, PhotoMore.class);
                startActivity(PhotoMoreIntent);


            }
        });
    }

    /**
     * Menü setzen
     *
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.hauptmenue, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menü auswerten
     *
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.opt_about:
                Stuff.sendDialog("Photo Diary", "Copyright 2014 greendog", PhotoAlbum_Main.this);

                return true;

            case R.id.opt_exit:
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
        //return false;               
    }

    /**
     * Neues Photo
     */
    public void newPhoto(View view) {

        //Datum holen --> Datum und Zeitstempel

        String date = Stuff.getDate();
        String time = Stuff.getTime();

        //Camera Intent aufrufen um Bild zu schießen
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        //Foto anlegen -->Pfad setzen
        File photo = new File(Environment.getExternalStorageDirectory(), "/photobook/ALB_" + date + "_" + time + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));

        //Pfad setzen
        imageUri = Uri.fromFile(photo);

        //Intent Activity starten
        startActivityForResult(intent, TAKE_PICTURE);


    }

    /**
     * Result von Camera
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //Url setzen
            Uri selectedImage = imageUri;
            getContentResolver().notifyChange(selectedImage, null);
            try {
                //alles cool

                Stuff.setPath(selectedImage.getPath());
                Intent PhotoDetailIntent = new Intent(PhotoAlbum_Main.this, PhotoDetail.class);
                startActivity(PhotoDetailIntent);


            } catch (Exception e) {
                //Problem
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

}

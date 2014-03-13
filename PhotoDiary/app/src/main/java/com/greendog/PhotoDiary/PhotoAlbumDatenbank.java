package com.greendog.PhotoDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PhotoAlbumDatenbank extends SQLiteOpenHelper{
    private static final String DATENBANK_NAME = "photoalbum.db";
    private static final int DATENBANKVERSION = 1;

    /**
     *
     * @param context
     */
    public PhotoAlbumDatenbank(Context context){
        super(
                context,
                DATENBANK_NAME,
                null,
                DATENBANKVERSION
        );
    }

    /**
     * Neue Datenbankanlegen
     * @param db  //db kommt aus der Java-Klasse Datenbankzugriff, zum neu Anlegen einer Datenbank
     */
    public void onCreate(SQLiteDatabase db) {
        //Datenbank anlegen
        db.execSQL("CREATE TABLE BILD (" +   //TODO Datenbankschema eintragen
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pfad TEXT NOT NULL,"+
                "titel TEXT," +
                "wo TEXT ,"+
                "wer TEXT ,"+
                "was TEXT ,"+
                "wann TEXT ,"+
                "laengendrad REAL," +       //T: Datentypen wurden ergänzt
                "breitengrad REAL" +
                ");"
        );
    }

    /**
     * Bei neuer Datenbankversion alte Datenbank löschen und neue Anlegen
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //bei Datenbankupdate
        db.execSQL("DROP TABLE BILD");  //alte Datenbank löschen
        onCreate(db);                   //neue Datenbank anlegen nach neuem Muster
    }


}

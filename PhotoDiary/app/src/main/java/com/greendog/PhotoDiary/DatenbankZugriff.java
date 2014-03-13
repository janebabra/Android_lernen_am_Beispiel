package com.greendog.PhotoDiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatenbankZugriff {

    static SQLiteDatabase dbConn=null;     //Globaler Zugriff zur Datenbank in der Klasse
    /**
     * Construktor
     * @param context
     */
    public DatenbankZugriff(Context context) {
        PhotoAlbumDatenbank db = new PhotoAlbumDatenbank(context);
        dbConn = db.getWritableDatabase();         //dbConn ist die Zugriffsvariable zur db
    }

    /**
     *
     * @return Cursor von Datenbankzugriff
     */
    public Cursor getAllData(){
        String SQL="Select * FROM BILD order by _id desc";
        Cursor ergebnis =  dbConn.rawQuery(SQL,null);
    return ergebnis;
    }


    /**
     * Neuen Eintrag in der Datenbank erzeugen
     * @param pfad
     * @param titel
     * @param wo
     * @param wer
     * @param was
     * @param gps
     * @return
     */
   public boolean insertNewPhoto(String pfad, String titel, String wo, String wer, String was, String gps){
       String datum=Stuff.getFormatDateTime();
       String SQL ="INSERT INTO BILD (_id,pfad,titel,wo,wer,was,wann) " +
                   "VALUES (null,'"+pfad+"','"+titel+"','"+wo+"','"+wer+"','"+was+"','"+datum+"')";

       dbConn.execSQL(SQL);

       return true;

    }

    public boolean  deletePhoto(String path) {
        String SQL="Delete from bild where pfad='"+path+"'";
        dbConn.execSQL(SQL);
        return true;
    }
}

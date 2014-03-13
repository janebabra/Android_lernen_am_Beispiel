package com.greendog.PhotoDiary;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Stuff {
    static String pfad;
    static Context applicationcontext;
    static ArrayList list;

    
    public static void sendToast(String ausgabe,Context context) {



                    CharSequence text = ausgabe;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
    }

    

    public static void sendDialog(String title,String ausgabe,Context context) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle(title);
                dialog.setCancelable(true);
                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.TextView01);
                text.setText(ausgabe);
                //now that the dialog is set up, it's time to show it
                dialog.show();
    }

    /**
     * @return Datum als String
     */
    public static String getFormatDateTime() {
        String datum="";
		GregorianCalendar cal = new GregorianCalendar();
        String year=cal.get(Calendar.YEAR)+"";
		String month = (cal.get(GregorianCalendar.MONTH) + 1) + "";
		String day = cal.get(GregorianCalendar.DAY_OF_MONTH) + "";
		if ((cal.get(GregorianCalendar.MONTH) + 1) <= 9)
			month = "0" + (cal.get(GregorianCalendar.MONTH) + 1);

		if (cal.get(GregorianCalendar.DAY_OF_MONTH) <= 9)
			day = "0" + cal.get(GregorianCalendar.DAY_OF_MONTH);
        String hour=cal.get(GregorianCalendar.HOUR_OF_DAY)+"";
        String minute=cal.get(GregorianCalendar.MINUTE)+"";
        String second=cal.get(GregorianCalendar.SECOND)+"";
        if ((cal.get(GregorianCalendar.HOUR_OF_DAY))<=9) {
            hour="0"+cal.get(GregorianCalendar.HOUR_OF_DAY);
        }
        if ((cal.get(GregorianCalendar.MINUTE)<=9)) {
            minute="0"+cal.get(GregorianCalendar.MINUTE);
        }
        if ((cal.get(GregorianCalendar.SECOND))<=9) {
            second="0"+cal.get(GregorianCalendar.SECOND);
        }
        datum=day+"."+month+"."+year+"-"+hour+":"+minute;
        return datum;
    }
    public static String getDate() {
        String datum="";
		GregorianCalendar cal = new GregorianCalendar();
        String year=cal.get(Calendar.YEAR)+"";
		String month = (cal.get(GregorianCalendar.MONTH) + 1) + "";
		String day = cal.get(GregorianCalendar.DAY_OF_MONTH) + "";
		if ((cal.get(GregorianCalendar.MONTH) + 1) <= 9)
			month = "0" + (cal.get(GregorianCalendar.MONTH) + 1);

		if (cal.get(GregorianCalendar.DAY_OF_MONTH) <= 9)
			day = "0" + cal.get(GregorianCalendar.DAY_OF_MONTH);

        datum=year+""+month+""+day;
        return datum;
    }

    /**
     * @return  Zeitstempel als String
     */
    public static String getTime() {
        String zeit="";
        GregorianCalendar cal = new GregorianCalendar();
        String hour=cal.get(GregorianCalendar.HOUR_OF_DAY)+"";
        String minute=cal.get(GregorianCalendar.MINUTE)+"";
        String second=cal.get(GregorianCalendar.SECOND)+"";
        if ((cal.get(GregorianCalendar.HOUR_OF_DAY))<=9) {
            hour="0"+cal.get(GregorianCalendar.HOUR_OF_DAY);
        }
        if ((cal.get(GregorianCalendar.MINUTE)<=9)) {
            minute="0"+cal.get(GregorianCalendar.MINUTE);
        }
        if ((cal.get(GregorianCalendar.SECOND))<=9) {
            second="0"+cal.get(GregorianCalendar.SECOND);
        }
        zeit=hour+""+minute+""+second;
        return zeit;
    }

    /**
     * bildpfad global setzen
     * @param path
     */
    public static void setPath(String path) {
        pfad=path;
    }

    /**
     * globalen bildpfad holen
     * @return Bildpfad
     */
    public static String getPath() {
        return pfad;
    }

    /**
     * appcontext setzen
     * @param context
     */
     public static void setAppContext(Context context) {
        applicationcontext=context;
    }

    /**
     * appcontext holen
     * @return
     */
    public static Context getAppContext() {
        return applicationcontext;
    }

    public static void setSeeingPic(ArrayList liste) {
        list=liste;
    }

    public static ArrayList getSeeingPic() {
        return list;
    }


}

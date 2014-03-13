package com.greendog.example.webapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Baskerville extends ActionBarActivity {

    MediaPlayer backgroundmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Fehlersuche", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        Log.e("Fehlersuche", "onResume");
        backgroundmusic = MediaPlayer.create(this, R.raw.backgroundmusic);
        backgroundmusic.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("Fehlersuche", "onPause");
        backgroundmusic.stop();
        backgroundmusic.release();
        super.onPause();
    }

    // open button: Wiki
    public void openWiki(View v) {

        String url = "http://de.wikipedia.org/wiki/Der_Hund_von_Baskerville";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



package com.greendog.example.postcardapp;

import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnLoadImage1;
    TextView textSource1;
    EditText editTextCaption;
    Button btnProcessing;
    ImageView imageResult;

    final int RQS_IMAGE1 = 1;

    Uri source1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadImage1 = (Button)findViewById(R.id.loadimage1);
        textSource1 = (TextView)findViewById(R.id.sourceuri1);
        editTextCaption = (EditText)findViewById(R.id.caption);
        btnProcessing = (Button)findViewById(R.id.processing);
        imageResult = (ImageView)findViewById(R.id.result);

        btnLoadImage1.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_IMAGE1);
            }});

        btnProcessing.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                if(source1 != null){
                    Bitmap processedBitmap = ProcessingBitmap();
                    if(processedBitmap != null){
                        imageResult.setImageBitmap(processedBitmap);
                        Toast.makeText(getApplicationContext(),
                                "Done",
                                Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Something wrong in processing!",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Select both image!",
                            Toast.LENGTH_LONG).show();
                }

            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case RQS_IMAGE1:
                    source1 = data.getData();
                    textSource1.setText(source1.toString());
                    break;
            }
        }
    }

    private Bitmap ProcessingBitmap(){
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        try {
            bm1 = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            Config config = bm1.getConfig();
            if(config == null){
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight() * 2, config);
            Canvas newCanvas = new Canvas(newBitmap);

            newCanvas.drawBitmap(bm1, 0, bm1.getHeight(), null);

            String captionString = editTextCaption.getText().toString();
            if(captionString != null){

                Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintText.setColor(Color.BLUE);
                paintText.setTextSize(50);
                paintText.setStyle(Style.FILL);
                paintText.setShadowLayer(10f, 10f, 10f, Color.BLACK);

                Rect rectText = new Rect();
                paintText.getTextBounds(captionString, 0, captionString.length(), rectText);

                newCanvas.drawText(captionString,
                        0, rectText.height(), paintText);

                Toast.makeText(getApplicationContext(),
                        "drawText: " + captionString,
                        Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

}
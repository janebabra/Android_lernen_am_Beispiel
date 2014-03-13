package com.greendog.example.mysurveyapplication.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MainActivity extends Activity implements TextWatcher {

    private static final String TAG = "MainActivity";

    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Look for these views after we've created them !

        mName = (EditText) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.phone);
        mEmail = (EditText) findViewById(R.id.email);
        mComment = (EditText) findViewById(R.id.comments);

        // We can add this activity as a TextWatcher pointer because
        // this class "implements TextWatcher" (see above)
        // Alternatively,
        // TextWatcher watcher = new TextWatcher() { ... }
        // mComment.addTextChangedListener(watcher);

        mComment.addTextChangedListener(this);
    }

    /*
     * Another TextWatcher method. We need to write this because our Activity
     * promised! We said it "implements TextWatcher"
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // This method is a "NO-OP" (short for no-operation)
        // It does nothing!
    }

    /*
     * Another TextWatcher method. We need to write this because our Activity
     * promised! We said it "implements TextWatcher" above
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    /* Another TextWatcher method. We actually use this one! */
    @Override
    public void afterTextChanged(Editable s) {
        // 's' points to the user's input that has changed
        // We used mComment.addTextChangedListener(this);
        // so 's' will be the comment field's text

        // FYI We could also use mComment.getText().toString()
        // to get the comment field text
        String comments = s.toString();

        // getString returns a Java String pointer to a resource string
        String duck = getString(R.string.duck);

        // You can add two Strings together
        Log.d(TAG, "Checking for " + duck);

		/*
		 * Advanced notes: Some String methods optionally take a locale.
		 * toLowerCase(Locale.US) toLowerCase(Locale.getDefault()) are useful
		 *
		 * The first is used when machine readability is required e.g.
		 * Comparing host names such as 'www.google.com'
		 *
		 * The second - which is equivalent to toLowerCase() - is used when
		 * respecting the language-specific upper-lower case conversions and
		 * used to present Strings to the user. Be careful when comparing strings -
		 * s.toLowerCase().equals("hi") will not work when the default locale is
		 * Turkish because the lowercase of I is a 'dotless i': ı Also note the
		 * length of the string may change during conversion.
		 */

        // See above for an Internationalization discussion on toLowerCase.
        boolean valid = comments.length() > 0
                && comments.toLowerCase().indexOf(duck) == -1;

        View view = findViewById(R.id.imageButton1);

        // Store true or false in the 'isVisible' boolean variable
        // true = duck is visible
        boolean isVisible = view.getVisibility() == View.VISIBLE;
        if (isVisible == valid) {
            // No animation required if both values are true or both values are
            // false
            return;
        }
        // Declare the variable (the pointer!) The next line does NOT create a
        // new animation object - all we have is pointer so far.
        Animation anim;

        if (valid) {
            view.setVisibility(View.VISIBLE);
            // Create a new animation object
            anim = AnimationUtils.makeInAnimation(this, true);
        } else {
            // Create a new animation object
            anim = AnimationUtils.makeOutAnimation(this, true);
            view.setVisibility(View.INVISIBLE);
        }
        // Tell the view it's time to start animating
        view.startAnimation(anim);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void processForm(View duck) {
        // Uncomment - remove the // - from one of these methods!
        // And comment out - add // to the other two
        simpleShareExample();
        // sendSMS();
        // sendEmail();
    }

    public void simpleShareExample() {
        // The simplest code to share a message....
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "What a wonderful app!");
        startActivity(i);
        // But on real devices you will see many many matching options
        // including Bluetooth, Google drive...
        // And to be more robust you should catch ActivityNotFoundException...
    }

    /*public void sendSMS() {
        String comments = mComment.getText().toString();
        String phone = mPhone.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.fromParts("sms", phone, null));
        intent.putExtra("sms_body", comments);

        try {
            startActivity(intent);
            // startActivity can throw ActivityNotFoundException
            // So to be robust our app will catch the exception ..
        } catch (Exception ex) {
            // We could tell the user it didn't work e.g. with a Toast
            // Also we can print the exception message and stack trace in the
            // log...
            Log.e(TAG, "Could not send message", ex);
        }
    }*/

    /*public void sendEmail() {

        String comments = mComment.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();

        String message = name + " says.. \n" + comments;
        if (phone.length() > 0) {
            message = message + "\nPhone:" + phone;
        }

        if (email.length() > 0) {
            message = message + "\nAlternative Email:" + email;
        }

        // FYI There's lots of discussion about email intents on StackOverflow
        // eg SEND vs SENDTO and setting the mimetype to message/rfc822
        // Experimentally the following works on many devices
        // - Tested on Android 1.6 and 4.x phones, and tablets and 2.x,4.x
        // emulator.
        // You will need to configure the emulator's email client with a
        // real email address.

        // To test unsupported schemes change "mailto" to "horseback"
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.fromParts("mailto",
                "feedback@myapp.somewhere...", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "important news");

        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        // Better .... use resolveActivity
        // We can check to see if there is a configured email client
        // BEFORE trying to start an activity
        // Using this test we could have prevented the user from ever opening
        // the survey...
        if (emailIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(),
                    "Please configure your email client!", Toast.LENGTH_LONG)
                    .show();
        } else {
            // Secondly, use a chooser will gracefully handle 0,1,2+ matching
            // activities
            startActivity(Intent.createChooser(emailIntent,
                    "Please choose your email app!"));
        }

    }*/

   /* public void processFormOriginal(View duck) {

        String comments = mComment.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();

        // Notice '=' means assign to the variable on the left
        // to the value of the right hand side

        int position = email.indexOf("@");

        // Notice '==' means see if these integers are the same
        if (position == -1) {
            // Alternatively... if( ! email.contains("@") )

            Toast.makeText(this.getApplicationContext(),
                    "Invalid email address!", Toast.LENGTH_LONG).show();
            mEmail.requestFocus();
            return;
        }

        // You can ask a string for its length (number of characters)
        int len = comments.length();

        // To see if two integer values are equal use ==
        // But don't use == for Strings (see below)!
        if (len == 0) {
            Toast.makeText(this.getApplicationContext(), "Give me comments!",
                    Toast.LENGTH_LONG).show();
            mComment.requestFocus();
            return;
        }
        // To see if two String objects are equal use the "equals" method
        // For String pointers, '==' compares two memory pointers to see if they
        // point to the same object
        //
        // if (name == "Fred") Not OK! (because name can point to a different
        // String object, that might also contain F-r-e-d)
        //
        // if( name == null) is OK for this special case of comparing with null
        //
        if (name.equals("Fred")) {
            Toast.makeText(this.getApplicationContext(), "Hi Fred!",
                    Toast.LENGTH_LONG).show();
        }

        // We might run into some surprises when we try to convert
        // a string to an integer value
        // Convert a string (a sequence of characters) "123123123" into an
        // integer value

        // Notice we declare these variables OUTSIDE the try-catch block
        // So we can use them after the catch block ends
        int value = -1; // we will change this
        boolean valueOK = false; // will become true if everything works out OK

        try {
            // The next line will throw an exception if it does not like our
            // string!
            // e.g. if phone is an empty string "3000000000" or "askdgahksdg"
            value = Integer.parseInt(phone);

            // We will skip this code if parseInt throws its
            // NumberFormatException
            // If everything goes to plan though we will just continue here...

            valueOK = true; // Change AFTER parseInt has returned
            Log.d(TAG, "Phone number:" + value);

        } catch (Exception e) {
            // Uh oh... We caught that nasty exception!!
            // (FYI More experienced programmers might choose to catch
            // NumberFormatException)

            Log.d(TAG,
                    "Invalid Phone Number!? Could not be turned into an Java integer value"
                            + phone);
        }

        if (valueOK) {
            Log.d(TAG, "Phone number as an integer value:" + value);
        }

        String username = email.substring(0, position);
        String thankyou = "Thank you " + username + "!";

        Toast.makeText(this.getApplicationContext(), thankyou,
                Toast.LENGTH_LONG).show();

        // Move the duck to the right and fade it out
        Animation anim = AnimationUtils.makeOutAnimation(this, true);
        duck.startAnimation(anim);
        //
        // duck.setVisibility(View.INVISIBLE);
        // Toast.makeText(this.getApplicationContext(), R.string.app_name,
        // Toast.LENGTH_LONG).show();
    }*/

}


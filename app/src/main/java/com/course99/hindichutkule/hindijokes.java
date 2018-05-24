package com.course99.hindichutkule;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;


public class hindijokes extends AppCompatActivity {

    public TextView textdetail;
    InterstitialAd interstitial;
    AdView mAdView;
    ScrollView scrollview;
    Toolbar toolbar;
    String[] numbers;
    String[] details;
    int p = 0;
    private static int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.funny_jokes);
        AppRate.with(this)
                .setInstallDays(1) // default 10, 0 means install day.
                .setLaunchTimes(2) // default 10
                .setRemindInterval(5) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(hindijokes.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);


        //advertisement
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        toolbar = (Toolbar) findViewById(R.id.toolbarid);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);




        // Get the listview item click position
        //Get the numbers array from Mainactivity


        Intent i = getIntent();
        numbers = i.getStringArrayExtra("mylist");
        position = i.getExtras().getInt("position");
        details = numbers;



        // Locate the TextViews in singleitemview.xml
        // Load the text into the TextViews followed by the position

        textdetail = (TextView) findViewById(R.id.textviewdetail);
        textdetail.setText(details[position]);


//        Typeface tf = Typeface.createFromAsset(getAssets(), "goodfoot.ttf");
//        textdetail.setTypeface(tf);

        ImageButton previousbutton = (ImageButton) findViewById(R.id.nexbutton);
        ImageButton nextbutton = (ImageButton) findViewById(R.id.previousbutton);
        ImageButton sharebutton = (ImageButton) findViewById(R.id.sharebutton);

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textdetail = (TextView) findViewById(R.id.textviewdetail);
                String string = textdetail.getText().toString();

                String string1 = "\n"+ "READ HINDI JOKES OR CHUTKULE,HUSBAND WIFE JOKES ,SANTA BANTA JOKES,TEACHER STUDENT JOKES,GIRLFRIEND BOYFRIEND JOKES,FUNNY JOKES,FUNNY SMS...INSTALL NOW" + " https://play.google.com/store/apps/details?id=com.course99.hindichutkule";
                string = string.concat(string1);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, string);
                startActivity(Intent.createChooser(intent, "Share via"));


            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = position + 1;
                if (position < numbers.length) {


                    textdetail.setText(details[position]);

                    scrollview = (ScrollView) findViewById(R.id.scrollViewscrollView);
                    scrollview.scrollTo(0, 0);
                    if (position % 4 == 0) {

                        interstitial = new InterstitialAd(hindijokes.this);
                        // Insert the Ad Unit ID
                        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
                        AdRequest adRequest = new AdRequest.Builder().build();
                        interstitial.loadAd(adRequest);
                        // Prepare an Interstitial Ad Listener
                        interstitial.setAdListener(new AdListener() {
                            public void onAdLoaded() {
                                // Call displayInterstitial() function

                                displayInterstitial();
                            }
                        });
                    }
                } else if (position == numbers.length) {
                    Toast.makeText(getApplicationContext(), "This is Last Page! Please move to Previous Pages", Toast.LENGTH_SHORT).show();

                    position = position - 1;
                }

            }
        });
        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = position - 1;
                if (position >= 0) {



                    textdetail.setText(details[position]);

                    scrollview = (ScrollView) findViewById(R.id.scrollViewscrollView);
                    scrollview.scrollTo(0, 0);
                    if (position % 4 == 0) {
                        interstitial = new InterstitialAd(hindijokes.this);
// Insert the Ad Unit ID
                        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
                        AdRequest adRequest = new AdRequest.Builder().build();
                        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
                        interstitial.setAdListener(new AdListener() {
                            public void onAdLoaded() {
                                // Call displayInterstitial() function

                                displayInterstitial();
                            }
                        });
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "This is First Page Page! Please move to Next  Pages", Toast.LENGTH_SHORT).show();
                    position = position + 1;

                }
            }
        });


    }

//    public void onBackPressed() {
//        hindijokes.this.finish();
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//// Prepare the Interstitial Ad
//        interstitial = new InterstitialAd(hindijokes.this);
//// Insert the Ad Unit ID
//        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
//
//        interstitial.loadAd(adRequest);
//// Prepare an Interstitial Ad Listener
//        interstitial.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                // Call displayInterstitial() function
//
//                displayInterstitial();
//            }
//        });
//    }

    public void displayInterstitial() {

// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbarmenu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.fontmenu:
                AlertDialog.Builder builder = new AlertDialog.Builder(hindijokes.this, R.style.Theme_AppCompat_DayNight_DarkActionBar);
                builder.setTitle("Choose Font Size:")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(true);

                final SeekBar seek = new SeekBar(hindijokes.this);

                seek.setMax(100);
                seek.setProgress(27);
                seek.setKeyProgressIncrement(1);
                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b)

                    {


                        TextView textView = (TextView) findViewById(R.id.textviewdetail);
                        textView.setTextSize(i);
                    }


                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }
                });
                builder.setView(seek);


                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        TextView textView = (TextView) findViewById(R.id.textviewdetail);
                        textView.setTextSize(27);
                    }
                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alertDialog = builder.create();
                WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();

                wmlp.gravity = Gravity.TOP | Gravity.END;
                wmlp.x = 50;   //x position
                wmlp.y = 50;   //y position


                alertDialog.show();


                break;

            case R.id.sharemenu:


                textdetail = (TextView) findViewById(R.id.textviewdetail);
                String string = textdetail.getText().toString();



                String string1 = "\n" + "READ HINDI JOKES OR CHUTKULE,HUSBAND WIFE JOKES ,SANTA BANTA JOKES,TEACHER STUDENT JOKES,GIRLFRIEND BOYFRIEND JOKES,FUNNY JOKES,FUNNY SMS..." + " https://play.google.com/store/apps/details?id=com.course99.hindichutkule";
                string = string.concat(string1);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, string);
                startActivity(Intent.createChooser(intent, "Share via"));

                break;


            case R.id.rateus:
                try {
                    String shareBody = "market://details?id=com.course99.hindichutkule";
                    Uri uri = Uri.parse(shareBody);
                    Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(shareintent);
                } catch (ActivityNotFoundException e) {
                    String shareBody = " https://play.google.com/store/apps/details?id=com.course99.hindichutkule";
                    Uri uri = Uri.parse(shareBody);
                    Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(shareintent);

                }


                break;


            case R.id.moreapps:

                try {
                    String shareBody = "market://search?id=rajeev+kumar+sharma";
                    Uri uri = Uri.parse(shareBody);
                    Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(shareintent);
                } catch (ActivityNotFoundException e) {
                    String shareBody = "https://play.google.com/store/apps/developer?id=rajeev+kumar+sharma";
                    Uri uri = Uri.parse(shareBody);
                    Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(shareintent);

                }

                break;


            case R.id.aboutus:
                Intent start = new Intent(hindijokes.this, funnyjokes.class);
                startActivity(start);


                break;


        }
        return super.onOptionsItemSelected(item);
    }
}




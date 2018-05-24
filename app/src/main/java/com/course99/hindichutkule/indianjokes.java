package com.course99.hindichutkule;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class indianjokes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hindi_jokes_2018);}




    public  void firstButtonClick(View view)

    {

        String[]numbers=getResources().getStringArray(R.array.jokesinhindi);
        Intent i = new Intent(indianjokes.this, hindijokes.class);

        // Pass all data rank
        i.putExtra("mylist", numbers);
        // Pass listview item click position

        startActivity(i);




    }

    public  void thirdButtonClick(View view)

    {
        String string1 = "\n"+ "READ HINDI JOKES OR CHUTKULE,HUSBAND WIFE JOKES ,SANTA BANTA JOKES,TEACHER STUDENT JOKES,GIRLFRIEND BOYFRIEND JOKES,FUNNY JOKES,FUNNY SMS... INSTALL IT FOR FREE" + " https://play.google.com/store/apps/details?id=com.course99.hindichutkule";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, string1);
        startActivity(Intent.createChooser(intent, "Share via"));


    }

    public void rateUsbutton(View view)
    {

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


    }

    public  void moreAppsButton(View view )
    {

        try {
            String shareBody = "market://search?q=rajeev+kumar+sharma";
            Uri uri = Uri.parse(shareBody);
            Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(shareintent);
        } catch (ActivityNotFoundException e) {
            String shareBody = "https://play.google.com/store/apps/developer?id=rajeev+kumar+sharma";
            Uri uri = Uri.parse(shareBody);
            Intent shareintent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(shareintent);

        }
    }

}

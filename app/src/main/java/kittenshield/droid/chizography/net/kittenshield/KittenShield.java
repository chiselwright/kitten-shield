package kittenshield.droid.chizography.net.kittenshield;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class KittenShield extends AppCompatActivity {
    private static String logTag = "KITTENSHIELD";
    private static Uri intentData;

    private int       imageWidth    = 1024;
    private int       imageHeight   = 768;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(logTag, "onCreate()");
        setContentView(R.layout.activity_kitten_shield);

        // Get the intent that started this activity, if any
        Intent intent = getIntent();
        intentData = intent.getData();

        if (intentData != null) {
            Log.d(logTag, intentData.toString());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(logTag, "onStart()");

        setKittenImage();
        setIncomingURL();
    }


    @Override
    protected void onResume() {
        super.onStart();
        Log.d(logTag, "onResume()");
    }

    private void setKittenImage() {
        setImageDimensions();

        String imageURL         = String.format(
                Locale.getDefault(),
                "http://lorempixel.com/%d/%d/cats/",
                imageWidth,
                imageHeight
        );

        ImageView kittenView = (ImageView) findViewById(R.id.kittenView);

        kittenView.setMaxHeight(imageHeight);
        kittenView.setMaxWidth(imageWidth);

        kittenView.setMinimumHeight(imageHeight);
        kittenView.setMinimumWidth(imageWidth);

        //Picasso.with(this.getApplicationContext()).load("http://i.imgur.com/DvpvklR.png").into(kittenView);
        Log.d(logTag, imageURL);
        Picasso.with(this.getApplicationContext()).load(imageURL).into(kittenView);
    }

    private void setIncomingURL() {
        Log.d(logTag, "setIncomingURL()");
        TextView incomingURL = (TextView) findViewById(R.id.incomingURL);
        TextView urlLabel    = (TextView) findViewById(R.id.urlLabel);
        if (intentData == null) {
            Log.d(logTag, "intentData == null");
            incomingURL.setVisibility(View.INVISIBLE);
            urlLabel.setVisibility(View.INVISIBLE);
            return;
        }

        Log.d(logTag, "intentData != null");
        urlLabel.setVisibility(View.VISIBLE);
        incomingURL.setText(intentData.toString());
        incomingURL.setVisibility(View.VISIBLE);
    }

    // http://paulbourke.net/dataformats/dimensions/
    private void setImageDimensions() {
        DisplayMetrics metrics  = getScreenMetrics();

        if (metrics.widthPixels >= 1365) {
            imageWidth  = 1365;
            imageHeight = 1024;
        }
        else if (metrics.widthPixels >= 1024) {
            imageWidth  = 1024;
            imageHeight =  768;
        }
        else if (metrics.widthPixels >= 960) {
            imageWidth  = 960;
            imageHeight = 720;
        }
        else if (metrics.widthPixels >= 800) {
            imageWidth  = 800;
            imageHeight = 600;
        }
        else {
            imageWidth  = 100;
            imageHeight = 100;
        }
    }

    private DisplayMetrics getScreenMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        } else {
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        return metrics;
    }

}

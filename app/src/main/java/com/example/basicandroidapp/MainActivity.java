package com.example.basicandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnLearnViews;
    private Button btnWelcomePushNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Log.d("BASE_URL",BuildConfig.BASE_URL);
        setListeners();
    }

    /**
     * Initializing the Views to the Appropriate Objects
     */
    private void initViews() {
        btnLearnViews = findViewById(R.id.btn_learn_views);
        btnWelcomePushNotification = findViewById(R.id.btn_getwelcome_pushnotification);
    }

    /**
     * Setting the Listeners for Buttons.
     */
    private void setListeners() {
        /*
         * Setting the Action for Learn Views Button
         * This Action will be same as IBAction in iOS
         * But in Android this can be achieved in different ways
         */
        btnLearnViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivityIntent = new Intent(MainActivity.this, NextActivity.class);
                startActivity(nextActivityIntent);
            }
        });

        /*
         * Setting the onClick Action for Triggering the Local Push Notification
         */
        btnWelcomePushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CHANNEL_ID = "BasicAndroidAPP_01";
                String textTitle = "Notification";
                String textContent = "Welcome to Android!. Rock yourself on both Android and iOS";
                NotificationCompat.Builder builder = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(textTitle)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(textContent))
                            .setContentText(textContent)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                }
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                // notificationId is a unique int for each notification that you must define
                if (builder != null) {
                    notificationManager.notify(1, builder.build());
                }
            }
        });
    }
}

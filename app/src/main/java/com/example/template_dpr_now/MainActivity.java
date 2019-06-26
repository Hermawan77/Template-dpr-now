package com.example.template_dpr_now;


import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class MainActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";
    private static final String CHANNEL_ID = ".notificationDemo.channelId";
    Button crudbtn, homebtn, send_notif;

    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        crudbtn = findViewById(R.id.crudbtn);
        homebtn = findViewById(R.id.homebtn);
        send_notif = findViewById(R.id.send_notif);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MulaiActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        crudbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Input.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        send_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                Notification.Builder builder = new Notification.Builder(MainActivity.this);

                    Notification notification = builder.setContentTitle("Notif Dummy")
                            .setContentText("Notif Baru")
                            .setTicker("Pesan baru")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pendingIntent).build();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder.setChannelId(CHANNEL_ID);
                }

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            CHANNEL_ID,
                            "NotificationDemo",
                            IMPORTANCE_DEFAULT
                    );
                    notificationManager.createNotificationChannel(channel);
                }

                notificationManager.notify(0, notification);

                /*NotificationCompat.Builder builder;
                builder = new NotificationCompat.Builder(MainActivity.this);

                builder.setSmallIcon(R.drawable.notification_icon);
                builder.setContentIntent(pendingIntent);

                builder.setAutoCancel(true);

                //builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_icon));

                builder.setContentTitle("BasicNotifications Sample");
                builder.setContentText("Time to learn about notifications!");
                builder.setSubText("Tap to view documentation about notifications.");

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
                */
            }
        });
    }

}

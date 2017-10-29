package com.example.tomasz.playground;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by uczen on 2017-10-29.
 */

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationCompat.Builder builder =
                new android.support.v7.app.NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentTitle(remoteMessage.getNotification().getBody())
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

    }
}

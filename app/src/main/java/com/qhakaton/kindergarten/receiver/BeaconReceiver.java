package com.qhakaton.kindergarten.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.sensorberg.sdk.action.Action;
import com.sensorberg.sdk.action.InAppAction;
import com.sensorberg.sdk.action.UriMessageAction;
import com.sensorberg.sdk.action.VisitWebsiteAction;

/**
 * Created by Maciej Kozłowski on 20.02.16.
 */
public class BeaconReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Action action = intent.getExtras().getParcelable(Action.INTENT_KEY);
        if (action == null) {
            return;
        }

        switch (action.getType()) {
            case MESSAGE_URI:
                UriMessageAction uriMessageAction = (UriMessageAction) action;
                showNotification(context, action.getUuid().hashCode(), uriMessageAction.getTitle(), uriMessageAction.getContent(), Uri.parse(uriMessageAction.getUri()));
                break;
            case MESSAGE_WEBSITE:
                VisitWebsiteAction visitWebsiteAction = (VisitWebsiteAction) action;
                showNotification(context, action.getUuid().hashCode(), visitWebsiteAction.getSubject(), visitWebsiteAction.getBody(), visitWebsiteAction.getUri());
                break;
            case MESSAGE_IN_APP:
                InAppAction inAppAction = (InAppAction) action;
                showNotification(context, action.getUuid().hashCode(), inAppAction.getSubject(), inAppAction.getBody(), inAppAction.getUri());
                break;
        }
    }

    private void showNotification(Context context, int id, String title, String content, Uri uri) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + content + "\n" + uri.toString());
        sendIntent.setType("text/plain");

        Notification notification = new NotificationCompat.Builder(context)
                .setContentIntent(PendingIntent.getActivity(
                        context,
                        0,
                        new Intent(Intent.ACTION_VIEW, uri),
                        PendingIntent.FLAG_UPDATE_CURRENT))
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setShowWhen(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }
}

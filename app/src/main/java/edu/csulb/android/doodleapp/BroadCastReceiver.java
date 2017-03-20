package edu.csulb.android.doodleapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;



public class BroadCastReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            callPush();
        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
        }
    }

    private void callPush()
    {
        try{
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder b = new NotificationCompat.Builder(context);
            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("DoodleApp")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Doodle Awaiting for You!")
                    .setContentText("Doodle for fun !!!")
                    .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent)
                    .setContentInfo("Info");


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, b.build());
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }
    }
}

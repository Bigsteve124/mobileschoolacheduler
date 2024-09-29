package android.reserver.c196project.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.reserver.c196project.R;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    String channel_id="c196";
    //static int notificationID;
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        int notificationID = prefs.getInt("notificationID", 0);
        createNotificationChannel(context,channel_id);
        Notification notification=new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("text"))
                .setContentTitle(intent.getStringExtra("title")).build();
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,notification);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("notificationID", notificationID);
        editor.apply();

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID){
        CharSequence name= "mychanname";
        String description="channeldescription";
        int importance=NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,importance);
        channel.setDescription(description);
        NotificationManager notificationManager=context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
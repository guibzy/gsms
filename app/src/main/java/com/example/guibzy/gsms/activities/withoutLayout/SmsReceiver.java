package com.example.guibzy.gsms.activities.withoutLayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;

import com.example.guibzy.gsms.R;

/**
 * Created by guibzy on 12/12/16.
 */

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Ensure that got the sms
        if(intent.getExtras() != null) {

            // Get the data of the sms
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            SmsMessage sms = msgs[0];

            // Create a notification
            NotificationCompat.Builder notif_builder = new NotificationCompat.Builder(context);

            notif_builder.setContentTitle("Nouveau message");
            if(sms.getOriginatingAddress() != null)notif_builder.setContentText(sms.getOriginatingAddress());
            else notif_builder.setContentText("Inconnu");
            notif_builder.setSmallIcon(R.drawable.ic_chat_black_24dp);

            Notification notification = notif_builder.build();



        }
    }
}

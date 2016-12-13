package com.example.guibzy.gsms.activities.withLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guibzy.gsms.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final String myPackageName = getPackageName();
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            // App is not default
            // create alertDialog to communicate to user
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Demande de confirmation");
            builder.setMessage("Voulez vous définir gsms comme application par défaut ?");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Intent to change default sms app
                    Intent intent =
                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                            myPackageName);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Quit the current app
                    finish();
                }
            });

            // Show the alert dialog
            builder.show();
        } else {
            // App is the default.
            // Hide the "not currently set as the default SMS app" interface
            Toast.makeText(this, "Super cette application est par défault", Toast.LENGTH_LONG);
        }
    }
}

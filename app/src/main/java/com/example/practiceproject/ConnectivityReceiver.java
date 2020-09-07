package com.example.practiceproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()=="CONNECTIVITY_ACTION"){
            Log.d("BROADCAST","Hello from broadcast");

        }
    }
}

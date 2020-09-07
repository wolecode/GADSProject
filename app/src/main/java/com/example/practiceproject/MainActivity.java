package com.example.practiceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public ConnectivityManager connectManager;
    public IntentFilter filter;
    public ConnectivityReceiver receiver;
    public AlarmManager alarm;
    public PendingIntent pending;
    public ProgressBar progress;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView);

        pending = PendingIntent.getActivity(this, 0, new Intent(this,LeaderBoard.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

      connectManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      alarm=(AlarmManager)getSystemService(ALARM_SERVICE);

      if(!connectManager.isDefaultNetworkActive()){
          progress.setVisibility(View.INVISIBLE);
          textView.setVisibility(View.VISIBLE);
      }

      checkConnectivity();


    }

    public void checkConnectivity() {
        connectManager.registerNetworkCallback(getNetworkRequest(),new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(Network network){
                super.onAvailable(network);
                Log.i("AVAILABLE","Network present");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setVisibility(View.INVISIBLE);
                        progress.setVisibility(View.VISIBLE);
                    }
                });
                alarm.set(AlarmManager.ELAPSED_REALTIME,3000, pending);
            }
            @Override
            public void onLost(Network network){
                super.onLost(network);
                Log.i("UNAVAILABLE","No network");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
    }

 public NetworkRequest getNetworkRequest(){
        NetworkRequest networkRequest= new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        return networkRequest;
 }
}
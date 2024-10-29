package com.joyp.healthandfitnessapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            // Device is connected to the Internet
            Toast.makeText(context, "Connected to the Internet", Toast.LENGTH_SHORT).show();
        } else {
            // Device is not connected to the Internet
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}

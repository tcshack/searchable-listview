package com.tcscorp.searchablelistviewapp;

import android.content.Context;
import android.content.Intent;

public class Util {
    public static void launchSharingIntent(Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.sharing_message, BuildConfig.APPLICATION_ID));
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_with)));
    }
}

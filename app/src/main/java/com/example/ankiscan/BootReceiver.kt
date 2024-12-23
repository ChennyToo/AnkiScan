package com.example.ankiscan

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

//On device startup, this class is used to start the PersistentNotificationService
class BootReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
                Log.d("BootReceiver", "Boot completed, starting service...")
                val serviceIntent = Intent(context, PersistentNotificationService::class.java)
                context.startForegroundService(serviceIntent)
            }
        }

}
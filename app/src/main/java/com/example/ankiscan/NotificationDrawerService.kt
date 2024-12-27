package com.example.ankiscan

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.content.Context
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class NotificationDrawerService : AccessibilityService() {
    private val closeDrawerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("NotificationDrawerService", "Received broadcast to close notification drawer")
            closeNotificationDrawer()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("NotificationDrawerService", "Service created")
        val filter = IntentFilter("com.example.ankiscan.CLOSE_NOTIFICATION_DRAWER")
        registerReceiver(closeDrawerReceiver, filter, RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NotificationDrawerService", "Service destroyed")
        unregisterReceiver(closeDrawerReceiver)
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }

    fun closeNotificationDrawer() {
        performGlobalAction(GLOBAL_ACTION_BACK)
    }
}
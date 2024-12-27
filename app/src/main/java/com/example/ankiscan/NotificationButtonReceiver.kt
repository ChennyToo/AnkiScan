package com.example.ankiscan

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityManager


class NotificationButtonReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotificationButton", "hello world")
        //TODO user needs to close the notification drawer themselves to see that
        //TODO the accessibility screen has been opened
        handleAccessibilityService(context)
        val broadcastIntent = Intent("com.example.ankiscan.CLOSE_NOTIFICATION_DRAWER")
        context.sendBroadcast(broadcastIntent)

    }
    //auto enable accessibility service for debugging purposes
    //adb shell settings put secure enabled_accessibility_services com.example.ankiscan/com.example.ankiscan.NotificationDrawerService
    //adb shell settings put secure accessibility_enabled 1
    fun isAccessibilityServiceEnabled(context: Context, service: Class<out AccessibilityService?>): Boolean {
        val am: AccessibilityManager =
            context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices: List<AccessibilityServiceInfo> =
            am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)

        for (enabledService in enabledServices) {
            val enabledServiceInfo: ServiceInfo = enabledService.resolveInfo.serviceInfo
            if (enabledServiceInfo.packageName.equals(context.packageName) && enabledServiceInfo.name.equals(
                    service.name
                )
            ) return true
        }

        return false
    }

    //goes to the accessibility service settings if the service is not enabled
    fun handleAccessibilityService(context: Context) {
        if (!isAccessibilityServiceEnabled(context, NotificationDrawerService::class.java)) {
            val intent = Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            try {
                pendingIntent.send()
            } catch (e: PendingIntent.CanceledException) {
                Log.e("NotificationButton", "PendingIntent was canceled", e)
            }
        }
    }
}
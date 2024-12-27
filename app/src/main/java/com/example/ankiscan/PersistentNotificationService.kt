package com.example.ankiscan


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

//Main piece of the project, the user can simply click a button on the notification to scan the screen

class PersistentNotificationService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
        startForeground(1, createNotification(this))
    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return START_STICKY
//    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun createNotificationChannel(context: Context) {
        val name = "AnkiScanChannel"
        val descriptionText = "Channel for AnkiScan notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("ANKISCAN_CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotification(context: Context): Notification {
        val buttonIntent = Intent(context, NotificationButtonReceiver::class.java)
        val buttonPendingIntent = PendingIntent.getBroadcast(context, 0, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, "ANKISCAN_CHANNEL_ID")
            .setSmallIcon(R.drawable.japanese_example)
            .setContentTitle("AnkiScan")
            .setContentText("This is a persistent notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .addAction(R.drawable.notification_scan_button, "Scan", buttonPendingIntent)
            .build()
    }
}
package com.example.ankiscan

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.ankiscan.ui.theme.AnkiScanTheme
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ankiscan.ui.screens.DictViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
//            createNotificationChannel(this)
//            showNotification(this)
//        } else {
//            val pushNotificationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
//            { isGranted: Boolean ->
//                if (isGranted) {
//                    createNotificationChannel(this)
//                    showNotification(this)
//                } else {
//                    //TODO handle what happens if user denies permission
//                    Log.d("MainActivity", "Notification permission denied")
//                }
//            }
//            pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
//        }


        setContent {
            AnkiScanTheme {
                MainScreen()
            }
        }
    }
}



//code moved to PersistentNotificationService.kt
//fun createNotificationChannel(context: Context) {
//        val name = "AnkiScanChannel"
//        val descriptionText = "Channel for AnkiScan notifications"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel("ANKISCAN_CHANNEL_ID", name, importance).apply {
//            description = descriptionText
//        }
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//}
//
//@SuppressLint("MissingPermission")
//fun showNotification(context: Context) {
//    val builder = NotificationCompat.Builder(context, "ANKISCAN_CHANNEL_ID")
//        .setSmallIcon(R.drawable.japanese_example)
//        .setContentTitle("AnkiScan")
//        .setContentText("This is a persistent notification")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        .setOngoing(true)
//
//    NotificationManagerCompat.from(context).notify(1, builder.build())
//}

@Composable
fun MainScreen(viewModel: DictViewModel = viewModel(factory = DictViewModel.Factory)) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var recognizedText by remember { mutableStateOf("Recognizing...") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.japanese_example),
                contentDescription = "Sample Image",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recognizedText)
        }

        val bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.japanese_example)
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(JapaneseTextRecognizerOptions.Builder().build())


        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                recognizedText = visionText.text
                Log.d("MainActvity", "Recognized text: ${visionText.text}")

            }
            .addOnFailureListener { e ->
                recognizedText = "Recognition failed: ${e.message}"
                Log.d("MainActvity", "Recognition failed: ${e.message}")
            }
    }
}

package com.example.standup

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mNotificationManager: NotificationManager
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmToggle = findViewById<ToggleButton>(R.id.alarmToggle)

        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        val notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager


        alarmToggle.setOnCheckedChangeListener { compoundButton, isChecked ->
            var toastMessage: String = ""
            if (isChecked) {
                deliverNotification(this)
                //Set the toast message for the "on" case.
                toastMessage = "Stand Up Alarm On!"
            } else {
                mNotificationManager.cancelAll()
                //Set the toast message for the "off" case.
                toastMessage = "Stand Up Alarm Off!"
            }

            //Show a toast to say the alarm is turned on or off.

            //Show a toast to say the alarm is turned on or off.
            Toast.makeText(this@MainActivity, toastMessage, Toast.LENGTH_SHORT)
                .show()
        }

//        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager;

        createNotificationChannel()
    }


    private fun createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Stand up notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifies every 15 minutes to stand up and walk"
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
    }



}
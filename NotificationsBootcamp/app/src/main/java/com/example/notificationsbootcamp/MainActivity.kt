package com.example.notificationsbootcamp

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random.Default.nextInt


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDefaultNotificationChannel()
        createNotificationChannelGroup()
        createBounceNotificationChannel()

        findViewById<Button>(R.id.txtAndTitle).setOnClickListener {
            firstNotification()
        }

        findViewById<Button>(R.id.txtTitleAndLargeIcon).setOnClickListener {
            secondNotification()
        }

        findViewById<Button>(R.id.txtTitleAndSmallIcon).setOnClickListener {
            thirdNotification()
        }

        findViewById<Button>(R.id.sound).setOnClickListener {
            fourthNotification()
        }

        findViewById<Button>(R.id.intent).setOnClickListener {
            fifthNotification()
        }
    }

    private fun dispatchNotification(builder: NotificationCompat.Builder) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(nextInt(), builder.build())
    }

    private fun firstNotification() {
        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL)
            .setContentTitle("First Button Title")
            .setContentText("First Button Message")
            .setSmallIcon(R.drawable.ic_pizza_24)

        dispatchNotification(builder)
    }

    private fun secondNotification() {
        val bitmapIcon = tintImage(R.drawable.ic_launcher_foreground)

        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL)
            .setContentTitle("Second $DEFAULT_TITLE")
            .setContentText("Second $DEFAULT_MESSAGE")
            .setSmallIcon(R.drawable.ic_pizza_24)
            .setLargeIcon(bitmapIcon)

        dispatchNotification(builder)
    }

    private fun thirdNotification() {
        val bitmapIcon = tintImage(R.drawable.ic_launcher_foreground)

        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL)
            .setContentTitle("Third $DEFAULT_TITLE")
            .setContentText("Third $DEFAULT_MESSAGE")
            .setSmallIcon(R.drawable.ic_pizza_24)
            .setLargeIcon(bitmapIcon)
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(bitmapIcon).bigLargeIcon(null)
            )

        dispatchNotification(builder)
    }

    private fun fourthNotification() {
        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL)
            .setContentTitle("Fourth $DEFAULT_TITLE")
            .setContentTitle("Fourth $DEFAULT_MESSAGE")
            .setSound(buildBounceSoundUri())
            .setVibrate(longArrayOf(1000, 1000))
            .setSmallIcon(R.drawable.ic_pizza_24)

        dispatchNotification(builder)
    }

    private fun fifthNotification() {
        val builder = NotificationCompat.Builder(this, "bounce")
            .setSmallIcon(R.drawable.ic_pizza_24)
            .setContentText("Fifth $DEFAULT_MESSAGE")
            .setContentTitle("Fifth $DEFAULT_TITLE")

        dispatchNotification(builder)
    }

    private fun createBounceNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            val channel = NotificationChannel(
                "bounce", "Bounce", NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description =  DEFAULT_MESSAGE
                group = "custom"
                vibrationPattern = longArrayOf(1000,1000,1000)

                setSound(buildBounceSoundUri(), audioAttributes)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun createDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default", "Default", NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = DEFAULT_MESSAGE
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotificationChannelGroup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val group = NotificationChannelGroup("custom", "Custom")

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannelGroup(group)
        }
    }



    private fun buildBounceSoundUri() =
        Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.bounce)

    // use if the large icon is a xml
    private fun tintImage(drawableRes: Int): Bitmap? {
        val drawable = resources.getDrawable(drawableRes)
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    companion object {
        const val DEFAULT_CHANNEL = "default"
        const val DEFAULT_TITLE = "Button Title"
        const val DEFAULT_MESSAGE = "Button Message"
    }
}
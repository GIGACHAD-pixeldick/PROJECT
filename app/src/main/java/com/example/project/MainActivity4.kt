package com.example.project

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dd = getSharedPreferences("data_base",0)
        val editor = dd.edit()
        editor.putString("login","test@mail.ru")
        editor.putString("password", "157379")
        editor.apply()
        var login = getSharedPreferences("data_base",0)
            .getString("login", "error")
        val a = findViewById<ImageView>(R.id.imageView)
        a.setOnClickListener {
            if (pochta.text.isNotEmpty()&&(parol.text.isNotEmpty()))
            {
                val al = AlertDialog.Builder(this)
                    .setTitle("Dialog menu")
                    .setMessage("Text Window")
                    .setPositiveButton("Enable", null)
                    .setNegativeButton("Disable", null)
                    .create()
                al.show()
                if (Patterns.EMAIL_ADDRESS.matcher(pochta.text.toString()).matches())
                {
                    if (pochta.text.toString()==getSharedPreferences("data_base",0).getString("login", "error")&& parol.text.toString()==getSharedPreferences("data_base",0).getString("password","you're idiot"))
                    {
                            Toast.makeText(this,"Your email successfully confirmed",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity2::class.java))
                        }
                }
            }
        }
    }
}
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onMessageReceived(remoteMessage: RemoteMessage){
        Log.d(TAG,"From: ${remoteMessage.from}")
        remoteMessage.notification?.apply {
            var pendingIntent: PendingIntent? = null
            if (remoteMessage.data.isNotEmpty()) {
                val body = remoteMessage.data
                val type = body["type"]
                if (type == "for task") {
                    val notificationIntent = if (getSharedPreferences("0", 0).getString(
                            "person_id",
                            null
                        ) == null
                    ) Intent(
                        applicationContext,
                        AuthActivity::class.java
                    ) else Intent(
                        applicationContext,
                        TaskActivity::class.java
                    )
                    notificationIntent.putExtra("taskId", body["id_task"]!!.toInt())
                    notificationIntent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    pendingIntent = PendingIntent.getActivity(
                        applicationContext,
                        105,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                }
            }
            Log.d(TAG, "Message Notification Body: ${this.body}")
            (this@MyFirebaseMessagingService.getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .sendNotification(
                    this@MyFirebaseMessagingService,
                    this.body!!,
                    this.title!!,
                    pendingIntent = pendingIntent
                )
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG,"Refreshed token :$token")
    }

    companion object {
        private const val TAG = "FirebaseMsgService"
    }
}

@SuppressLint("UnspecifiedImmutableFlag")
private fun NotificationManager.sendNotification(
    context: Context,
    messageBody: String,
    title: String = context.getString(R.string.app_name),
    channelName: String = "Tasks",
    channelId: String = "TasksSchedulerAppIdChannel",
    idNotification: Unit = generateNotificationIdFromDate(),
    pendingIntent: PendingIntent? = null,
    autoCancel: Boolean = true,
    smallIconId: Int = R.drawable.icon_notification,
    color_icon: Int = ContextCompat.getColor(context,R.color.coloraccentdark)
) {
    val mPendingIntent = if (pendingIntent == null) {
        val intent = Intent(context,AuthActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        PendingIntent.getActivity(
            context,0 /*Request code*/,intent,
            PendingIntent.FLAG_ONE_SHOT
        )
    } else {
        pendingIntent
    }
    val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    NotificationCompat.Builder(context,channelId)
        .setSmallIcon(smallIconId)
        .setContentTitle(title)
        .setContentText(messageBody)
        .setAutoCancel(autoCancel)
        .setColor(color_icon)
        .setStyle(NotificationCompat.BigTextStyle())
        .setSound(defaultSoundUri)
        .setContentIntent(mPendingIntent)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        createNotificationChannel(channel)
    }
    notify(idNotification)
}

fun notify(idNotification: Unit) {

}

fun generateNotificationIdFromDate() {

}

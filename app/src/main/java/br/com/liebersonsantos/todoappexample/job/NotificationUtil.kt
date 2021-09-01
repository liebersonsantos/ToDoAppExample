package br.com.liebersonsantos.todoappexample.job

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import br.com.liebersonsantos.todoappexample.R

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */

private const val PRIMARY_CHANNEL_ID = "primary_channel_id"

object NotificationUtil {

    fun sendNotification(context: Context, taskName: String, taskId: Long) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createChannel(notificationManager, context)
        val notificationBuilder: NotificationCompat.Builder =
            getNotificationBuilder(context, taskName)

        notificationManager.notify(taskId.toInt(), notificationBuilder.build())

    }

    private fun getNotificationBuilder(context: Context, taskName: String): NotificationCompat.Builder {
       return NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
           .setSmallIcon(R.drawable.ic_notification)
           .setContentTitle(taskName)
           .setContentText(context.getString(R.string.notification_text))
           .setPriority(NotificationCompat.PRIORITY_HIGH)
    }


    private fun createChannel(notificationManager: NotificationManager, context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description =
                context.getString(R.string.notification_channel_description)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun deleteNotification(context: Context, id: Int) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(id)
    }

}
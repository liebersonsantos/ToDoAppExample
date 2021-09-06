package br.com.liebersonsantos.todoappexample.job

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.TASK_ID

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
            getNotificationBuilder(context, taskName, taskId)

        notificationManager.notify(taskId.toInt(), notificationBuilder.build())

    }

    private fun getNotificationBuilder(context: Context, taskName: String, taskId: Long): NotificationCompat.Builder {
       return NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
           .setSmallIcon(R.drawable.ic_notification)
           .setContentTitle(taskName)
           .setContentText(context.getString(R.string.notification_text))
           .setAutoCancel(true)
           .setPriority(NotificationCompat.PRIORITY_HIGH)
           .setContentIntent(pendingIntentNav(context, taskId))
    }

//    private fun pendingIntent(context: Context, taskId: Long): PendingIntent {
//        val intent = Intent(context, MainActivity::class.java)
//        intent.putExtra("toFragment", taskId.toInt())
//        return PendingIntent.getActivity(context, taskId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
//    }

    private fun pendingIntentNav(context: Context, taskId: Long): PendingIntent {
        val bundle = Bundle().apply {
            putLong(TASK_ID, taskId)
        }

        return NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailFragment)
            .setArguments(bundle)
            .createPendingIntent()
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
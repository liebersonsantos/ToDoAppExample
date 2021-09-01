package br.com.liebersonsantos.todoappexample.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.TASK_ID
import br.com.liebersonsantos.todoappexample.ui.fragment.addtaskfragment.TASK_NAME

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
class NotificationWorkManager(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        val name = inputData.getString(TASK_NAME)
        val id = inputData.getLong(TASK_ID, 0)

        return name?.let {
            NotificationUtil.sendNotification(applicationContext, it, id)
            Result.success()
        } ?: Result.failure()
    }

}
package br.com.liebersonsantos.todoappexample.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import br.com.liebersonsantos.todoappexample.R
import br.com.liebersonsantos.todoappexample.data.Status

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class StatusView(context: Context, attrs: AttributeSet): AppCompatTextView(context, attrs){

    fun setStatus(status: Status){
        val bg = when(status){
            Status.TODO, Status.UNDEFINED -> R.drawable.rounded_blue
            Status.PROGRESS -> R.drawable.rounded_orange
            Status.DONE -> R.drawable.rounded_green
        }
        setBackgroundResource(bg)
    }
}
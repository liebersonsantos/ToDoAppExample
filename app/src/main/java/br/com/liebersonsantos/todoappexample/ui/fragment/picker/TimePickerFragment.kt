package br.com.liebersonsantos.todoappexample.ui.fragment.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
class TimePickerFragment: DialogFragment() {

    lateinit var timeListener: TimePickerDialog.OnTimeSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]

        return TimePickerDialog(activity, timeListener, hour, minute, true)
    }

    fun setTimeSetListener(listener: TimePickerDialog.OnTimeSetListener) {
        timeListener = listener
    }

    companion object {
        fun newInstance(listener: TimePickerDialog.OnTimeSetListener): TimePickerFragment {
            val instance = TimePickerFragment()
            instance.setTimeSetListener(listener)
            return instance
        }
    }
}